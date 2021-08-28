package com.nechaev.calculator.model.validator;

import com.nechaev.calculator.model.tokens.TokenType;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ExpressionRules {
    public static Set<TokenType> afterNUMBER = new HashSet<>(Arrays.asList(TokenType.NUMBER,
                                                             TokenType.OPERATION,
                                                             TokenType.COMMA,
                                                             TokenType.BRACKET_CLOSE));

    public static Set<TokenType> afterOPERATION = new HashSet<>(Arrays.asList(TokenType.NUMBER,
                                                                TokenType.BRACKET_OPEN));

    public static Set<TokenType> afterBRACKET_OPEN = new HashSet<>(Collections.singletonList(TokenType.NUMBER));

    public static Set<TokenType> afterBRACKET_CLOSE = new HashSet<>(Collections.singletonList(TokenType.OPERATION));

    public static Set<TokenType> afterDOT = new HashSet<>(Collections.singletonList(TokenType.NUMBER));
}
