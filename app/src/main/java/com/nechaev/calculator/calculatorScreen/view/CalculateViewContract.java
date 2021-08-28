package com.nechaev.calculator.calculatorScreen.view;

public interface CalculateViewContract {
    void setExpressionResult(String expressionResult);
    void setExpressionError(String expressionError);
    void updateExpression(String newExpression);
    String getExpressionString();
}
