package com.products.ammar.sheikhsha3ban.advice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.products.ammar.sheikhsha3ban.R;
import com.products.ammar.sheikhsha3ban.common.data.firebase.FirebaseRepository;
import com.products.ammar.sheikhsha3ban.common.data.model.AdviceModel;

import java.util.ArrayList;

public class AdviceFragment extends Fragment implements AdviceContract.Views, AdviceRecyclerAdapter.OnItemClickListener {

    private AdviceContract.Actions mPresenter;
    private RecyclerView listView;
    private AdviceRecyclerAdapter adapter;
    private ArrayList<AdviceModel> adviceList;

    public static AdviceFragment newInstance() {
        return new AdviceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_advice, container, false);
        new AdvicePresenter(this, FirebaseRepository.getInstance());
        listView = root.findViewById(R.id.adviceFrag_list);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        adviceList = new ArrayList<>();
        adapter = new AdviceRecyclerAdapter(adviceList, this);
        listView.setAdapter(adapter);
        return root;
    }


    @Override
    public void showAdvices(ArrayList<AdviceModel> adviceList) {
        this.adviceList.clear();
        this.adviceList.addAll(adviceList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(AdviceContract.Actions presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void onOptionsClicked(View view, int pos, String id) {
        mPresenter.adviceOptionClicked(id);
    }

}

