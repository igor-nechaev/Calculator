package com.nechaev.calculator.history.presenter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import com.nechaev.calculator.R;
import com.nechaev.calculator.database.DataModel;
import com.nechaev.calculator.database.DatabaseUtils;
import com.nechaev.calculator.history.view.HistoryActivityContract;

import java.util.ArrayList;

public class HistoryPresenter implements HistoryPresenterContract{
    private final DatabaseUtils databaseUtils;
    private HistoryActivityContract view;

    public HistoryPresenter(Context context, HistoryActivityContract view){
        databaseUtils = new DatabaseUtils(context);
        this.view = view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.imgbtn_delete:
                deleteData();
                break;
            case R.id.imgbtn_back:
                this.view.goBack();
                break;

            default:
        }
    }

    @Override
    public void deleteData() {
        databaseUtils.deleteAll();
        view.updateExpressionsHistory();
    }

    @Override
    public ArrayList<DataModel> getModelData() {
        return databaseUtils.getData();
    }

    @Override
    public void onViewDetached() {
        view = null;
    }

}
