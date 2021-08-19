package com.nechaev.calculator.presenter;

import android.annotation.SuppressLint;
import android.view.View;

import com.nechaev.calculator.R;
import com.nechaev.calculator.model.NumberCalculator;
import com.nechaev.calculator.view.CalculateActivity;

import java.util.Objects;

public class CalculatePresenter implements CalculatePresenterContract{
    private CalculateActivity view;
    private final NumberCalculator model;

    public CalculatePresenter(CalculateActivity view) {
        this.view = view;
        this.model = new NumberCalculator();
    }

    @Override
    public void initButtons() {
        view.tv_expression = view.findViewById(R.id.tv_expression);
        view.btn_zero = view.findViewById(R.id.btn_zero);
        view.btn_zero.setOnClickListener(this::onViewClicked);
        view.btn_one = view.findViewById(R.id.btn_one);
        view.btn_one.setOnClickListener(this::onViewClicked);
        view.btn_two = view.findViewById(R.id.btn_two);
        view.btn_two.setOnClickListener(this::onViewClicked);
        view.btn_three = view.findViewById(R.id.btn_three);
        view.btn_three.setOnClickListener(this::onViewClicked);
        view.btn_four = view.findViewById(R.id.btn_four);
        view.btn_four.setOnClickListener(this::onViewClicked);
        view.btn_five = view.findViewById(R.id.btn_five);
        view.btn_five.setOnClickListener(this::onViewClicked);
        view.btn_six = view.findViewById(R.id.btn_six);
        view.btn_six.setOnClickListener(this::onViewClicked);
        view.btn_seven = view.findViewById(R.id.btn_seven);
        view.btn_seven.setOnClickListener(this::onViewClicked);
        view.btn_eight = view.findViewById(R.id.btn_eight);
        view.btn_eight.setOnClickListener(this::onViewClicked);
        view.btn_nine = view.findViewById(R.id.btn_nine);
        view.btn_nine.setOnClickListener(this::onViewClicked);

        view.btn_backspace = view.findViewById(R.id.btn_backspace);
        view.btn_backspace.setOnClickListener(this::onViewClicked);
        view.btn_clear = view.findViewById(R.id.btn_clear);
        view.btn_clear.setOnClickListener(this::onViewClicked);
        view.btn_equals = view.findViewById(R.id.btn_equals);
        view.btn_equals.setOnClickListener(this::onViewClicked);
        view.btn_comma = view.findViewById(R.id.btn_comma);
        view.btn_comma.setOnClickListener(this::onViewClicked);

        view.btn_open_bracket = view.findViewById(R.id.btn_open_bracket);
        view.btn_open_bracket.setOnClickListener(this::onViewClicked);
        view.btn_close_bracket = view.findViewById(R.id.btn_close_bracket);
        view.btn_close_bracket.setOnClickListener(this::onViewClicked);

        view.btn_plus = view.findViewById(R.id.btn_plus);
        view.btn_plus.setOnClickListener(this::onViewClicked);
        view.btn_minus = view.findViewById(R.id.btn_minus);
        view.btn_minus.setOnClickListener(this::onViewClicked);
        view.btn_division = view.findViewById(R.id.btn_division);
        view.btn_division.setOnClickListener(this::onViewClicked);
        view.btn_multiply = view.findViewById(R.id.btn_multiply);
        view.btn_multiply.setOnClickListener(this::onViewClicked);
    }

