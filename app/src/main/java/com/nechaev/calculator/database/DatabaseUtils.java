package com.nechaev.calculator.database;

import static com.nechaev.calculator.database.ExpressionDbSchema.ExpressionTable.Cols.EXPRESSION;
import static com.nechaev.calculator.database.ExpressionDbSchema.ExpressionTable.Cols.RESULT;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseUtils {
    private final SQLiteDatabase mDatabse;

    public DatabaseUtils(Context context){
        Context mContext = context.getApplicationContext();
        mDatabse = new ExpressionBaseHelper(mContext).
                getWritableDatabase();
    }

    public void saveExpression(String expression, String result){
        mDatabse.insert(ExpressionDbSchema.ExpressionTable.NAME,
                null, getContentValues(expression, result));

    }

    public void deleteAll(){
        mDatabse.delete(ExpressionDbSchema.ExpressionTable.NAME,null,null);
    }

    @SuppressLint("Range")
    public ArrayList<ExpressionHistoryModel> getData(){
        Cursor cursor = mDatabse.rawQuery("select * from " + ExpressionDbSchema.ExpressionTable.NAME,null);
        ArrayList<ExpressionHistoryModel> expressionHistoryModelArrayList = new ArrayList<>();

        if (cursor.moveToFirst()){
            expressionHistoryModelArrayList.add(new ExpressionHistoryModel(cursor.getString(cursor.getColumnIndex(EXPRESSION)),
                                                cursor.getString(cursor.getColumnIndex(RESULT))));
            while(cursor.moveToNext()){
                expressionHistoryModelArrayList.add(new ExpressionHistoryModel(cursor.getString(cursor.getColumnIndex(EXPRESSION)),
                        cursor.getString(cursor.getColumnIndex(RESULT))));
            }
        }
        cursor.close();
        return expressionHistoryModelArrayList;
    }


    private ContentValues getContentValues(String expression, String result){
        ContentValues cv = new ContentValues();
        cv.put(EXPRESSION, expression);
        cv.put(RESULT, result);
        return cv;
    }
}
