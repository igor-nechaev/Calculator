package com.nechaev.calculator.database;

public class ExpressionDbSchema {
    public static final class ExpressionTable {
        public static final String NAME = "lastExpressions";
        public static final class Cols {
            public static final String EXPRESSION = "expression";
            public static final String RESULT = "result";
        }
    }
}
