package com.products.ammar.sheikhsha3ban.advice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.data.model.AdviceModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by Ammar Alsayed on 28/6/2018.
 */


class AdviceRecyclerAdapter extends RecyclerView.Adapter<AdviceRecyclerAdapter.AdviceViewHolder> {

    private OnItemClickListener mItemClickListener = null;
    private ArrayList<AdviceModel> mAdviceList;

    public AdviceRecyclerAdapter(ArrayList<AdviceModel> groups,
                                 OnItemClickListener onEditClickListener) {
        this(groups);
        mItemClickListener = onEditClickListener;
    }


    public AdviceRecyclerAdapter(ArrayList<AdviceModel> groupsList) {
        this.mAdviceList = groupsList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mItemClickListener = onItemClickListener;
    }

    @Override
    public AdviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_advice, parent, false);

        return new AdviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdviceViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mAdviceList.size();
    }

    public void swapList(ArrayList<AdviceModel> sessions) {
        if (mAdviceList != null) {
            mAdviceList.clear();
            mAdviceList.addAll(sessions);
            notifyDataSetChanged();
            return;
        }
        mAdviceList = new ArrayList<>(sessions);
        notifyDataSetChanged();
    }

    interface OnItemClickListener {
        void onOptionsClicked(View view, int pos, String id);
    }

    class AdviceViewHolder extends RecyclerView.ViewHolder {

        private TextView bodyView;
        private TextView creatorView;
        private TextView dateView;
        private View optionsView;

        AdviceViewHolder(View itemView) {
            super(itemView);
            bodyView = itemView.findViewById(R.id.adviceItem_body);
            creatorView = itemView.findViewById(R.id.adviceItem_creator);
            dateView = itemView.findViewById(R.id.adviceItem_dae);
            optionsView = itemView.findViewById(R.id.adviceItem_options);
        }

        void bind(final int position) {

            final AdviceModel currAdvice = mAdviceList.get(position);

            bodyView.setText(currAdvice.getBody());
            creatorView.setText(currAdvice.getCreator().getName());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String dateStr = simpleDateFormat.format(currAdvice.getDate());
            dateView.setText(dateStr);

            if (mItemClickListener == null)
                return;

            optionsView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onOptionsClicked(view, position, currAdvice.getId());
                }
            });
        }
    }
}

