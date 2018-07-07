package com.products.ammar.sheikhsha3ban.community;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.data.model.PostModel;
import com.products.ammar.sheikhsha3ban.common.util.ProfileImageUtil;

import java.util.ArrayList;


public class PostsRecyclerAdapter extends RecyclerView.Adapter<PostsRecyclerAdapter.PostViewHolder> {

    private final static int VIEW_TYPE_CELL = 0;
    private final static int VIEW_TYPE_HEADER = 1;
    private OnItemClickListener mItemClickListener = null;
    private ArrayList<PostModel> mPosts;
    private Context mContext;

    public PostsRecyclerAdapter(ArrayList<PostModel> groups,
                                OnItemClickListener onEditClickListener) {
        this(groups);
        mItemClickListener = onEditClickListener;
    }


    public PostsRecyclerAdapter(ArrayList<PostModel> groupsList) {
        this.mPosts = groupsList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mItemClickListener = onItemClickListener;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_CELL) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_post, parent, false);
            return new PostViewHolder(view, VIEW_TYPE_CELL);

        } else if (viewType == VIEW_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_newpost, parent, false);
            return new PostViewHolder(view, VIEW_TYPE_HEADER);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        if (position == 0) {
            holder.bindFirstView(position);
            return;
        }
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mPosts.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? VIEW_TYPE_HEADER : VIEW_TYPE_CELL;
    }

    interface OnItemClickListener {
        void onSaveItemClick(View v, String newPostBody, int position);

//        void onBookmarkClicked(View view, String postId, int pos);
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView bodyView;
        private TextView creatorNameView;
        private TextView dateView;
        private ImageView creatorImageView;

        private EditText newPostView;
        private View saveView;

        PostViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == VIEW_TYPE_HEADER) {
                newPostView = itemView.findViewById(R.id.newPostItem_body);
                saveView = itemView.findViewById(R.id.newPostItem_save);
                return;
            }
            bodyView = itemView.findViewById(R.id.postItem_body);
            creatorNameView = itemView.findViewById(R.id.postItem_name);
            dateView = itemView.findViewById(R.id.postItem_date);
            creatorImageView = itemView.findViewById(R.id.postItem_profileImage);
        }

        void bind(final int position) {

            final PostModel currPost = mPosts.get(position - 1);

            bodyView.setText(currPost.getBody());
            creatorNameView.setText(currPost.getCreator().getName());
            dateView.setText(currPost.getDate().toString());
            ProfileImageUtil.setProfileImage(currPost.getCreator().getProfileImage(), creatorImageView, 70);
        }

        void bindFirstView(final int position) {
            saveView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onSaveItemClick(view, newPostView.getText().toString(), position);
                }
            });
        }
    }
}
