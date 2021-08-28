package com.nechaev.calculator.model.tokens;

import static com.nechaev.calculator.model.ResourceConstants.OPEN_BRACKET;

public class BracketOpenToken extends Token {
    private final String value;

    public BracketOpenToken(){
        super(TokenType.BRACKET_OPEN, String.valueOf(OPEN_BRACKET));
        this.value = String.valueOf(OPEN_BRACKET);
    }

    @Override
    public int getType() {
        return TokenType.BRACKET_OPEN.getValue();
    }

    @Override
    public String getValue() {
        return value;
    }

}
