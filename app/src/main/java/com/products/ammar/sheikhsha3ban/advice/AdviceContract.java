package com.products.ammar.sheikhsha3ban.advice;

import com.products.ammar.sheikhsha3ban.IBaseActions;
import com.products.ammar.sheikhsha3ban.IBaseView;
import com.products.ammar.sheikhsha3ban.common.data.model.AdviceModel;

import java.util.ArrayList;
import java.util.List;

public interface AdviceContract {

    interface Actions extends IBaseActions {

        void adviceOptionClicked(String id);
    }

    interface Views extends IBaseView<Actions> {
        void showAdvices(ArrayList<AdviceModel> adviceList);
    }

}
