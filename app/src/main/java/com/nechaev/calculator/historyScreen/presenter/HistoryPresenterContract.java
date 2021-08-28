package com.nechaev.calculator.historyScreen.presenter;


public interface HistoryPresenterContract {
    void onViewClicked(int viewId);
    void onViewDetached();
    void deleteAllData();
    void getExpressionHistory();
}
