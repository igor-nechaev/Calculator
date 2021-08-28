package com.nechaev.calculator.calculatorScreen.presenter;

public interface CalculatePresenterContractForModel {
    void setExpressionResult(String expressionResult);
    void setExpressionError(String expressionError);
    void saveExpression(String expression, String result);
}
