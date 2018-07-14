package com.products.ammar.sheikhsha3ban.evaluation;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.auth.firebase.FirebaseAuthService;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseRepository;
import com.products.ammar.sheikhsha3ban.common.data.model.EvaluationModel;
import com.xw.repo.BubbleSeekBar;

/**
 * Created by AmmarRabie on 08/03/2018.
 */

public class EvaluationFragment extends Fragment implements EvaluationContract.Views, EvaluationAdapter.OnEditClickListener {

    private static final String TAG = "EvaluationFragment";

    private EvaluationContract.Actions mAction;

    private ExpandableListView ratingListView;
    private EvaluationAdapter adapter;
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
        new EvaluationPresenter(FirebaseAuthService.getInstance(), FirebaseRepository.getInstance(), this);
        ratingListView = root.findViewById(R.id.evaluationFrag_list);
        return root;
    }

    @Override
    public void showRatings(EvaluationModel rats) {
        this.rats = rats;
        adapter = new EvaluationAdapter(getContext(), this.rats, this);
        ratingListView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        mAction.start();
    }

    @Override
    public void onEditClick(final int part, final int quarter) {
        new RatingDialog(getContext(), this.rats.getRememberRate(part, quarter), this.rats.getPerformanceRate(part, quarter), new RatingDialog.OnChangeListener() {
            @Override
            public void onChangeClick(int newRemember, int newPerformance) {
                mAction.updateRating(part, quarter, newRemember, newPerformance);
                rats.getRats()[part][quarter][EvaluationModel.REMEMBER_INDEX] = newRemember;
                rats.getRats()[part][quarter][EvaluationModel.PERFORMANCE_INDEX] = newPerformance;
                adapter.notifyDataSetChanged();
            }
        }).show();
    }


    private static class RatingDialog extends AlertDialog.Builder {

        private AlertDialog dialog;
        private BubbleSeekBar performanceBar;
        private BubbleSeekBar rememberBar;

        private int currRememberRate;
        private int currPerformanceRate;

        RatingDialog(@NonNull Context context, int currRememberRate, int currPerformanceRate, final OnChangeListener listener) {
            super(context);
            this.currPerformanceRate = currPerformanceRate;
            this.currRememberRate = currRememberRate;

            setView(R.layout.dialog_edit_rating);
            setTitle(R.string.dTitle_editRatings);
            setPositiveButton(R.string.dAction_change, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int newPerformance = performanceBar.getProgress() - 1;
                    int newRemember = rememberBar.getProgress() - 1;
                    listener.onChangeClick(newRemember, newPerformance);
                }
            }).setNegativeButton(R.string.dAction_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            dialog = create();
        }

        private void setupSeekBars() {
            performanceBar.setProgress(currPerformanceRate + 1);
            rememberBar.setProgress(currRememberRate + 1);
        }

        @Override
        public AlertDialog show() {
            dialog.show();
            performanceBar = dialog.findViewById(R.id.editRatingDialog_performanceRate);
            rememberBar = dialog.findViewById(R.id.editRatingDialog_rememberRate);
            setupSeekBars();
            return dialog;
        }

        public interface OnChangeListener {
            void onChangeClick(int newRemember, int newPerformance);
        }
    }
}