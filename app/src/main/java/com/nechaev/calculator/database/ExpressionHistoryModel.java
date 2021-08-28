package com.nechaev.calculator.database;

public class ExpressionHistoryModel {
    private final String expression;
    private final String result;

    public ExpressionHistoryModel(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }

    public String getExpression() {
        return expression;
    }

    public String getResult() {
        return result;
    }
}
