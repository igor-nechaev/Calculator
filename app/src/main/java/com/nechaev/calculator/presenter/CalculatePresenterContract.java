package com.nechaev.calculator.presenter;

import android.view.View;

public interface CalculatePresenterContract {
    void initButtons();
    boolean checkExpression(String newExpression);
    void calculateExpression();
    void onViewClicked(View view);
}
