package com.products.ammar.sheikhsha3ban.evaluation;


import com.products.ammar.sheikhsha3ban.common.auth.AuthService;
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

    public EvaluationPresenter(AuthService authService, DataService dataSource, EvaluationContract.Views view) {
        this.mAuthService = authService;
        mDataSource = dataSource;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        // try to find the current user
        mDataSource.getUserRats(mAuthService.getCurrentUser().getUserId(), new DataService.Get<EvaluationModel>() {
            @Override
            public void onDataFetched(EvaluationModel data) {
                mView.showRatings(data);
            }
        });
    }

    @Override
    public void updateRating(final int part, final int quarter, int newRateRemember, final int newRatePerformance) {
        final String userId = mAuthService.getCurrentUser().getUserId();
        mDataSource.updateUserRate(userId, part, quarter, EvaluationModel.REMEMBER_INDEX, newRateRemember, new DataService.Update() {
            @Override
            public void onUpdateSuccess() {
                mDataSource.updateUserRate(userId, part, quarter, EvaluationModel.PERFORMANCE_INDEX, newRatePerformance, null);
            }
        });
    }
}
