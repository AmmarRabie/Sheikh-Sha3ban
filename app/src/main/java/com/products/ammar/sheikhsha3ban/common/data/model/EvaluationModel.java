package com.products.ammar.sheikhsha3ban.common.data.model;

import android.util.Log;

public class EvaluationModel {
    private static final String TAG = "EvaluationModel";

    public static final int REMEMBER_INDEX = 0;
    public static final int PERFORMANCE_INDEX = 1;
    private int rats[][][];

    public EvaluationModel(int[][][] rats) {
        this.rats = rats;
    }

    public int[] getRate(int part, int quarter) {
        if (part > 29 || quarter > 7 || quarter < 0 || part < 0) {
            Log.e(TAG, "getRate: parts should be from 0 to 29 and quarter from 0 to 7");
            throw new IllegalArgumentException("parts should be from 0 to 29 and quarter from 0 to 7");
        }
        return rats[part][quarter];
    }
}
