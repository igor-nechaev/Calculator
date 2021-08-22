package com.nechaev.calculator.tokenizer;

import static com.nechaev.calculator.tokens.TokenType.NUMBER;

import com.nechaev.calculator.tokens.BracketCloseToken;
import com.nechaev.calculator.tokens.BracketOpenToken;
import com.nechaev.calculator.validator.ExpressionСhecker;
import com.nechaev.calculator.tokens.DotToken;
import com.nechaev.calculator.tokens.NumberToken;
import com.nechaev.calculator.tokens.OperationToken;
import com.nechaev.calculator.tokens.Token;
import com.nechaev.calculator.tokens.TokenType;


public class Tokenizer implements TokenizerContract {
    private final char [] charExpression;
    private Token [] tokenArray;

    public Tokenizer(String expression){
        this.charExpression = expression.toCharArray();
    }

    public Token [] tokenize(){
        toTokenArray();
        ExpressionСhecker checker = new ExpressionСhecker(tokenArray);
        if(checker.isValidExpression()){
            return tokenArray;
        }else{
            return null;
        }
    }

    public Token [] toTokenArray() {
        this.tokenArray = new Token[charExpression.length];

        for (int currentIndex = 0; currentIndex < charExpression.length; currentIndex++) {

            switch (typeOfChar(charExpression[currentIndex])){
                case NUMBER:
                    tokenArray[currentIndex] = new NumberToken(charExpression[currentIndex]);
                    break;

                case OPERATION:
                    tokenArray[currentIndex] = new OperationToken(charExpression[currentIndex]);
                    break;

                case BRACKET_OPEN:
                    tokenArray[currentIndex] = new BracketOpenToken();
                    break;

                case BRACKET_CLOSE:
                    tokenArray[currentIndex] = new BracketCloseToken();
                    break;

                default:
                    tokenArray[currentIndex] = new DotToken();
            }

        }

        return tokenArray;
    }

    public TokenType typeOfChar(char inputChar){
        if (inputChar >= 48 && inputChar <= 57) return NUMBER;
        if (inputChar == 40) return TokenType.BRACKET_OPEN;
        if (inputChar == 41) return TokenType.BRACKET_CLOSE;
        if (inputChar == 44) return TokenType.COMMA;
        return TokenType.OPERATION;
    }
}
