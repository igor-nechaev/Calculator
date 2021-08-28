package com.nechaev.calculator.historyScreen.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nechaev.calculator.R;
import com.nechaev.calculator.database.ExpressionHistoryModel;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{
    private ArrayList<ExpressionHistoryModel> listModel;

    public HistoryAdapter() {
        this.listModel = new ArrayList<>();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder{
        TextView tv_expression;
        TextView tv_result;
        HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_expression = itemView.findViewById(R.id.data_model_tv_expression);
            tv_result = itemView.findViewById(R.id.data_model_tv_result);
        }
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datamodel, parent, false);
        return new HistoryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final HistoryAdapter.HistoryViewHolder holder, final int position) {
        holder.tv_expression.setText(listModel.get(listModel.size() - position - 1).getExpression());
        holder.tv_result.setText("=" +listModel.get(listModel.size() - position - 1).getResult());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setListModel(ArrayList<ExpressionHistoryModel> listModel){
        this.listModel = listModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return listModel.size();
    }

}

