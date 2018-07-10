package com.products.ammar.sheikhsha3ban.attendance;

import android.support.v4.app.Fragment;

public class AdminAttendanceFragment extends Fragment implements AdminAttendanceContract.Views {

    private AdminAttendanceContract.Actions mActions;

    public static AdminAttendanceFragment newInstance() {
        return new AdminAttendanceFragment();
    }

    @Override
    public void setPresenter(AdminAttendanceContract.Actions presenter) {
        mActions = presenter;
    }
}
