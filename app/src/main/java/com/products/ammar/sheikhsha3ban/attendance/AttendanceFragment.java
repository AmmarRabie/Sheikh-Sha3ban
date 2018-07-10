package com.products.ammar.sheikhsha3ban.attendance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.products.ammar.sheikhsha3ban.R;

public class AttendanceFragment extends Fragment implements AttendanceContract.Views {

    private AttendanceContract.Actions mActions;

    public static AttendanceFragment newInstance() {
        return new AttendanceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_attendance, container, false);

        return root;
    }

    @Override
    public void setPresenter(AttendanceContract.Actions presenter) {
        mActions = presenter;
    }
}
