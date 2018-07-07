package com.products.ammar.sheikhsha3ban.profile;


import com.products.ammar.sheikhsha3ban.IBaseActions;
import com.products.ammar.sheikhsha3ban.IBaseView;
import com.products.ammar.sheikhsha3ban.common.data.model.UserModel;

/**
 * Created by AmmarRabie on 08/03/2018.
 */

interface ProfileContract {


    /**
     * views methods implemented by fragment
     */
    interface Views extends IBaseView<Actions> {

        /**
         * to show error message when something went wrong
         *
         * @param cause readable message of the error
         */
        void showErrorMessage(String cause);

        /**
         * to show user info
         * @param user the user info to be displayed
         */
        void showUserInfo(UserModel user);

        /**
         * to show message when change name success
         *
         * @param
         */
        void showOnChangeNameSuccess();

        /**
         * to show message when change password success
         *
         * @param
         */
        void showOnChangePassSuccess();

        /**
         * to show message when SignOut
         *
         * @param
         */
        void showOnSignOutSuccess();

        /**
         * to show progess indicator of a specific task
         *
         * @param
         */
        void showProgressIndicator(String progressWorkMessage);

        /**
         * to hide progess indicator of a specific task
         *
         * @param
         */
        void hideProgressIndicator();
    }


    /**
     * Actions methods implemented by presenter
     */
    interface Actions extends IBaseActions {
        /**
         * logic of change password functionality
         *
         * @param oldPass
         * @param pass
         * @param confirmPass
         */
        void changePassword(String oldPass, String pass, String confirmPass);

        /**
         * logic of change name functionality
         *
         * @param newName
         */
        void changeName(String newName);

        /**
         * logic of signout functionality
         */
        void signOut();

        /**
         * logic of changing profile image
         */
        void changeProfileImage(byte[] newImageBytes);
    }
}
