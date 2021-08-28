package com.nechaev.calculator.tokenizerTest;
import org.junit.Test;

import static org.junit.Assert.*;

import com.nechaev.calculator.model.tokenizer.Tokenizer;
import com.nechaev.calculator.model.tokens.TokenType;


public class typeOfChar {

    @Test
    public void token1() {
        Tokenizer tokenizer = new Tokenizer("1+3*(5+7)");
        assertEquals(TokenType.NUMBER, tokenizer.typeOfChar('0'));
    }

    @Test
    public void token2() {
        Tokenizer tokenizer = new Tokenizer("1+3*(5+7)");
        assertEquals(TokenType.BRACKET_CLOSE, tokenizer.typeOfChar(')'));
    }
    @Test
    public void token3() {
        Tokenizer tokenizer = new Tokenizer("1+3*(5+7)");
        assertEquals(TokenType.COMMA, tokenizer.typeOfChar(','));
    }
    @Test
    public void token4() {
        Tokenizer tokenizer = new Tokenizer("1+3*(5+7)");
        assertEquals(TokenType.OPERATION, tokenizer.typeOfChar('+'));
    }

    @Test
    public void token5() {
        Tokenizer tokenizer = new Tokenizer("1+3*(5+7)");
        assertEquals(TokenType.BRACKET_OPEN, tokenizer.typeOfChar('('));
    }
}
