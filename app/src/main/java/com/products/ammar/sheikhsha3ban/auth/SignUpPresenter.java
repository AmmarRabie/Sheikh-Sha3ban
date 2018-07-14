package com.products.ammar.sheikhsha3ban.auth;


import android.util.Log;

import com.products.ammar.sheikhsha3ban.common.auth.AuthService;
import com.products.ammar.sheikhsha3ban.common.auth.AuthenticatedUser;
import com.products.ammar.sheikhsha3ban.common.data.DataService;
import com.products.ammar.sheikhsha3ban.common.data.model.UserModel;
import com.products.ammar.sheikhsha3ban.common.util.ValidationUtils;

/**
 * Created by AmmarRabie on 08/03/2018.
 */

class SignUpPresenter implements SignUpContract.Actions {


    private AuthService mAuthService;
    private DataService mDataSource;
    private SignUpContract.Views mView;
    private boolean isInTask;

    private AuthenticatedUser.DeleteCallback deleteTaskListener;


    public SignUpPresenter(AuthService authService, DataService dataSource, SignUpContract.Views view) {
        mAuthService = authService;
        mDataSource = dataSource;
        mView = view;
        isInTask = false;

        deleteTaskListener = new AuthenticatedUser.DeleteCallback() {
            @Override
            public void onDeleteSuccess() {
                Log.e("SignUpPresenter: ", "the user can't write his data in the " +
                        "database although he is authenticated");
                mView.showErrorMessage("can't sign up right now");
                endTask();

            }

            @Override
            public void onError(String error) {
                // else the user is signed in although we can't insert him at the database, throw exception
//                    throw new RuntimeException("The user is authenticated and not inserted");
                Log.wtf("SignUpPresenter: ", "The user is authenticated and not inserted");
            }
        };
        mView.setPresenter(this);
    }

    private boolean startTask() {
        if (isInTask)
            return false;

        isInTask = true;
        mView.showProgressIndicator();
        return true;
    }

    private boolean endTask() {
        if (!isInTask)
            return false;

        isInTask = false;
        mView.hideProgressIndicator();
        return true;
    }

    @Override
    public void start() {
    }

    @Override
    public void signUp(final String name, final String email, final String phone,
                       String password, String confirmPassword, final byte[] profileImage) {
        if (!startTask())
            return;

        // sign up
        if (email == null || email.isEmpty() ||
                password == null || password.isEmpty() ||
                name == null || name.isEmpty() ||
                confirmPassword == null || confirmPassword.isEmpty() ||
                phone == null || phone.isEmpty()) {
            mView.showErrorMessage("all fields can't be empty");
            endTask();
            return;
        }
        if (!ValidationUtils.emailValidator(email)) {
            mView.showErrorMessage("Invalid  Email Address");
            endTask();
            return;
        }
        if (!ValidationUtils.userNameValidator(name)) {
            mView.showErrorMessage("Invalid UserName");
            endTask();
            return;
        }
        if (!ValidationUtils.phoneValidator(phone)) {
            mView.showErrorMessage("Invalid phone number");
            endTask();
            return;
        }
        if (!password.equals(confirmPassword)) {
            mView.showErrorMessage("two passwords are different");
            endTask();
            return;
        }

        mAuthService.signUp(email, password, new AuthService.OnAuthActionComplete<String>() {
            @Override
            public void onSuccess(String userId) {
                Log.d("SignUpPresenter: ", "createUserWithEmailAndPassword:success");
                insertUser(userId, name, email, phone, profileImage);
            }

            @Override
            public void onError(String error) {
                // If sign in fails, display a message to the user.
                Log.w("SignUpPresenter: ", "createUserWithEmailAndPassword:failure");
                mView.showErrorMessage(error);
                endTask();
            }
        });
    }

    private void insertUser(String id, String name, String email, String phone, byte[] profileImageBytes) {
        UserModel newUserModel = new UserModel(id, name, email, phone, null);
        mDataSource.insertUser(newUserModel, profileImageBytes, new DataService.Insert<Void>() {
            @Override
            public void onDataInserted(Void feedback) {
                mView.showOnSuccess();
                endTask();
            }

            @Override
            public void onError(String cause) {
                mAuthService.getCurrentUser().delete(deleteTaskListener);
            }
        });
    }
}
