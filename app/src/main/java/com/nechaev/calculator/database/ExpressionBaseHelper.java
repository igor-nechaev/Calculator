package com.nechaev.calculator.database;

import static com.nechaev.calculator.database.ExpressionDbSchema.ExpressionTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExpressionBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "lastExpression.db";

    public ExpressionBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + ExpressionTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ExpressionTable.Cols.EXPRESSION + ", " +
                ExpressionTable.Cols.RESULT + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
