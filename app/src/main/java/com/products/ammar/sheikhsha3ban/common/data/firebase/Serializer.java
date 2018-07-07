package com.products.ammar.sheikhsha3ban.common.data.firebase;

/**
 * Created by AmmarRabie on 21/04/2018.
 */

import com.google.firebase.database.DataSnapshot;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseContract.AdviceEntry;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseContract.PostEntry;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseContract.UserEntry;
import com.products.ammar.sheikhsha3ban.common.data.model.AdviceModel;
import com.products.ammar.sheikhsha3ban.common.data.model.PostModel;
import com.products.ammar.sheikhsha3ban.common.data.model.UserModel;

import java.util.Date;

/**
 * Hide the logic of getting models from firebase DataSnapshot
 */
class Serializer {
    private static final String TAG = "Serializer";

/*    static GroupModel group(DataSnapshot groupSnapshot) {
        String[] requiredChildes = GroupEntry.requiredChildes;
        if (!checkRequiredChildes(requiredChildes, groupSnapshot)) return null;

        String groupId = groupSnapshot.getKey();
        String name = groupSnapshot.child(GroupEntry.KEY_NAME).getValue(String.class);
        String ownerId = groupSnapshot.child(GroupEntry.KEY_OWNER_ID).getValue(String.class);

        return new GroupModel(name, groupId, ownerId);
    }*/

    static UserModel user(DataSnapshot userRoot) {
        String name = userRoot.child(UserEntry.KEY_NAME).getValue(String.class);
        String email = userRoot.child(UserEntry.KEY_EMAIL).getValue(String.class);
        String profileImage = userRoot.child(UserEntry.KEY_IMAGE).getValue(String.class);
        return new UserModel(userRoot.getKey(), name, email, profileImage);
    }

    static PostModel post(DataSnapshot postSnapShot, DataSnapshot creatorSnapshot) {
        String id, body;
        id = postSnapShot.child(PostEntry.KEY_THIS).getValue(String.class);
        body = postSnapShot.child(PostEntry.KEY_BODY).getValue(String.class);
        Date date = new Date(postSnapShot.child(PostEntry.KEY_DATE).getValue(long.class));
        UserModel creatorModel = user(creatorSnapshot);
        return new PostModel(id, creatorModel, body, date);
    }

    public static AdviceModel advice(DataSnapshot adviceSnapshot, DataSnapshot userSnapshot) {
        String id = adviceSnapshot.child(AdviceEntry.KEY_THIS).getValue(String.class);
        String body = adviceSnapshot.child(AdviceEntry.KEY_BODY).getValue(String.class);
        long date = ((long) adviceSnapshot.child(AdviceEntry.KEY_DATE).getValue());
        UserModel creatorModel = user(userSnapshot);
        return new AdviceModel(id, creatorModel, body, new Date(date));
    }
}