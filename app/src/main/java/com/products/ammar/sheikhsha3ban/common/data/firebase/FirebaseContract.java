package com.products.ammar.sheikhsha3ban.common.data.firebase;

/**
 * Created by AmmarRabie on 14/04/2018.
 */

final class FirebaseContract {
    public static final String KEY_THIS = "smart-lecture-cmp";

    private FirebaseContract() {
    }

    static final class UserEntry {
        static final String KEY_THIS = "user";


        static final String KEY_NAME = "name";
        static final String KEY_EMAIL = "email";
        static final String KEY_IMAGE = "profileImageUrl";

        static String[] keySet;
        static String[] requiredChildes;

        static {
            keySet = new String[]{
                    KEY_NAME,
                    KEY_EMAIL};
        }

        static {
            requiredChildes = new String[]{KEY_NAME,
                    KEY_EMAIL};
        }

        private UserEntry() {
        }
    }

    static final class AdviceEntry {
        static final String KEY_THIS = "advice";

        static final String KEY_BODY = "body";
        static final String KEY_CREATOR = "creator";
        static final String KEY_DATE = "date";
    }

    static final class PostEntry {
        static final String KEY_THIS = "post";

        static final String KEY_BODY = "body";
        static final String KEY_CREATOR = "creator";
        static final String KEY_DATE = "date";
    }


    static final class StorageEntry {
        static final String KEY_THIS = "gs://smart-lecture-cmp.appspot.com/";

        static final String FOLDER_PROFILE_IMAGES = "profile-images";
    }


}
