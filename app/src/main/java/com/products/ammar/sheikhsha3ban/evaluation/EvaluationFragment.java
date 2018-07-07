package com.products.ammar.sheikhsha3ban.evaluation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.auth.firebase.FirebaseAuthService;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseRepository;
import com.products.ammar.sheikhsha3ban.common.data.model.EvaluationModel;

/**
 * Created by AmmarRabie on 08/03/2018.
 */

public class EvaluationFragment extends Fragment implements EvaluationContract.Views {

    private static final String TAG = "EvaluationFragment";

    private EvaluationContract.Actions mAction;

    private ExpandableListView RatingListView;
    private ExpandableAdapter adapter;
    private EvaluationModel rats;

    public static EvaluationFragment newInstance() {
        return new EvaluationFragment();
    }

    @Override
    public void setPresenter(EvaluationContract.Actions presenter) {
        mAction = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_evaluation, container, false);
        new EvaluationPresenter(FirebaseAuthService.getInstance(), FirebaseRepository.getInstance(),this);
        RatingListView = root.findViewById(R.id.evaluationFrag_list);
        return root;
    }

    @Override
    public void showRatings(EvaluationModel rats) {
        adapter = new ExpandableAdapter(getContext(), rats);
        RatingListView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mAction.start();
    }
}