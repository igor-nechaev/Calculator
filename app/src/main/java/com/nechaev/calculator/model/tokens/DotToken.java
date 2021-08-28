package com.nechaev.calculator.model.tokens;

import static com.nechaev.calculator.model.ResourceConstants.COMMA;

public class DotToken extends Token{
    private final String value;

    public DotToken(){
        super(TokenType.COMMA, String.valueOf(COMMA));
        value = String.valueOf(COMMA);
    }
    @Override
    public int getType() {
        return TokenType.COMMA.getValue();
    }

    @Override
    public String getValue() {
        return value;
    }
}
