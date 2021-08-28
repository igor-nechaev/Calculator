package com.nechaev.calculator.model.validator;

import com.nechaev.calculator.model.tokens.Token;
import com.nechaev.calculator.model.tokens.TokenType;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class ExpressionСhecker {
    private Token [] tokenArray;
    private HashMap<TokenType, Set<TokenType>> validTokensAfterCurrent;

    public ExpressionСhecker(Token[] tokenArray) {
        this.tokenArray = tokenArray;
        validTokensAfterCurrent = new HashMap<>(){{
                put(TokenType.NUMBER, ExpressionRules.afterNUMBER);
                put(TokenType.OPERATION, ExpressionRules.afterOPERATION);
                put(TokenType.BRACKET_OPEN, ExpressionRules.afterBRACKET_OPEN);
                put(TokenType.BRACKET_CLOSE, ExpressionRules.afterBRACKET_CLOSE);
                put(TokenType.COMMA, ExpressionRules.afterDOT);

        }};
    }

    public boolean isValidExpression(){
        if(tokenArray[0].type == TokenType.OPERATION ||
           tokenArray[0].type == TokenType.BRACKET_CLOSE ||
           tokenArray[0].type == TokenType.COMMA) return false;

        if(tokenArray[tokenArray.length -1].type == TokenType.OPERATION ||
           tokenArray[tokenArray.length -1].type == TokenType.BRACKET_OPEN ||
           tokenArray[tokenArray.length -1].type == TokenType.COMMA) return false;

        for (int i = 0; i < tokenArray.length - 1; i++) {
            if (!Objects.requireNonNull(validTokensAfterCurrent.get(tokenArray[i].type)).
                    contains(tokenArray[i+1].type))
                return false;
        }
        return true;
    }
}
