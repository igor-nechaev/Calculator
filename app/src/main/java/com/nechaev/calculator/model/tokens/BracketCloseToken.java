package com.nechaev.calculator.model.tokens;

import com.nechaev.calculator.model.ResourceConstants;

public class BracketCloseToken extends Token {
    private final String value;

    public BracketCloseToken(){
        super(TokenType.BRACKET_CLOSE, String.valueOf(ResourceConstants.CLOSE_BRACKET));
        this.value = String.valueOf(ResourceConstants.CLOSE_BRACKET);
    }

    @Override
    public int getType() {
        return TokenType.BRACKET_CLOSE.getValue();
    }

    @Override
    public String getValue() {
        return value;
    }

}
