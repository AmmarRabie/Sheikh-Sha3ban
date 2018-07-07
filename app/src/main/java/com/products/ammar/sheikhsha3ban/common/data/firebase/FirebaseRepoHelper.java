package com.products.ammar.sheikhsha3ban.common.data.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.products.ammar.sheikhsha3ban.common.data.DataService;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseContract.StorageEntry;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseContract.UserEntry;


/**
 * Created by AmmarRabie on 21/04/2018.
 */

/**
 * Helper class for {@link FirebaseRepository}. simplify the logic  of listening and forgetting
 * Also define the get reference for basic entries
 */
abstract class FirebaseRepoHelper implements DataService {


    FirebaseRepoHelper() {

    }


    static DatabaseReference getReference(String ref) {
        return FirebaseDatabase.getInstance().getReference(ref);
    }

    static DatabaseReference getUserRef(String userId) {
        return getReference(UserEntry.KEY_THIS).child(userId);
    }

    static DatabaseReference getPostRef(String postId) {
        return getReference(FirebaseContract.PostEntry.KEY_THIS).child(postId);
    }

    static StorageReference getProfileImageRef(String imageId) {
        return FirebaseStorage.getInstance()
                .getReference(StorageEntry.FOLDER_PROFILE_IMAGES).child(imageId + ".png");
    }


    @Override
    public void forget(Listen listener) {
    }
}
