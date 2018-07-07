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

public class AttendanceFragment extends Fragment {

    public static AttendanceFragment newInstance() {
        return new AttendanceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_attendance, container, false);
        root.findViewById(R.id.aaa).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String text = ((EditText) view).getText().toString();
                HashMap<String, String> values = new HashMap<>();
                values.put("body", text);
                values.put("date", "29/06/2018");
                values.put("creator", "RO2p1H6Cp0SyzZGF1iQzd1p8obh2");
                Random random = new Random();
                int id = random.nextInt();
                FirebaseDatabase.getInstance().getReference("advice").child("fromAndroid" + id).setValue(values);
                return true;
            }
        });
        return root;
    }
}
