package com.nechaev.calculator.tokens;


public abstract class Token {
    public TokenType type;
    public String value;

    public Token(TokenType type, String value){
        this.type = type;
        this.value = value;
    }

    public abstract int getType();
    public abstract String getValue();

}
