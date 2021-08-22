package com.nechaev.calculator.view;

public interface CalculateActivityContract {
    void setExpressionResult(String expressionResult);
    void setExpressionError(String expressionError);
    void attachPresenter();
    void detachPresenter();
    void updateExpression(String newExpression);
    String getExpressionString();
}
