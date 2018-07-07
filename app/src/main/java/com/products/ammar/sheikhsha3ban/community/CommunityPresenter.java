package com.products.ammar.sheikhsha3ban.community;

import com.products.ammar.sheikhsha3ban.common.auth.AuthService;
import com.products.ammar.sheikhsha3ban.common.data.DataService;
import com.products.ammar.sheikhsha3ban.common.data.model.AdviceModel;
import com.products.ammar.sheikhsha3ban.common.data.model.PostModel;

import java.util.ArrayList;
import java.util.Collections;

public class CommunityPresenter implements CommunityContract.Actions {

    private final AuthService authService;
    private CommunityContract.Views mView;
    private DataService data;

    public CommunityPresenter(AuthService authService, CommunityContract.Views view, DataService source) {
        mView = view;
        mView.setPresenter(this);
        data = source;
        this.authService = authService;
    }

    @Override
    public void start() {
        data.getMorePosts(new DataService.Get<ArrayList<PostModel>>() {
            @Override
            public void onDataFetched(ArrayList<PostModel> data) {
                Collections.reverse(data);
                mView.showPosts(data);
            }
        }, 1);
    }

    @Override
    public void insertPost(String body) {
        data.insertPost(authService.getCurrentUser().getUserId(), body, new DataService.Insert<PostModel>() {
            @Override
            public void onDataInserted(PostModel feedback) {
                mView.addPost(feedback);
            }
        });
    }
}
