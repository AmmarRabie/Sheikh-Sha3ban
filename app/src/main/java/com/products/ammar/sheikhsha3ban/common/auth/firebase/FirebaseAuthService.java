package com.products.ammar.sheikhsha3ban.common.auth.firebase;


/**
 * Created by AmmarRabie on 17/04/2018.
 */


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.products.ammar.sheikhsha3ban.common.auth.AuthService;
import com.products.ammar.sheikhsha3ban.common.auth.AuthenticatedUser;

/**
 * The implementation of the AuthService using FirebaseAuth.
 * This class is also considered an adapter for the {@link FirebaseAuth}
 */

public class FirebaseAuthService implements AuthService {

    private static FirebaseAuthService INSTANCE;

    private AuthenticatedUser mCurrentUser = null;

    private FirebaseAuthService() {
    }


    public static FirebaseAuthService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FirebaseAuthService();
        return INSTANCE;
    }

    @Override
    public void signIn(String email, final String pass, final OnAuthActionComplete<String> callback) {
        Task<AuthResult> task = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass);
        if (callback == null) return;
        task.addOnCompleteListener
                (new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess(getCurrentUser().getUserId());
                            updateUser();
                            return;
                        }
                        if (task.getException() != null)
                            callback.onError(task.getException().getMessage());
                        else callback.onError("Can't sign in");
                    }
                });
    }

    @Override
    public void signUp(String email, final String pass, final OnAuthActionComplete<String> callback) {
        Task<AuthResult> task = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass);
        if (callback == null) return;
        task.addOnCompleteListener
                (new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess(getCurrentUser().getUserId());
                            updateUser();
                            return;
                        }
                        if (task.getException() != null)
                            callback.onError(task.getException().getMessage());
                    }
                });
    }

    @Override
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        updateUser();
    }

    @Override
    public AuthenticatedUser getCurrentUser() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            mCurrentUser = null;
            return null;
        }
        if (mCurrentUser != null) return mCurrentUser;
        mCurrentUser = new FirebaseAuthenticatedUser(currentUser);
        return mCurrentUser;
    }

    private void updateUser() {
        // force the getCurrentUser to create a new user
        mCurrentUser = null;
    }
}

