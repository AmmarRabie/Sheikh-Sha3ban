package com.products.ammar.sheikhsha3ban.evaluation;


import com.products.ammar.sheikhsha3ban.IBaseActions;
import com.products.ammar.sheikhsha3ban.IBaseView;
import com.products.ammar.sheikhsha3ban.common.data.model.EvaluationModel;
import com.products.ammar.sheikhsha3ban.common.data.model.UserModel;

import java.util.HashMap;

/**
 * Created by AmmarRabie on 08/03/2018.
 */

interface EvaluationContract {


    /**
     * views methods implemented by fragment
     */
    interface Views extends IBaseView<Actions> {
        void showRatings(EvaluationModel rats);
    }


    /**
     * Actions methods implemented by presenter
     */
    interface Actions extends IBaseActions {
        void updateRating(int part, int quarter, int newRateRemember, int newRatePerformance);
    }
}