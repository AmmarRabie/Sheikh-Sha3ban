package com.products.ammar.sheikhsha3ban.common.data.model;

import android.util.SparseBooleanArray;

/**
 * represents the attendance of one user in one month
 */
public class UserMonthAttendanceModel {
    private SparseBooleanArray attendance;

    public UserMonthAttendanceModel(SparseBooleanArray attendance) {
        this.attendance = attendance;
    }

    public SparseBooleanArray getAttendance() {
        return attendance;
    }

    public boolean getAttendance(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(String.format("day %s is an invalid day", day));
        }
        return attendance.get(day - 1);
    }
}
