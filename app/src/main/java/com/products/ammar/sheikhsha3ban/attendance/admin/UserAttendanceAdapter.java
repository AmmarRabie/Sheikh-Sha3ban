package com.products.ammar.sheikhsha3ban.attendance.admin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.data.model.UserDayAttendanceModel;
import com.products.ammar.sheikhsha3ban.common.data.model.UserModel;
import com.products.ammar.sheikhsha3ban.common.util.DateUtil;
import com.products.ammar.sheikhsha3ban.common.util.ProfileImageUtil;

import java.util.ArrayList;

public class UserAttendanceAdapter extends RecyclerView.Adapter<UserAttendanceAdapter.UserAttendanceViewHolder> {

    private OnItemClickListener mItemClickListener = null;
    private ArrayList<UserDayAttendanceModel> mUsers;

    public UserAttendanceAdapter(ArrayList<UserDayAttendanceModel> users,
                                 OnItemClickListener onEditClickListener) {
        this(users);
        mItemClickListener = onEditClickListener;
    }


    public UserAttendanceAdapter(ArrayList<UserDayAttendanceModel> users) {
        this.mUsers = users;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mItemClickListener = onItemClickListener;
    }

    @Override
    public UserAttendanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_attendance, parent, false);

        return new UserAttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAttendanceViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    interface OnItemClickListener {
        void onAttendanceChange(View view, UserModel user, int pos, boolean attend);

        void onUserClicked(View view, UserModel user, int pos);
    }

    class UserAttendanceViewHolder extends RecyclerView.ViewHolder {

        private TextView nameView;
        private ImageView profileImageView;
        private CheckBox attendanceView;

        UserAttendanceViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.userAttendanceItem_name);
            profileImageView = itemView.findViewById(R.id.userAttendanceItem_image);
            attendanceView = itemView.findViewById(R.id.userAttendanceItem_checkBox);
        }

        void bind(final int position) {

            final UserDayAttendanceModel currUser = mUsers.get(position);

            nameView.setText(currUser.getName());
            attendanceView.setChecked(currUser.getMonthAttendance().getAttendance(DateUtil.getCurrentDay()));
            ProfileImageUtil.setProfileImage(currUser.getProfileImage(), profileImageView, 70);


            if (mItemClickListener == null)
                return;

            attendanceView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onAttendanceChange(view, currUser, position, attendanceView.isChecked());
                }
            });

            nameView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onUserClicked(view, currUser, position);
                }
            });
        }
    }
}
