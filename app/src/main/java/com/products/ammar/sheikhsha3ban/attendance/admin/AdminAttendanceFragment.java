package com.products.ammar.sheikhsha3ban.attendance.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.attendance.viewer.ViewerAttendanceActivity;
import com.products.ammar.sheikhsha3ban.common.data.model.UserDayAttendanceModel;
import com.products.ammar.sheikhsha3ban.common.data.model.UserModel;

import java.util.ArrayList;

/**
 * View all the users available and make the ui of attend them the current day
 * If user is clicked, go to the attendance activity as admin
 */
public class AdminAttendanceFragment extends Fragment implements AdminAttendanceContract.Views, UserAttendanceAdapter.OnItemClickListener {

    private AdminAttendanceContract.Actions mActions;

    // views
    private RecyclerView usersRecyclerView;
    private ArrayList<UserDayAttendanceModel> usersList;
    private UserAttendanceAdapter adapter;

    public static AdminAttendanceFragment newInstance() {
        return new AdminAttendanceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_attendance_admin, container, false);
        usersRecyclerView = root.findViewById(R.id.adminAttendanceFrag_list);

        usersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        usersList = new ArrayList<UserDayAttendanceModel>();
        adapter = new UserAttendanceAdapter(usersList, this);
        usersRecyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void setPresenter(AdminAttendanceContract.Actions presenter) {
        mActions = presenter;

    }

    @Override
    public void showUsers(ArrayList<UserDayAttendanceModel> userDayAttendanceModel) {
        usersList.clear();
        usersList.addAll(userDayAttendanceModel);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        mActions.start();
    }

    @Override
    public void onAttendanceChange(View view,UserModel user, int pos, boolean attend) {
        mActions.updateAttendance(user.getId(), attend);
    }

    @Override
    public void onUserClicked(View view, UserModel user, int pos) {
        Intent intent = new Intent(getContext(), ViewerAttendanceActivity.class);
        intent.putExtra(getString(R.string.IKey_userId), user.getId());
        intent.putExtra(getString(R.string.IKey_admin), true);
        startActivity(intent);
    }
}
