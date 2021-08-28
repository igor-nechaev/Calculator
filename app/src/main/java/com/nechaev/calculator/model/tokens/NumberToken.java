package com.nechaev.calculator.model.tokens;


public class NumberToken extends Token {
    private final String value;

    public NumberToken(char value){
        super(TokenType.NUMBER, String.valueOf(value));
        this.value = String.valueOf(value);
    }

    public NumberToken(String value){
        super(TokenType.NUMBER, value);
        this.value = value;
    }

    @Override
    public int getType() {
        return TokenType.NUMBER.getValue();
    }

    @Override
    public String getValue() {
        return value;
    }
}
