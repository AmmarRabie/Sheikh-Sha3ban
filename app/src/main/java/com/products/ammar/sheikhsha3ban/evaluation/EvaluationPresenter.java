package com.products.ammar.sheikhsha3ban.evaluation;


import com.products.ammar.sheikhsha3ban.common.auth.AuthService;
import com.products.ammar.sheikhsha3ban.common.auth.AuthenticatedUser;
import com.products.ammar.sheikhsha3ban.common.data.DataService;
import com.products.ammar.sheikhsha3ban.common.data.model.EvaluationModel;

/**
 * Created by AmmarRabie on 08/03/2018.
 */

class EvaluationPresenter implements EvaluationContract.Actions {

    private static final String TAG = "EvaluationPresenter";
    private DataService mDataSource;

    private AuthService mAuthService;
    private EvaluationContract.Views mView;
    private AuthenticatedUser mCurrentUser;

    public EvaluationPresenter(AuthService authService, DataService dataSource, EvaluationContract.Views view) {
        this.mAuthService = authService;
        mDataSource = dataSource;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        // try to find the current user
        mCurrentUser = mAuthService.getCurrentUser();

        int[][][] rats = new int[30][8][2];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 2; k++) {
                    rats[i][j][k] = (i+1)*(j+1)*(k+1);
                }

            }
        }
        EvaluationModel model = new EvaluationModel(rats);
        mView.showRatings(model);
    }

}
