package com.nechaev.calculator.presenter;

import android.view.View;

public interface CalculatePresenterContractForView {
    void calculateExpression(String expression);
    void onViewClicked(View view);
    void onViewDetached();
}
