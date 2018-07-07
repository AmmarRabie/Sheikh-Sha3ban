package com.products.ammar.sheikhsha3ban.auth;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.auth.firebase.FirebaseAuthService;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseRepository;
import com.products.ammar.sheikhsha3ban.common.util.ActivityUtils;

public class SignUpActivity extends AppCompatActivity {

    private SignUpContract.Actions mSignUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        SignUpFragment signUpFragment = (SignUpFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);


        if (signUpFragment == null) {
            signUpFragment = SignUpFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    signUpFragment, R.id.contentFrame);
        }

        // Create the presenter
        mSignUpPresenter = new SignUpPresenter(FirebaseAuthService.getInstance(),
                FirebaseRepository.getInstance(),signUpFragment);

    }
}
