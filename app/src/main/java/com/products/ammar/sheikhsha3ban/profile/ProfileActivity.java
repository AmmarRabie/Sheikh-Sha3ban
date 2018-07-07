package com.products.ammar.sheikhsha3ban.profile;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.auth.firebase.FirebaseAuthService;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseRepository;
import com.products.ammar.sheikhsha3ban.common.util.ActivityUtils;

/**
 * The container of the fragment which implement the views
 * This should init the presenter and pass the instance to the fragment to do the tasks
 */
public class ProfileActivity extends AppCompatActivity {

    public static final int RESULT_SIGN_OUT = 256483;

    private ProfilePresenter mProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);


        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    profileFragment, R.id.contentFrame);
        }

        // Create the presenter
        mProfilePresenter = new ProfilePresenter(FirebaseAuthService.getInstance(),
                FirebaseRepository.getInstance(), profileFragment);
    }
}