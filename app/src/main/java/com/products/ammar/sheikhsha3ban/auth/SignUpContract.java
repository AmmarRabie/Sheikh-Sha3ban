package com.products.ammar.sheikhsha3ban.auth;


import com.products.ammar.sheikhsha3ban.IBaseActions;
import com.products.ammar.sheikhsha3ban.IBaseView;

/**
 * Created by AmmarRabie on 08/03/2018.
 */

interface SignUpContract {


    /**
     * views methods implemented by fragment
     */
    interface Views extends IBaseView<Actions> {
        /**
         * show message when sign up succssed
         */
        void showOnSuccess();

        /**
         * show message when a error occured
         *
         * @param cause
         */
        void showErrorMessage(String cause);

        /**
         * show progress indicator of a task
         *
         * @param
         */
        void showProgressIndicator();

        /**
         * hide progress indicator of a task
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
         * sign up functionality
         *
         * @param name
         * @param email
         * @param password
         * @param confirmPassword
         */
        void signUp(String name, String email, String password, String confirmPassword, byte[] profileImage);

    }
}
