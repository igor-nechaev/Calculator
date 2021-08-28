package com.nechaev.calculator.tokenizerTest;
import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import com.nechaev.calculator.model.tokenizer.Tokenizer;
import com.nechaev.calculator.model.tokens.Token;
import com.nechaev.calculator.model.tokens.TokenType;

import java.util.Arrays;

public class toTokenArray {
    @Test
    public void toTokenArray1() {
        Tokenizer tokenizer = new Tokenizer("1+3*(5+7)");
        Token [] tokenArray = tokenizer.toTokenArray();
        Log.d("Tokenizer", Arrays.toString(tokenArray));
        assertEquals(TokenType.NUMBER, tokenizer.typeOfChar('0'));
    }
}
