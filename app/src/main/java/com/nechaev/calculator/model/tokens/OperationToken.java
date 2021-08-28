package com.nechaev.calculator.model.tokens;

import static com.nechaev.calculator.model.ResourceConstants.DIVISION;
import static com.nechaev.calculator.model.ResourceConstants.MINUS;
import static com.nechaev.calculator.model.ResourceConstants.MULTIPLY;
import static com.nechaev.calculator.model.ResourceConstants.PLUS;

public class OperationToken extends Token{
    private final String value;

    public OperationToken(String value){
        super(TokenType.OPERATION, value);
        this.value = value;
    }

    public OperationToken(char value){
        super(TokenType.OPERATION, String.valueOf(value));
        this.value = String.valueOf(value);
    }


    @Override
    public int getType() {
        return TokenType.OPERATION.getValue();
    }

    @Override
    public String getValue() {
        return value;
    }

    public static int getPriority(char value){
        switch (value) {
            case  PLUS:
            case MINUS:
                return 1;
            case MULTIPLY:
            case DIVISION:
                return 2;

            default: return 3;
        }
    }
}
