package com.products.ammar.sheikhsha3ban.common.data.mock;


import android.util.SparseBooleanArray;

import com.products.ammar.sheikhsha3ban.common.data.DataService;
import com.products.ammar.sheikhsha3ban.common.data.model.AdviceModel;
import com.products.ammar.sheikhsha3ban.common.data.model.EvaluationModel;
import com.products.ammar.sheikhsha3ban.common.data.model.PostModel;
import com.products.ammar.sheikhsha3ban.common.data.model.UserDayAttendanceModel;
import com.products.ammar.sheikhsha3ban.common.data.model.UserModel;
import com.products.ammar.sheikhsha3ban.common.data.model.UserMonthAttendanceModel;

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
    public void getUserRats(String userId, Get<EvaluationModel> callback) {

    }

    @Override
    public void updateUserRates(String userId, ArrayList<Integer> partsPos, ArrayList<Integer> quartersPos, ArrayList<Integer> typesPos, ArrayList<Integer> newRates, Update callback) {

    }

    @Override
    public void updateUserRate(String userId, int partsPos, int quarterPos, int typePos, int newRate, Update callback) {

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

    @Override
    public void setOneDayAttendanceForUser(String userId, int year, int month, int day, boolean attend, Update callback) {

    }

    @Override
    public void setOneMonthAttendanceForUser(String userId, int year, int month, SparseBooleanArray attendFlags, Update callback) {

    }

    @Override
    public void getAttendanceForUserOfMonth(String userId, int year, int month, Get<UserMonthAttendanceModel> callback) {

    }

    @Override
    public void getAllUsers(Get<ArrayList<UserModel>> callback) {

    }

    @Override
    public void getDayAttendanceForAllUsers(Get<ArrayList<UserDayAttendanceModel>> callback) {

    }
}
