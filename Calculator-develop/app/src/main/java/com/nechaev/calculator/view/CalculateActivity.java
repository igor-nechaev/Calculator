package com.nechaev.calculator.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;
import com.nechaev.calculator.R;
import com.nechaev.calculator.presenter.CalculatePresenter;

public class CalculateActivity extends AppCompatActivity implements CalculateActivityContract{
    public TextInputEditText tv_expression;
    public Button btn_zero, btn_one, btn_two, btn_three, btn_four, btn_five, btn_six,
                   btn_seven, btn_eight, btn_nine;

    public Button btn_open_bracket, btn_close_bracket, btn_division, btn_multiply,
                   btn_minus, btn_plus, btn_equals,btn_clear, btn_comma;
    public ImageButton btn_backspace;

    private CalculatePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPresenter();
        setContentView(R.layout.activity_main);
        initButtons();


    }



    @Override
    public void initButtons() {
        presenter.initButtons();
    }

    @Override
    public void attachPresenter() {
        presenter = new CalculatePresenter(this);
    }

    @Override
    public void detachPresenter() {
        presenter = null;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        detachPresenter();
        super.onDestroy();
    }
}