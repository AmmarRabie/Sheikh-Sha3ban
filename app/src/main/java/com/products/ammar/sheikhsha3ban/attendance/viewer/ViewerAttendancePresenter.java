package com.products.ammar.sheikhsha3ban.attendance.viewer;

import android.util.SparseBooleanArray;

import com.products.ammar.sheikhsha3ban.common.data.DataService;
import com.products.ammar.sheikhsha3ban.common.data.model.UserMonthAttendanceModel;
import com.products.ammar.sheikhsha3ban.common.util.DateUtil;

import java.util.Date;

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
        mView.markDateAsAttended(DateUtil.getDate(year, month, day));
    }

    @Override
    public void removeAttendance(int year, int month, int day) {
        mData.setOneDayAttendanceForUser(userId, year, month, day, false, new DataService.Update() {
            @Override
            public void onUpdateSuccess() {

            }
        });
        mView.removeDateAsAttended(DateUtil.getDate(year, month, day));
    }

/*    @Override
    public void getMonthAttendance(final int year, final int month) {
        mData.getMonthAttendance(userId, year, month, new DataService.Get<UserMonthAttendanceModel>() {
            @Override
            public void onDataFetched(UserMonthAttendanceModel data) {
                SparseBooleanArray attendance = data.getAttendance();
                for (int day = 0; day < attendance.size(); day++) {
                    boolean isAttend = attendance.get(day);
                    Date date = DateUtil.getDate(year, month, day + 1);
                    if (isAttend) mView.markDateAsAttended(date);
                    else mView.removeDateAsAttended(date);
                }
            }
        });
    }*/

    @Override
    public void getMonthAttendance(final int year, final int month) {
        mData.getMonthAttendance(userId, year, month, new DataService.Get<UserMonthAttendanceModel>() {
            @Override
            public void onDataFetched(UserMonthAttendanceModel data) {
                mView.changeDateAttendance(data, year, month);
            }
        });
    }

    @Override
    public void start() {

    }
}
