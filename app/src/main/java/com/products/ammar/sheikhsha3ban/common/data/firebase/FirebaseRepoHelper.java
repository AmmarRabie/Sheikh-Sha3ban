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

    static String zeros480 = "";

    static {
        for (int i = 0; i < 240; i++)
            zeros480 += "00";
    }

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

    static int[][][] parseRats(String rats) {
        int[][][] result = new int[30][8][2];
        int part = 0, quarter = 0, type = 0;
        for (char rateChar : rats.toCharArray()) {
            int parsedRate = Integer.parseInt(rateChar + "");
            result[part][quarter][type++] = parsedRate;
            if (type == 2) {
                type = 0;
                quarter++;
                if (quarter == 8) {
                    quarter = 0;
                    type = 0;
                    part++;
                }
            }
        }
        return result;
    }

    static String deparseRats(int[][][] rats) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 2; k++) {
                    result.append(rats[i][j][k]);
                }
            }
        }
        return result.toString();
    }

    static DatabaseReference getRateRef(String userId) {
        return getUserRef(userId).child(UserEntry.KEY_RATE);
    }

    @Override
    public void forget(Listen listener) {
    }

}
