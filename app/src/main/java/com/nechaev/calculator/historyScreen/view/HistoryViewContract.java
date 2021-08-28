package com.nechaev.calculator.historyScreen.view;

import com.nechaev.calculator.database.ExpressionHistoryModel;

import java.util.ArrayList;

public interface HistoryViewContract {
    void showHistory(ArrayList<ExpressionHistoryModel> data);
    void toCalculateView();
}
