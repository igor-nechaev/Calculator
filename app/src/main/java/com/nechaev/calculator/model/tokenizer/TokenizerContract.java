package com.nechaev.calculator.model.tokenizer;

import com.nechaev.calculator.model.tokens.Token;

public interface TokenizerContract {
    Token [] tokenize();
}
