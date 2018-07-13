package com.products.ammar.sheikhsha3ban.attendance.admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseRepository;
import com.products.ammar.sheikhsha3ban.common.util.ActivityUtils;


public class AdminAttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_admin);
        AdminAttendanceFragment adminAttendanceFragment = (AdminAttendanceFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (adminAttendanceFragment == null) {
            adminAttendanceFragment = AdminAttendanceFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    adminAttendanceFragment, R.id.contentFrame);
        }

        // Create the presenter
        new AdminAttendancePresenter(adminAttendanceFragment, FirebaseRepository.getInstance());
    }
}

