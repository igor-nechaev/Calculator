package com.nechaev.calculator.model.tokens;

public enum TokenType {
    OPERATION(1),
    NUMBER(2),
    BRACKET_OPEN(3),
    BRACKET_CLOSE(4),
    COMMA(5),
    DELETED(6);


    private final int value;
    TokenType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
