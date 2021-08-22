package com.nechaev.calculator.history.presenter;

import android.view.View;

import com.nechaev.calculator.database.DataModel;

import java.util.ArrayList;

public interface HistoryPresenterContract {
    void onViewClicked(View view);
    void onViewDetached();
    void deleteData();
    ArrayList<DataModel> getModelData();
}
