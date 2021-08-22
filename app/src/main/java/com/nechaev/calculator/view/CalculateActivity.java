package com.nechaev.calculator.view;

import static com.nechaev.calculator.model.ResourceConstants.TEXT_EXPRESSION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;
import com.nechaev.calculator.R;
import com.nechaev.calculator.history.view.HistoryActivity;
import com.nechaev.calculator.presenter.CalculatePresenter;
import com.nechaev.calculator.presenter.CalculatePresenterContractForView;

import java.util.Objects;

public class CalculateActivity extends AppCompatActivity implements CalculateActivityContract, View.OnClickListener {
    public TextInputEditText edtxExpression;
    public Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix,
            btnSeven, btnEight, btnNine;

    public Button btnOpenBracket, btnCloseBracket, btnDivision, btnMultiply,
            btnMinus, btnPlus, btnEquals, btnClear, btnComma;
    public ImageButton imgbtnBackspace, imgbtnHistory;

    private CalculatePresenterContractForView presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPresenter();
        setContentView(R.layout.activity_main);
        initButtons();
    }

    @Override
    public void onClick(View view) {
        presenter.onViewClicked(view);
    }

    @Override
    public void setExpressionResult(String expressionResult) {
        edtxExpression.setText(expressionResult);
    }

    @Override
    public void setExpressionError(String expressionError) {
        edtxExpression.setText(expressionError);
    }

    @Override
    public void attachPresenter() {
        presenter = new CalculatePresenter(this);
    }

    @Override
    public void detachPresenter() { presenter.onViewDetached(); }

    @Override
    public void updateExpression(String newExpression) {
        edtxExpression.setText(newExpression);
    }

    @Override
    public String getExpressionString() { return Objects.requireNonNull(edtxExpression.getText()).toString(); }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_EXPRESSION, Objects.requireNonNull(edtxExpression.getText()).toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        edtxExpression.setText(savedInstanceState.getString(TEXT_EXPRESSION));
    }

    @Override
    protected void onDestroy() {
        detachPresenter();
        super.onDestroy();
    }

    private void startHistoryActivity(View view){
        Intent historyIntent = new Intent(this, HistoryActivity.class);
        startActivity(historyIntent);
    }

    public void initButtons() {
        edtxExpression = findViewById(R.id.tv_expression);

        btnZero = findViewById(R.id.btn_zero);
        btnOne = findViewById(R.id.btn_one);
        btnTwo = findViewById(R.id.btn_two);
        btnThree = findViewById(R.id.btn_three);
        btnFour = findViewById(R.id.btn_four);
        btnFive = findViewById(R.id.btn_five);
        btnSix = findViewById(R.id.btn_six);
        btnSeven = findViewById(R.id.btn_seven);
        btnEight = findViewById(R.id.btn_eight);
        btnNine = findViewById(R.id.btn_nine);

        imgbtnBackspace = findViewById(R.id.btn_backspace);
        imgbtnHistory = findViewById(R.id.imgBtn_history);
        imgbtnHistory.setOnClickListener(this::startHistoryActivity);
        btnClear = findViewById(R.id.btn_clear);
        btnEquals = findViewById(R.id.btn_equals);
        btnComma = findViewById(R.id.btn_comma);

        btnOpenBracket = findViewById(R.id.btn_open_bracket);
        btnCloseBracket = findViewById(R.id.btn_close_bracket);

        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnDivision = findViewById(R.id.btn_division);
        btnMultiply = findViewById(R.id.btn_multiply);
    }
}