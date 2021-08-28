package com.nechaev.calculator.historyScreen.presenter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.nechaev.calculator.R;
import com.nechaev.calculator.database.ExpressionHistoryModel;
import com.nechaev.calculator.database.DatabaseUtils;
import com.nechaev.calculator.historyScreen.view.HistoryViewContract;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HistoryPresenter implements HistoryPresenterContract{
    private final DatabaseUtils databaseUtils;
    private HistoryViewContract view;
    private final Handler uiHandler;
    private final Executor executor;

    public HistoryPresenter(Context context, HistoryViewContract view){
        databaseUtils = new DatabaseUtils(context);
        this.view = view;
        this.uiHandler = new Handler(Looper.myLooper());
        this.executor = Executors.newSingleThreadExecutor();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onViewClicked(int viewId) {
        switch (viewId){
            case R.id.imgbtn_delete:
                deleteAllData();
                break;
            case R.id.imgbtn_back:
                view.toCalculateView();
                break;

            default:
        }
    }

    @Override
    public void deleteAllData() {
        executor.execute(() ->
        {
            databaseUtils.deleteAll();
            uiHandler.post(() -> view.showHistory(new ArrayList<>()));
        });
    }

    @Override
    public void getExpressionHistory() {
        executor.execute(() ->
                {
                    ArrayList<ExpressionHistoryModel> data = databaseUtils.getData();
                    uiHandler.post(() -> view.showHistory(data));
                });
    }

    @Override
    public void onViewDetached() {
        view = null;
    }
}
