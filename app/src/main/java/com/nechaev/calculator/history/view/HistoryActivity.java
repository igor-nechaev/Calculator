package com.nechaev.calculator.history.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.nechaev.calculator.R;
import com.nechaev.calculator.history.adapter.HistoryAdapter;
import com.nechaev.calculator.history.presenter.HistoryPresenter;
import com.nechaev.calculator.history.presenter.HistoryPresenterContract;

public class HistoryActivity extends AppCompatActivity implements HistoryActivityContract, View.OnClickListener {
    RecyclerView rvHistory;
    private HistoryAdapter rvAdapter;
    ImageButton imageButtonBack, imageButtonDelete;
    HistoryPresenterContract presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPresenter();
        setContentView(R.layout.activity_history);
        initElements();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateExpressionsHistory();
    }

    @Override
    public void updateExpressionsHistory() {
        rvAdapter.setListModel(presenter.getModelData());
    }

    @Override
    public void goBack() {
        this.onBackPressed();
    }

    private void initRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvHistory.setLayoutManager(layoutManager);
        rvAdapter = new HistoryAdapter(presenter.getModelData());
        rvHistory.setAdapter(rvAdapter);
    }

    private void attachPresenter(){
        presenter = new HistoryPresenter(this,this);
    }

    private void initElements(){
        rvHistory = findViewById(R.id.rv_history);
        imageButtonBack = findViewById(R.id.imgbtn_back);
        imageButtonBack.setOnClickListener(this);
        imageButtonDelete = findViewById(R.id.imgbtn_delete);
        imageButtonDelete.setOnClickListener(this);
        initRecyclerView();
    }

    @Override
    public void onClick(View view) {
        presenter.onViewClicked(view);
    }

    @Override
    protected void onDestroy() {
        presenter.onViewDetached();
        super.onDestroy();
    }
}