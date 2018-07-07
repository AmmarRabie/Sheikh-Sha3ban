package com.products.ammar.sheikhsha3ban.advice;

import com.products.ammar.sheikhsha3ban.common.data.DataService;
import com.products.ammar.sheikhsha3ban.common.data.model.AdviceModel;

import java.util.ArrayList;
import java.util.Collections;

public class AdvicePresenter implements AdviceContract.Actions {

    private AdviceContract.Views mView;
    private DataService data;

    public AdvicePresenter(AdviceContract.Views view, DataService source) {
        mView = view;
        mView.setPresenter(this);
        data = source;
    }

    @Override
    public void start() {
        data.getAllAdvice(new DataService.Get<ArrayList<AdviceModel>>() {
            @Override
            public void onDataFetched(ArrayList<AdviceModel> data) {
                Collections.reverse(data);
                mView.showAdvices(data);
            }
        });
    }


    @Override
    public void adviceOptionClicked(String id) {

    }
}
