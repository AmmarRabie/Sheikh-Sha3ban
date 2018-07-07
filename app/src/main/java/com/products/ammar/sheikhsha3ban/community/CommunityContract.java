package com.products.ammar.sheikhsha3ban.community;

import com.products.ammar.sheikhsha3ban.IBaseActions;
import com.products.ammar.sheikhsha3ban.IBaseView;
import com.products.ammar.sheikhsha3ban.common.data.model.PostModel;

import java.util.ArrayList;

public interface CommunityContract {

    interface Actions extends IBaseActions {

        void insertPost(String body);
    }

    interface Views extends IBaseView<Actions> {
        void showPosts(ArrayList<PostModel> posts);

        void addPost(PostModel feedback);
    }

}
