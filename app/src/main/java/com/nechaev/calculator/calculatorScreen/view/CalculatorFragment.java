package com.nechaev.calculator.calculatorScreen.view;

import static com.nechaev.calculator.model.ResourceConstants.TEXT_EXPRESSION;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;
import com.nechaev.calculator.R;
import com.nechaev.calculator.calculatorScreen.presenter.CalculatePresenter;
import com.nechaev.calculator.calculatorScreen.presenter.CalculatePresenterContractForView;
import com.nechaev.calculator.historyScreen.view.HistoryFragment;

public class CalculatorFragment extends Fragment implements CalculateViewContract, View.OnClickListener {

    public TextInputEditText edtxExpression;
    public ImageButton imgbtnBackspace, imgbtnHistory;

    private CalculatePresenterContractForView presenter;
    private View view;

    public CalculatorFragment() {
       super(R.layout.fragment_calculator);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPresenter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_calculator, container, false);
        initButtons();
        return view;
    }

    @Override
    public void onClick(View view) {
        presenter.onViewClicked(view.getId());
    }

    @Override
    public void setExpressionResult(String expressionResult) {
        edtxExpression.setText(expressionResult);
    }

    @Override
    public void setExpressionError(String expressionError) {
        edtxExpression.setText(expressionError);
    }

    private void attachPresenter() {
        presenter = new CalculatePresenter(this, getContext());
    }

    private void detachPresenter() { presenter.onViewDetached(); }

    @Override
    public void updateExpression(String newExpression) {
        edtxExpression.setText(newExpression);
    }

    @Override
    public String getExpressionString() {
        try {
            return edtxExpression.getText().toString();
        }catch (NullPointerException e){
            return "";
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_EXPRESSION, getExpressionString());
    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState!=null) edtxExpression.setText(savedInstanceState.getString(TEXT_EXPRESSION));

    }
    private void startHistoryFragment(){
        requireActivity().getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, HistoryFragment.class, null).commit();
    }
    public void initButtons() {
        int[] btnIds = {R.id.btn_zero, R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four, R.id.btn_five ,R.id.btn_six ,
                        R.id.btn_seven ,R.id.btn_eight , R.id.btn_nine, R.id.btn_open_bracket, R.id.btn_close_bracket,
                        R.id.btn_minus, R.id.btn_plus, R.id.btn_multiply, R.id.btn_division, R.id.btn_equals, R.id.btn_comma, R.id.btn_clear};
        for (int id: btnIds) {
            Button b = view.findViewById(id);
            b.setOnClickListener(this);
        }

        edtxExpression = view.findViewById(R.id.tv_expression);

        imgbtnBackspace = view.findViewById(R.id.btn_backspace);
        imgbtnBackspace.setOnClickListener(this);

        imgbtnHistory = view.findViewById(R.id.imgBtn_history);
        imgbtnHistory.setOnClickListener((v) -> startHistoryFragment());
    }

    @Override
    public void onDestroyView() {
        detachPresenter();
        super.onDestroyView();
    }
}