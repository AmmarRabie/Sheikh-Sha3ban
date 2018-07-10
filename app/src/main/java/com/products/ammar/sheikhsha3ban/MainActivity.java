package com.products.ammar.sheikhsha3ban;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.products.ammar.sheikhsha3ban.advice.AdviceFragment;
import com.products.ammar.sheikhsha3ban.attendance.AttendanceActivity;
import com.products.ammar.sheikhsha3ban.auth.LoginActivity;
import com.products.ammar.sheikhsha3ban.common.util.ActivityUtils;
import com.products.ammar.sheikhsha3ban.community.CommunityFragment;
import com.products.ammar.sheikhsha3ban.evaluation.EvaluationFragment;
import com.products.ammar.sheikhsha3ban.profile.ProfileActivity;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private static final int INTENT_REQUEST_LOGIN = 1;
    private static final int INTENT_REQUEST_PROFILE = 2;

    private static final String ADVICE_FRAG_TAG = "advice-frag";
    private static final String ATTENDANCE_FRAG_TAG = "attendance-frag";
    private static final String COMMUNITY_FRAG_TAG = "community-frag";
    private static final String EVALUATION_FRAG_TAG = "evaluation-frag";

    private String selectedTag;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_advice:
                if (!selectedTag.equals(ADVICE_FRAG_TAG))
                    loadAdviceFrag();
                return true;
            case R.id.nav_evaluation:
                if (!selectedTag.equals(EVALUATION_FRAG_TAG))
                    loadEvaluationFrag();
                return true;
            case R.id.nav_community:
                if (!selectedTag.equals(COMMUNITY_FRAG_TAG))
                    loadCommunityFrag();
                return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivityForResult(loginIntent, INTENT_REQUEST_LOGIN);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionMain_profile:
                Intent profileActivity = new Intent(this, ProfileActivity.class);
                startActivityForResult(profileActivity, INTENT_REQUEST_PROFILE);
                return true;
            case R.id.optionMain_attendance:
                Intent attendanceActivity = new Intent(this, AttendanceActivity.class);
                startActivity(attendanceActivity);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case INTENT_REQUEST_LOGIN:
                if (resultCode != RESULT_OK) {
                    // login cancelled, end the application
                    finish();
                    return;
                }
                loadAdviceFrag();
                break;
            case INTENT_REQUEST_PROFILE:
                if (resultCode == ProfileActivity.RESULT_SIGN_OUT) {
                    Intent loginIntent = new Intent(this, LoginActivity.class);
                    loginIntent.putExtra(getString(R.string.IKey_forceLogin), true);
                    startActivityForResult(loginIntent, INTENT_REQUEST_LOGIN);
                }
                break;
        }
    }


    private void loadAdviceFrag() {
        removeAllFrags();
        AdviceFragment frag = ((AdviceFragment) getSupportFragmentManager().findFragmentByTag(ADVICE_FRAG_TAG));
        if (frag == null) {
            frag = AdviceFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivityByTag(getSupportFragmentManager(), frag, R.id.contentFrame, ADVICE_FRAG_TAG);
        selectedTag = ADVICE_FRAG_TAG;
    }

    private void loadEvaluationFrag() {
        removeAllFrags();
        EvaluationFragment frag = ((EvaluationFragment) getSupportFragmentManager().findFragmentByTag(EVALUATION_FRAG_TAG));
        if (frag == null) {
            frag = EvaluationFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivityByTag(getSupportFragmentManager(), frag, R.id.contentFrame, EVALUATION_FRAG_TAG);
        selectedTag = EVALUATION_FRAG_TAG;
    }

    private void loadCommunityFrag() {
        removeAllFrags();
        CommunityFragment frag = ((CommunityFragment) getSupportFragmentManager().findFragmentByTag(COMMUNITY_FRAG_TAG));
        if (frag == null) {
            frag = CommunityFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivityByTag(getSupportFragmentManager(), frag, R.id.contentFrame, COMMUNITY_FRAG_TAG);
        selectedTag = COMMUNITY_FRAG_TAG;
    }

    private void removeAllFrags() {
        ActivityUtils.removeFragmentToActivityByTag(getSupportFragmentManager(), ATTENDANCE_FRAG_TAG);
        ActivityUtils.removeFragmentToActivityByTag(getSupportFragmentManager(), COMMUNITY_FRAG_TAG);
        ActivityUtils.removeFragmentToActivityByTag(getSupportFragmentManager(), ADVICE_FRAG_TAG);
        ActivityUtils.removeFragmentToActivityByTag(getSupportFragmentManager(), EVALUATION_FRAG_TAG);
    }
}
