package com.nechaev.calculator.historyScreen.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.nechaev.calculator.R;
import com.nechaev.calculator.database.ExpressionHistoryModel;
import com.nechaev.calculator.historyScreen.adapter.HistoryAdapter;
import com.nechaev.calculator.historyScreen.presenter.HistoryPresenter;
import com.nechaev.calculator.historyScreen.presenter.HistoryPresenterContract;
import com.nechaev.calculator.calculatorScreen.view.CalculatorFragment;

import java.util.ArrayList;

public class HistoryFragment extends Fragment implements HistoryViewContract, View.OnClickListener{
    private RecyclerView rvHistory;
    private HistoryAdapter rvAdapter;
    private HistoryPresenterContract presenter;

    public HistoryFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        rvHistory = view.findViewById(R.id.rv_history);
        ImageButton imageButtonBack = view.findViewById(R.id.imgbtn_back);
        ImageButton imageButtonDelete = view.findViewById(R.id.imgbtn_delete);

        imageButtonBack.setOnClickListener(this);
        imageButtonDelete.setOnClickListener(this);

        initRecyclerView();
        getData();

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    @Override
    public void showHistory(ArrayList<ExpressionHistoryModel> data) {
        rvAdapter.setListModel(data);
    }

    private void getData(){
        presenter.getExpressionHistory();
    }

    @Override
    public void toCalculateView() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true).addToBackStack(null)
                .replace(R.id.fragment_container_view, CalculatorFragment.class, null).commit();
    }

    private void initRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvHistory.setLayoutManager(layoutManager);
        rvAdapter = new HistoryAdapter();
        rvHistory.setAdapter(rvAdapter);
    }

    private void attachPresenter(){
        presenter = new HistoryPresenter(getContext(),this);
    }


    @Override
    public void onClick(View view) {
        presenter.onViewClicked(view.getId());
    }

    @Override
    public void onDestroyView() {
        presenter.onViewDetached();
        super.onDestroyView();
    }
}