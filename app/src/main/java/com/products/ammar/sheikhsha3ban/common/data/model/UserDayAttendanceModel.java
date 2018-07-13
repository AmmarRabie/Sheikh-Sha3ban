package com.products.ammar.sheikhsha3ban.common.data.model;

import java.util.HashMap;

/**
 * used in {@link com.products.ammar.sheikhsha3ban.attendance.viewer.ViewerAttendanceActivity}
 */
public class UserDayAttendanceModel extends UserModel{

    private UserMonthAttendanceModel monthAttendance;

    public UserDayAttendanceModel(UserModel user, UserMonthAttendanceModel monthAttendance) {
        super(user);
        this.monthAttendance = monthAttendance;
    }

    public UserMonthAttendanceModel getMonthAttendance() {
        return monthAttendance;
    }
}
