package com.products.ammar.sheikhsha3ban.attendance.viewer;

import com.products.ammar.sheikhsha3ban.common.data.DataService;

public class ViewerAttendancePresenter implements ViewerAttendanceContract.Actions {
    private ViewerAttendanceContract.Views mView;
    private DataService mData;
    private String userId;
    private boolean enableEdit;

    ViewerAttendancePresenter(ViewerAttendanceContract.Views mView, DataService dataSource, String userId, boolean enableEdit) {
        this.mView = mView;
        this.mData = dataSource;
        this.userId = userId;
        this.enableEdit = enableEdit;
        mView.setPresenter(this);
    }

    @Override
    public void addAttendanceOfDay(int year, int month, int day) {
        mData.setOneDayAttendanceForUser(userId, year, month, day, true, new DataService.Update() {
            @Override
            public void onUpdateSuccess() {

            }
        });
    }

    @Override
    public void removeAttendance(int year, int month, int day) {

    }

    @Override
    public void start() {

    }
}
