package com.products.ammar.sheikhsha3ban.evaluation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.data.model.EvaluationModel;

public class EvaluationAdapter extends BaseExpandableListAdapter {

    private final EvaluationModel RATS;
    private final Context context;
    private OnEditClickListener listener;

    public EvaluationAdapter(Context context, EvaluationModel rats, OnEditClickListener listener) {
        this.context = context;
        RATS = rats;
        this.listener = listener;
    }

    @Override
    public Object getChild(int partPosition, int quarterPosition) {
        return RATS.getRate(partPosition, quarterPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.listrow_rob3, null);
        }
        TextView name = convertView.findViewById(R.id.rob3Item_name);
        name.setText(childPosition + 1 + "");

        TextView rateKeeping = convertView.findViewById(R.id.rob3Item_rateRemembering);
        int rememberRate = RATS.getRate(groupPosition, childPosition)[EvaluationModel.REMEMBER_INDEX] + 1;
        rateKeeping.setText(rememberRate + "");

        TextView ratePerformance = convertView.findViewById(R.id.rob3Item_ratePerformance);
        int performanceRate = RATS.getRate(groupPosition, childPosition)[EvaluationModel.PERFORMANCE_INDEX] + 1;
        ratePerformance.setText(performanceRate + "");

        View editView = convertView.findViewById(R.id.rob3Item_editRating);
        editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEditClick(groupPosition, childPosition);
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 8; // every part of moshaf is 8 rob3
    }

    @Override
    public Object getGroup(int groupPosition) {
        Integer titleInt = groupPosition + 1;
        return titleInt.toString();
    }

    @Override
    public int getGroupCount() {
        return 30;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.listrow_part, null);
        }
        ((TextView) convertView.findViewById(R.id.partItem_name)).setText(groupPosition + 1 + "");
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public interface OnEditClickListener {
        void onEditClick(int part, int quarter);
    }
}