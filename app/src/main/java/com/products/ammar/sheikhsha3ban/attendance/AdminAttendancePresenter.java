package com.products.ammar.sheikhsha3ban.attendance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;
import com.products.ammar.sheikhsha3ban.R;

import java.util.HashMap;
import java.util.Random;

public class AdminAttendancePresenter{

    private AdminAttendanceContract.Views mView;

    public AdminAttendancePresenter(AdminAttendanceContract.Views mView) {
        this.mView = mView;
    }
}

