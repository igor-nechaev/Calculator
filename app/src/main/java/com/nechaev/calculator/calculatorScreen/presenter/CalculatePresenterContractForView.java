package com.nechaev.calculator.calculatorScreen.presenter;


public interface CalculatePresenterContractForView {
    void calculateExpression(String expression);
    void onViewClicked(int viewId);
    void onViewDetached();
}
