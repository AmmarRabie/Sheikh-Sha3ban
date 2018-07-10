package com.products.ammar.sheikhsha3ban.attendance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.auth.firebase.FirebaseAuthService;
import com.products.ammar.sheikhsha3ban.common.util.ActivityUtils;


public class AttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        boolean isAdmin = FirebaseAuthService.getInstance().getCurrentUser().getUserId().equals("userId");
        if (isAdmin) {
            AdminAttendanceFragment adminAttendanceFragment = (AdminAttendanceFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.contentFrame);


            if (adminAttendanceFragment == null) {
                adminAttendanceFragment = AdminAttendanceFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        adminAttendanceFragment, R.id.contentFrame);
            }

            // Create the presenter
            new AdminAttendancePresenter(adminAttendanceFragment);
        } else {
            AttendanceFragment attendanceFragment = (AttendanceFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.contentFrame);

            if (attendanceFragment == null) {
                attendanceFragment = AttendanceFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        attendanceFragment, R.id.contentFrame);
            }
            // Create the presenter
            new AttendancePresenter(attendanceFragment);
        }
    }
}
