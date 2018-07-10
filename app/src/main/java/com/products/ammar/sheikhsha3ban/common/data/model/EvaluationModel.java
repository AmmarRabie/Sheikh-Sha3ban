package com.products.ammar.sheikhsha3ban.common.data.model;

import android.util.Log;

public class EvaluationModel {
    public static final int REMEMBER_INDEX = 0;
    public static final int PERFORMANCE_INDEX = 1;
    private static final String TAG = "EvaluationModel";
    private int rats[][][];

    public EvaluationModel(int[][][] rats) {
        // check only the first index
        boolean valid = rats.length == 30 && rats[0][0].length == 2 && rats[0].length == 8;
        if (!valid) {
            Log.e(TAG, "EvaluationModel: rats is badly formatted");
            throw new IllegalArgumentException("EvaluationModel: rats is badly formatted");
        }
        this.rats = rats;
    }

    public EvaluationModel(int[][] RememberRats, int[][] performanceRats) {
        int r[][][] = new int[30][8][2];

    }

    public int[][][] getRats() {
        return rats;
    }

    public int[] getRate(int part, int quarter) {
        if (!validateIndices(part, quarter, 0)) {
            Log.e(TAG, "getRate: parts should be from 0 to 29 and quarter from 0 to 7");
            throw new IllegalArgumentException("parts should be from 0 to 29 and quarter from 0 to 7");
        }
        return rats[part][quarter];
    }

    public int getPerformanceRate(int part, int quarter) {
        if (!validateIndices(part, quarter, 0)) {
            Log.e(TAG, "getRate: parts should be from 0 to 29 and quarter from 0 to 7");
            throw new IllegalArgumentException("parts should be from 0 to 29 and quarter from 0 to 7");
        }
        return rats[part][quarter][PERFORMANCE_INDEX];
    }

    public int getRememberRate(int part, int quarter) {
        if (!validateIndices(part, quarter, 0)) {
            Log.e(TAG, "getRate: parts should be from 0 to 29 and quarter from 0 to 7");
            throw new IllegalArgumentException("parts should be from 0 to 29 and quarter from 0 to 7");
        }
        return rats[part][quarter][REMEMBER_INDEX];
    }

    public static boolean validateIndices(int part, int quarter, int type) {
        return part <= 29 && quarter <= 7 && type <= 1 && type >= 0 && quarter >= 0 && part >= 0;
    }
}
