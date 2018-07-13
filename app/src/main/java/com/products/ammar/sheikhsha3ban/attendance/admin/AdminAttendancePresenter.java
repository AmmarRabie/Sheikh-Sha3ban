package com.products.ammar.sheikhsha3ban.attendance.admin;

import com.products.ammar.sheikhsha3ban.common.data.DataService;
import com.products.ammar.sheikhsha3ban.common.data.model.UserDayAttendanceModel;
import com.products.ammar.sheikhsha3ban.common.util.DateUtil;

import java.util.ArrayList;

public class AdminAttendancePresenter implements AdminAttendanceContract.Actions {

    private AdminAttendanceContract.Views mView;
    private DataService mData;

    public AdminAttendancePresenter(AdminAttendanceContract.Views mView, DataService dataSource) {
        this.mView = mView;
        this.mData = dataSource;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mData.getDayAttendanceForAllUsers(new DataService.Get<ArrayList<UserDayAttendanceModel>>() {
            @Override
            public void onDataFetched(ArrayList<UserDayAttendanceModel> data) {
                mView.showUsers(data);
            }
        });
    }

    @Override
    public void updateAttendance(String id, boolean attend) {
        mData.setOneDayAttendanceForUser(id, DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay(), attend, null);
    }
}

