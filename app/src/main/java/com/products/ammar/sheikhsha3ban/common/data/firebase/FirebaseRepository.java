package com.products.ammar.sheikhsha3ban.common.data.firebase;


import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.UploadTask;
import com.products.ammar.sheikhsha3ban.common.data.DataService;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseContract.AdviceEntry;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseContract.PostEntry;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseContract.UserEntry;
import com.products.ammar.sheikhsha3ban.common.data.model.AdviceModel;
import com.products.ammar.sheikhsha3ban.common.data.model.PostModel;
import com.products.ammar.sheikhsha3ban.common.data.model.UserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 * This is the implementation of the {@link DataService}
 * using firebase database
 * <p>
 * this class do the logic of querying data from firebase database
 */
public class FirebaseRepository extends FirebaseRepoHelper {

    private static FirebaseRepository INSTANCE = null;

    public static FirebaseRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseRepository();
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            FirebaseDatabase.getInstance().getReference().keepSynced(true);
        }
        return INSTANCE;
    }

    @Override
    public void getUser(String userId, final Get<UserModel> callback) {
        getUserRef(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot userSnapshot) {
                if (!userSnapshot.exists()) {
                    callback.onDataNotAvailable();
                    return;
                }
                UserModel userModel = Serializer.user(userSnapshot);
                callback.onDataFetched(userModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }


    private void getUserWithoutImage(String userId, final Get<UserModel> callback) {
        getUserRef(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot userSnapshot) {
                if (!userSnapshot.exists()) {
                    callback.onDataNotAvailable();
                    return;
                }
                UserModel userModel = Serializer.user(userSnapshot);
                callback.onDataFetched(userModel);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }

    @Override
    public void insertUser(final UserModel userModel, final byte[] profileImageBytes ,final Insert<Void> callback) {
        HashMap<String, String> values = new HashMap<>();
        values.put(UserEntry.KEY_EMAIL, userModel.getEmail());
        values.put(UserEntry.KEY_NAME, userModel.getName());

        getUserRef(userModel.getId()).setValue(values).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    callback.onError("Can't insert the user");
                    return;
                }
                callback.onDataInserted(null);
                if (profileImageBytes != null)
                    insertProfileImage();
            }

            private void insertProfileImage() {
                getProfileImageRef(userModel.getId()).putBytes(profileImageBytes);
                getProfileImageRef(userModel.getId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        getUserRef(userModel.getId()).child(UserEntry.KEY_IMAGE).setValue(uri.toString());
                    }
                });
            }
        });
    }

    @Override
    public void getAllAdvice(final Get<ArrayList<AdviceModel>> callback) {
        final ArrayList<AdviceModel> advices = new ArrayList<>();
        getReference(AdviceEntry.KEY_THIS)
                .orderByChild(AdviceEntry.KEY_DATE)
                .limitToFirst(5)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot advicesSnapshot) {
                for (final DataSnapshot oneAdviceSnapshot : advicesSnapshot.getChildren()) {
                    String creatorId = oneAdviceSnapshot.child(AdviceEntry.KEY_CREATOR).getValue(String.class);
                    getUserRef(creatorId).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot creatorSnapshot) {
                            AdviceModel newAdvice = Serializer.advice(oneAdviceSnapshot, creatorSnapshot);
                            advices.add(newAdvice);
                            if (advices.size() == advicesSnapshot.getChildrenCount())
                                callback.onDataFetched(advices);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            callback.onError(databaseError.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void insertPost(String userId, final String body, final Insert<PostModel> callback) {
        final String key = getReference(PostEntry.KEY_THIS).push().getKey();
        final long date = System.currentTimeMillis();
        HashMap<String, Object> values = new HashMap<>();
        values.put(PostEntry.KEY_BODY, body);
        values.put(PostEntry.KEY_CREATOR, userId);
        values.put(PostEntry.KEY_DATE, date);
        getPostRef(key).setValue(values);
        getUser(userId, new Get<UserModel>() {
            @Override
            public void onDataFetched(UserModel data) {
                callback.onDataInserted(new PostModel(key, data, body, new Date(date)));
            }
        });
    }

    @Override
    public void getMorePosts(final Get<ArrayList<PostModel>> callback, int moreIndx) {
        final ArrayList<PostModel> posts = new ArrayList<>();
        getReference(PostEntry.KEY_THIS)
                .orderByChild(PostEntry.KEY_DATE)
                .limitToLast(5)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot postsSnapshot) {
                        for (final DataSnapshot onePostSnapshot : postsSnapshot.getChildren()) {
                            String creatorId = onePostSnapshot.child(AdviceEntry.KEY_CREATOR).getValue(String.class);
                            getUserRef(creatorId).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot creatorSnapshot) {
                                    PostModel newPost = Serializer.post(onePostSnapshot, creatorSnapshot);
                                    posts.add(newPost);
                                    if (posts.size() == postsSnapshot.getChildrenCount())
                                        callback.onDataFetched(posts);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    callback.onError(databaseError.getMessage());
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void updateUserName(String userId, String newName, final Update callback) {
        final DatabaseReference thisUserRef = FirebaseDatabase.getInstance()
                .getReference(UserEntry.KEY_THIS)
                .child(userId);


        thisUserRef.child(UserEntry.KEY_NAME).setValue(newName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onUpdateSuccess();
                        } else {
                            if (task.getException() != null)
                                callback.onError(task.getException().getMessage());
                            else callback.onError("Can't update user name");
                        }
                    }
                });
    }

    @Override
    public void updateUserProfileImage(String userId, byte[] newImageBytes, final Update callback) {
        getProfileImageRef(userId).putBytes(newImageBytes)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        if (callback != null)
                            callback.onUpdateSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                if (callback != null)
                    callback.onError(e.getMessage());
            }
        });
    }

    @Override
    public void forget(Listen listener) {

    }
}
