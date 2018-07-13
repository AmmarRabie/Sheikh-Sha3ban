package com.products.ammar.sheikhsha3ban.attendance.viewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseRepository;
import com.products.ammar.sheikhsha3ban.common.util.ActivityUtils;


public class ViewerAttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_viewer);
        String userId = getIntent().getStringExtra(getString(R.string.IKey_userId));
        if (userId == null) {
            throw new IllegalStateException("no key found for userId");
        }
        ViewerAttendanceFragment viewerAttendanceFragment = (ViewerAttendanceFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (viewerAttendanceFragment == null) {
            viewerAttendanceFragment = ViewerAttendanceFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    viewerAttendanceFragment, R.id.contentFrame);
        }
        // Create the presenter
        new ViewerAttendancePresenter(viewerAttendanceFragment
                , FirebaseRepository.getInstance()
                , userId
                , getIntent().getBooleanExtra(getString(R.string.IKey_admin), false));
    }
}