    @Override
    public boolean checkExpression(String newExpression) {
        return false;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void calculateExpression() {
        view.tv_expression.setText("=" +
                model.calculate(Objects.requireNonNull(view.tv_expression.getText()).toString()));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_zero:
                newCharInput('0');
                break;
            case R.id.btn_one:
                newCharInput('1');
                break;
            case R.id.btn_two:
                newCharInput('2');
                break;
            case R.id.btn_three:
                newCharInput('3');
                break;
            case R.id.btn_four:
                newCharInput('4');
                break;
            case R.id.btn_five:
                newCharInput('5');
                break;
            case R.id.btn_six:
                newCharInput('6');
                break;
            case R.id.btn_seven:
                newCharInput('7');
                break;
            case R.id.btn_eight:
                newCharInput('8');
                break;
            case R.id.btn_nine:
                newCharInput('9');
                break;
            case R.id.btn_comma:
                newCharInput(',');
                break;
            case R.id.btn_open_bracket:
                newCharInput('(');
                break;
            case R.id.btn_close_bracket:
                newCharInput(')');
                break;
            case R.id.btn_plus:
                newCharInput('+');
                break;
            case R.id.btn_minus:
                newCharInput('-');
                break;
            case R.id.btn_multiply:
                newCharInput('×');
                break;
            case R.id.btn_division:
                newCharInput('÷');
                break;
            case R.id.btn_clear:
                clearExpression();
                break;
            case R.id.btn_backspace:
                backSpaceExpression();
                break;
            case R.id.btn_equals:
                equalsExpression();
                break;
                

        }
    }

    private void equalsExpression() {
        ///TODO проверка на пустоту и кореектность теперь уже при вычислении
        saveExpression();
        calculateExpression();

    }


    private void backSpaceExpression() {
        if(view.tv_expression.getText()!= null && view.tv_expression.getText().length() > 0){
            if(Character.isDigit(view.tv_expression.getText().toString().charAt(view.tv_expression.getText().length()-1)) &&
                countDigitsIfLast(view.tv_expression.getText().toString()) == 1){
                view.tv_expression.setText("");
            }else  view.tv_expression.setText(view.tv_expression.getText().delete(view.tv_expression.getText().length() - 1,
                    view.tv_expression.getText().length()).toString());

        }else {
            //TODO ошибочка тип но не критикал
        }
    }

    private void clearExpression() {
        view.tv_expression.setText("");
    }

    @SuppressLint("SetTextI18n")
    private void newCharInput(Character character) {
        String expression = view.tv_expression.getText().toString();
        if(canCharAdded(character)){
            if(character == '0' && expression.length() > 0 && !Character.isDigit(expression.charAt(expression.length()-1)) && expression.charAt(expression.length()-1) != ',')
                view.tv_expression.setText(view.tv_expression.getText().toString() + character + ',');
            else view.tv_expression.setText(view.tv_expression.getText().toString() + character);
        }else{
            errorCharAdded();
        }
    }

    private void errorCharAdded() {
    }

    private boolean canCharAdded(Character character) {
        String expression = view.tv_expression.getText().toString();
        if (expression.length() == 0) {
            if(character == '-') return true;
            return Character.isDigit(character) || character == '(' || character == ')'; // первый символ в строке цифра или скобка
        }
        char expressionLastChar = expression.charAt(expression.length() - 1);
        if (expression.contains("=")) {
            if (Character.isDigit(character) || character == '(') {  // после вычисления если ввести цифру или  откр. скобку, то пред операция исчезнет с экрана
                view.tv_expression.setText("");
                return true;
            } else if (character == ',' || character == ')') return false;
            else { // арифметическая операция нажата
                view.tv_expression.setText(expression.substring(1));// убираю равно и оставляю число
                return true;
            }
        }
        if (character == ',') return Character.isDigit(expressionLastChar);

        if (character == '(')
            return !(Character.isDigit(expressionLastChar) || expressionLastChar == ',');

        if (character == ')') return Character.isDigit(expressionLastChar);


        if (Character.isDigit(character)) return expressionLastChar != ')';


        if (expressionLastChar != '+' && expressionLastChar != '-' && expressionLastChar != '×' && expressionLastChar != '÷') {
        } else {
            view.tv_expression.setText(expression.substring(0, expression.length() - 1));
        }
        return true;
    }

    private int countDigitsIfLast(String expression){
        char [] array = expression.toCharArray();
        int t = 0;
        for (int i = 0; i < array.length; i++) {
            if(Character.isDigit(array[i])) t++;
        }
        return t;
    }

    private void saveExpression() {
        //TODO база данных
    }

}
