package com.nechaev.calculator.presenter;

import static com.nechaev.calculator.model.ResourceConstants.CLOSE_BRACKET;
import static com.nechaev.calculator.model.ResourceConstants.COMMA;
import static com.nechaev.calculator.model.ResourceConstants.DIVISION;
import static com.nechaev.calculator.model.ResourceConstants.EIGHT;
import static com.nechaev.calculator.model.ResourceConstants.FIVE;
import static com.nechaev.calculator.model.ResourceConstants.FOUR;
import static com.nechaev.calculator.model.ResourceConstants.MINUS;
import static com.nechaev.calculator.model.ResourceConstants.MULTIPLY;
import static com.nechaev.calculator.model.ResourceConstants.NINE;
import static com.nechaev.calculator.model.ResourceConstants.ONE;
import static com.nechaev.calculator.model.ResourceConstants.OPEN_BRACKET;
import static com.nechaev.calculator.model.ResourceConstants.PLUS;
import static com.nechaev.calculator.model.ResourceConstants.SEVEN;
import static com.nechaev.calculator.model.ResourceConstants.SIX;
import static com.nechaev.calculator.model.ResourceConstants.THREE;
import static com.nechaev.calculator.model.ResourceConstants.TWO;
import static com.nechaev.calculator.model.ResourceConstants.ZERO;

import android.annotation.SuppressLint;
import android.view.View;

import com.nechaev.calculator.R;
import com.nechaev.calculator.model.CalculatorModel;
import com.nechaev.calculator.model.ResourceConstants;
import com.nechaev.calculator.view.CalculateActivity;
import com.nechaev.calculator.view.CalculateActivityContract;

public class CalculatePresenter implements CalculatePresenterContractForView, CalculatePresenterContractForModel {
    private CalculateActivityContract view;
    private final CalculatorModel model;

    public CalculatePresenter(CalculateActivity view) {
        this.view = view;
        this.model = new CalculatorModel(this, view.getApplicationContext());
    }

    @Override
    public void calculateExpression(String expression) {
        model.calculate(expression);
    }

    private void backSpaceExpression(String expression) {
        if (expression.length() > 0) view.updateExpression(expression.substring(0,expression.length()-1));
    }

    private void newCharInput(Character character) {
        if (!view.getExpressionString().equals(ResourceConstants.ERROR)){
            view.updateExpression(view.getExpressionString() + character);
        }else{
            view.updateExpression(String.valueOf(character));
        }
    }

    @Override
    public void setExpressionResult(String expressionResult) {
        view.setExpressionResult(expressionResult);
    }

    @Override
    public void setExpressionError(String expressionError) {
        view.setExpressionError(expressionError);
    }

    private void clearExpression(){
        view.updateExpression("");
    }

    @Override
    public void onViewDetached() {
        view = null;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_zero:
                newCharInput(ZERO);
                break;
            case R.id.btn_one:
                newCharInput(ONE);
                break;
            case R.id.btn_two:
                newCharInput(TWO);
                break;
            case R.id.btn_three:
                newCharInput(THREE);
                break;
            case R.id.btn_four:
                newCharInput(FOUR);
                break;
            case R.id.btn_five:
                newCharInput(FIVE);
                break;
            case R.id.btn_six:
                newCharInput(SIX);
                break;
            case R.id.btn_seven:
                newCharInput(SEVEN);
                break;
            case R.id.btn_eight:
                newCharInput(EIGHT);
                break;
            case R.id.btn_nine:
                newCharInput(NINE);
                break;
            case R.id.btn_comma:
                newCharInput(COMMA);
                break;
            case R.id.btn_open_bracket:
                newCharInput(OPEN_BRACKET);
                break;
            case R.id.btn_close_bracket:
                newCharInput(CLOSE_BRACKET);
                break;
            case R.id.btn_plus:
                newCharInput(PLUS);
                break;
            case R.id.btn_minus:
                newCharInput(MINUS);
                break;
            case R.id.btn_multiply:
                newCharInput(MULTIPLY);
                break;
            case R.id.btn_division:
                newCharInput(DIVISION);
                break;
            case R.id.btn_clear:
                clearExpression();
                break;
            case R.id.btn_backspace:
                backSpaceExpression(this.view.getExpressionString());
                break;
            case R.id.btn_equals:
                calculateExpression(this.view.getExpressionString());
                break;
            default:

        }
    }
}
