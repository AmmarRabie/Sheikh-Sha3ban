package com.products.ammar.sheikhsha3ban.common.auth;

/**
 * Created by AmmarRabie on 17/04/2018.
 */

/**
 * Interface for abstraction of smart lecture authentication process.
 * <p>
 * Usually when new class implements this class, also implements {@link AuthenticatedUser}
 */
public interface AuthService {
    /**
     * authenticate a user with email and password if he sign up before to the system
     *
     * @param email    The email of the user
     * @param pass     the password of the provided email
     * @param callback the callback when the signing is success or fail
     */
    void signIn(String email, String pass, OnAuthActionComplete<String> callback);

    /**
     * Authenticate a new user into the system, this function authenticate the user only and
     * doesn't insert him into the database system. The function validate the inputs
     *
     * @param email    the email of the new user
     * @param pass     the password of the new user
     * @param callback the callback called in cases of success or a failure
     */
    void signUp(String email, String pass, OnAuthActionComplete<String> callback);

    /**
     * Sign out the current user, if there is not a user then it do nothing.
     * After calling it {@link #getCurrentUser()} should return null.
     */
    void signOut();


    /**
     * @return get current authenticated user, null if there is no authenticated user
     */
    AuthenticatedUser getCurrentUser();

    /**
     * Callback for authentication process
     *
     * @param <SuccessData> The type of the data returned after authentication success
     */
    interface OnAuthActionComplete<SuccessData> {
        void onSuccess(SuccessData data);

        void onError(String error);
    }

}
