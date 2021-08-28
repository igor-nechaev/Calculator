package com.nechaev.calculator;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nechaev.calculator.calculatorScreen.view.CalculatorFragment;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, CalculatorFragment.class, null).commit();
        }
    }
}