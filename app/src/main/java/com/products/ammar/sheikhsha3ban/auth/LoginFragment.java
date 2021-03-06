package com.products.ammar.sheikhsha3ban.auth;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.products.ammar.sheikhsha3ban.R;

import es.dmoral.toasty.Toasty;

/**
 * Created by AmmarRabie on 08/03/2018.
 */

public class LoginFragment extends Fragment implements LoginContract.Views, View.OnClickListener {

    ProgressDialog progressDialog;
    private LoginContract.Actions mAction;
    private EditText mEmail;
    private EditText mPassword;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void setPresenter(LoginContract.Actions presenter) {
        mAction = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_login, container, false);
        mEmail = root.findViewById(R.id.email);
        mPassword = root.findViewById(R.id.password);
        setHasOptionsMenu(true);

        root.findViewById(R.id.login).setOnClickListener(this);
//        root.findViewById(R.id.create_account).setOnClickListener(mSignUpListener);
        return root;
    }

    @Override
    public void showOnSuccess(String userName) {
        progressDialog.dismiss();
        final String helloMes = String.format(getString(R.string.FMT_welcomeBackMes), userName);
        Toasty.normal(getContext(), helloMes, Toast.LENGTH_SHORT).show();
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void showOnResetPasswordEmailSend() {
        progressDialog.dismiss();
        Toasty.info(getContext(), getString(R.string.mes_resetEmailSent),
                Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void showProgressIndicator(String progressWorkMessage) {
        if (progressDialog == null)
            progressDialog = ProgressDialog.show(getContext(),
                    null, progressWorkMessage
                    , true, false);
        else {
            progressDialog.setMessage(progressWorkMessage);
            progressDialog.show();
        }
    }

    @Override
    public void showErrorMessage(String cause) {
        if (progressDialog != null)
            progressDialog.dismiss();
        Toasty.error(getContext(), cause, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAction.start();
    }


    @Override
    public void onClick(View view) {
        mAction.login(mEmail.getText().toString(), mPassword.getText().toString());
    }
}