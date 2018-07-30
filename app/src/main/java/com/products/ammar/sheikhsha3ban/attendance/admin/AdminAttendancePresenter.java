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
                for (int i = 0; i < data.size(); i++) {
                    String currId = data.get(i).getId();
                    if (currId.equals("PdOfzsGkRPPRfeS0oedPnC7Z4yy1") || currId.equals("9MzTSbtaP5SSa956loDD71u4RvT2")) {
                        data.remove(i);
                    }
                }
                mView.showUsers(data);
            }
        });
    }

    @Override
    public void updateAttendance(String id, boolean attend) {
        mData.setOneDayAttendanceForUser(id, DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay(), attend, null);
    }
}

