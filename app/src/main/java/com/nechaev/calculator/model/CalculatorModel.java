package com.nechaev.calculator.model;

import android.content.Context;

import com.nechaev.calculator.core.CoreCalculator;
import com.nechaev.calculator.database.DatabaseUtils;
import com.nechaev.calculator.tokenizer.Tokenizer;
import com.nechaev.calculator.tokenizer.TokenizerContract;
import com.nechaev.calculator.tokens.Token;
import com.nechaev.calculator.presenter.CalculatePresenterContractForModel;

public class CalculatorModel implements CalculatorModelContract{
    private final CalculatePresenterContractForModel presenter;
    private final DatabaseUtils databaseUtils;

    public CalculatorModel(CalculatePresenterContractForModel presenter, Context context) {
        this.presenter = presenter;
        this.databaseUtils = new DatabaseUtils(context);
    }

    @Override
    public void calculate(String expression) {
        TokenizerContract tokenizer = new Tokenizer(expression);
        Token[] tokenArray;
        try {
            tokenArray = tokenizer.tokenize();
            CoreCalculator calcutate = new CoreCalculator(tokenArray);
            String resultExpression = calcutate.calculate();
            save(expression,resultExpression);
            presenter.setExpressionResult(resultExpression);
        } catch (NullPointerException exception) {
            presenter.setExpressionError(ResourceConstants.ERROR);
        }
    }

    private void save(String expression, String result){
        databaseUtils.save(expression, result);
    }
}