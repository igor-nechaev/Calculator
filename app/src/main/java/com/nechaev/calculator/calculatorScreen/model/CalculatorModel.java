package com.nechaev.calculator.calculatorScreen.model;

import android.content.Context;

import com.nechaev.calculator.calculatorScreen.presenter.CalculatePresenterContractForModel;
import com.nechaev.calculator.core.CoreCalculator;
import com.nechaev.calculator.database.DatabaseUtils;
import com.nechaev.calculator.model.ResourceConstants;
import com.nechaev.calculator.model.tokenizer.Tokenizer;
import com.nechaev.calculator.model.tokenizer.TokenizerContract;
import com.nechaev.calculator.model.tokens.Token;

public class CalculatorModel implements CalculatorModelContract{
    private final CalculatePresenterContractForModel presenter;

    public CalculatorModel(CalculatePresenterContractForModel presenter) {
        this.presenter = presenter;
    }

    @Override
    public void calculate(String expression) {
        TokenizerContract tokenizer = new Tokenizer(expression);
        Token[] tokenArray;
        try {
            tokenArray = tokenizer.tokenize();
            CoreCalculator calcutate = new CoreCalculator(tokenArray);
            String resultExpression = calcutate.calculate();
            presenter.saveExpression(expression,resultExpression);
            presenter.setExpressionResult(resultExpression);
        } catch (NullPointerException exception) {
            presenter.setExpressionError(ResourceConstants.ERROR);
        }
    }
}