package com.products.ammar.sheikhsha3ban.common.data.mock;


import com.products.ammar.sheikhsha3ban.common.data.DataService;
import com.products.ammar.sheikhsha3ban.common.data.model.AdviceModel;
import com.products.ammar.sheikhsha3ban.common.data.model.PostModel;
import com.products.ammar.sheikhsha3ban.common.data.model.UserModel;

import java.util.ArrayList;

/**
 * Created by AmmarRabie on 24/04/2018.
 */

/**
 * Mock implementation of the {@link DataService}, simple enough to test the app front end with
 * dummy data
 */
public class MockRepo implements DataService {
    private static final String TAG = "MockRepo";

    private static MockRepo INSTANCE = null;


    private MockRepo() {

        // insert the main system data (users, groups, sessions)
    }

    public static MockRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MockRepo();
        }
        return INSTANCE;
    }


    @Override
    public void getUser(String userId, Get<UserModel> callback) {

    }

    @Override
    public void insertUser(UserModel userModel, byte[] profileImageBytes, Insert<Void> callback) {

    }


    @Override
    public void getAllAdvice(Get<ArrayList<AdviceModel>> callback) {

    }

    @Override
    public void updateUserProfileImage(String userId, byte[] newImageBytes, Update callback) {

    }

    @Override
    public void updateUserName(String userId, String newName, Update callback) {

    }

    @Override
    public void getMorePosts(Get<ArrayList<PostModel>> callback, int moreIndx) {

    }

    @Override
    public void insertPost(String userId, String body, Insert<PostModel> callback) {

    }

    @Override
    public void forget(Listen listener) {

    }
}
