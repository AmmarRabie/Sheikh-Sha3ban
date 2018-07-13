package com.products.ammar.sheikhsha3ban.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.products.ammar.sheikhsha3ban.attendance.admin.AdminAttendanceActivity;
import com.products.ammar.sheikhsha3ban.common.auth.firebase.FirebaseAuthService;


public class AttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isAdmin = FirebaseAuthService.getInstance().getCurrentUser().getUserId().equals("PdOfzsGkRPPRfeS0oedPnC7Z4yy1");
        isAdmin = isAdmin || FirebaseAuthService.getInstance().getCurrentUser().getUserId().equals("9MzTSbtaP5SSa956loDD71u4RvT2");
        if (isAdmin) {
            Intent adminActivity = new Intent(this, AdminAttendanceActivity.class);
            startActivity(adminActivity);

        } else {
            Intent adminActivity = new Intent(this, AdminAttendanceActivity.class);
            startActivity(adminActivity);
        }
        finish();
    }
}
