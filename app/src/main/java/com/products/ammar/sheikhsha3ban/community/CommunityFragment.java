package com.products.ammar.sheikhsha3ban.community;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.auth.firebase.FirebaseAuthService;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseRepository;
import com.products.ammar.sheikhsha3ban.common.data.model.PostModel;

import java.util.ArrayList;

public class CommunityFragment extends Fragment implements CommunityContract.Views, PostsRecyclerAdapter.OnItemClickListener {

    private CommunityContract.Actions mPresenter;
    private RecyclerView listView;
    private PostsRecyclerAdapter adapter;
    private ArrayList<PostModel> communityList;

    public static CommunityFragment newInstance() {
        return new CommunityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_community, container, false);
        new CommunityPresenter(FirebaseAuthService.getInstance(), this, FirebaseRepository.getInstance());
        listView = root.findViewById(R.id.communityFrag_list);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        communityList = new ArrayList<>();
        adapter = new PostsRecyclerAdapter(communityList, this);
        listView.setAdapter(adapter);
        return root;
    }


    @Override
    public void showPosts(ArrayList<PostModel> posts) {
        this.communityList.clear();
        this.communityList.addAll(posts);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addPost(PostModel feedback) {
        communityList.add(0, feedback);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(CommunityContract.Actions presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void onSaveItemClick(View v, String newPostBody, int position) {
        mPresenter.insertPost(newPostBody);
    }
}

