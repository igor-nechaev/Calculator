package com.nechaev.calculator.core;

import static com.nechaev.calculator.model.ResourceConstants.CLOSE_BRACKET;
import static com.nechaev.calculator.model.ResourceConstants.DIVISION;
import static com.nechaev.calculator.model.ResourceConstants.DOT;
import static com.nechaev.calculator.model.ResourceConstants.MINUS;
import static com.nechaev.calculator.model.ResourceConstants.OPEN_BRACKET;
import static com.nechaev.calculator.model.ResourceConstants.PLUS;
import static com.nechaev.calculator.model.tokens.TokenType.BRACKET_CLOSE;
import static com.nechaev.calculator.model.tokens.TokenType.BRACKET_OPEN;
import static com.nechaev.calculator.model.tokens.TokenType.DELETED;
import static com.nechaev.calculator.model.tokens.TokenType.COMMA;
import static com.nechaev.calculator.model.tokens.TokenType.NUMBER;
import static com.nechaev.calculator.model.tokens.TokenType.OPERATION;

import com.nechaev.calculator.model.ResourceConstants;
import com.nechaev.calculator.model.tokens.NumberToken;
import com.nechaev.calculator.model.tokens.OperationToken;
import com.nechaev.calculator.model.tokens.Token;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

public class CoreCalculator {
    private Token [] tokenArray;
    private ArrayDeque<NumberToken> numberTokenStack;
    private ArrayDeque<OperationToken> operationStack;
    private ArrayDeque<Token> stack;
    private Stack<OperationToken> operationCurrentStack;
    Stack<NumberToken> numberCurrentStack;
    public CoreCalculator(Token[] tokenArray){
        this.tokenArray = tokenArray;
    }

    public String calculate(){
        unionNumbersToNumber();
        unionNumberToDouble();
        initStack();

        operationCurrentStack = new Stack<>();
        numberCurrentStack = new Stack<>();

        numberCurrentStack.push(numberTokenStack.pop());
        operationCurrentStack.push(operationStack.pop());
        stack.pop();
        stack.pop();

        while (!stack.isEmpty()) {
            if (Objects.requireNonNull(stack.peek()).type == OPERATION) {
                if (operationCurrentStack.isEmpty()) operationCurrentStack.push(getOperationToken());
                if (Objects.requireNonNull(stack.peek()).type == NUMBER) {
                    numberCurrentStack.push(getNumberToken());
                    if (stack.isEmpty()) break;
                }

                if (getOperationPriority(stack.peek()) <= getOperationPriority(operationCurrentStack.peek())) {
                    if (!isOperationCurrentEquals(OPEN_BRACKET)) {
                        if (numberCurrentStack.size() == 1) numberCurrentStack.push(getNumberToken());
                        numberCurrentStack.push(calc(numberCurrentStack.pop(), numberCurrentStack.pop(), operationCurrentStack.pop()));
                    } else operationCurrentStack.push(getOperationToken());
                } else if (isOperationStackEquals(OPEN_BRACKET)) {
                    operationCurrentStack.push(getOperationToken());
                } else if (isOperationStackEquals(CLOSE_BRACKET)) {
                    while (!isOperationCurrentEquals(OPEN_BRACKET)) {
                        numberCurrentStack.push(calc(numberCurrentStack.pop(), numberCurrentStack.pop(), operationCurrentStack.pop()));
                    }
                    stack.pop();
                    operationCurrentStack.pop();
                } else {
                    operationCurrentStack.push(getOperationToken());
                }
            } else {
                numberCurrentStack.push(getNumberToken());
            }
        }

        while (!operationCurrentStack.isEmpty()) {
            numberCurrentStack.push(calc(numberCurrentStack.pop(), numberCurrentStack.pop(), operationCurrentStack.pop()));
        }

        return numberCurrentStack.pop().getValue().replace(DOT, ResourceConstants.COMMA);
    }

    private void unionNumbersToNumber(){
        double currentNumer = 0;
        int digitCountInNumber = 0;
        for (int i = 0; i < tokenArray.length; i++) {
            if(tokenArray[i].type == NUMBER){
                digitCountInNumber++;
                currentNumer = currentNumer * 10 + Double.parseDouble(tokenArray[i].value);
            }else {
                if (digitCountInNumber > 1){
                    tokenArray[i-digitCountInNumber] = new NumberToken(String.valueOf(currentNumer));
                    for (int j = i-1; j > i-digitCountInNumber ; j--) {
                        tokenArray[j].type = DELETED;
                    }
                }
                digitCountInNumber = 0;
                currentNumer = 0;
            }

            if (i == tokenArray.length-1 && tokenArray[i].type == NUMBER && digitCountInNumber > 1){
                tokenArray[i-digitCountInNumber+1] = new NumberToken(String.valueOf(currentNumer));
                for (int j = i; j > i-digitCountInNumber + 1 ; j--) {
                    tokenArray[j].type = DELETED;
                }
            }
        }
        clearFromDeleteTokens();
    }

    private void unionNumberToDouble(){
        for (int i = 0; i < tokenArray.length; i++) {
            if(tokenArray[i].type == COMMA){
                double entier = Double.parseDouble(tokenArray[i-1].value);
                double mantissa = Double.parseDouble(tokenArray[i+1].value);
                double newDouble = entier + Double.parseDouble("0." + (int)mantissa);
                tokenArray[i-1] = new NumberToken(String.valueOf(newDouble));
                tokenArray[i].type = DELETED;
                tokenArray[i+1].type = DELETED;
            }
        }
        clearFromDeleteTokens();
    }

    private void initStack() {
        numberTokenStack = new ArrayDeque<>();
        operationStack = new ArrayDeque<>();
        stack = new ArrayDeque<>();

        for (int currentIndex = tokenArray.length - 1; currentIndex > -1; currentIndex--) {
            if (tokenArray[currentIndex].type != DELETED) {
                if(tokenArray[currentIndex].type == OPERATION ||
                        tokenArray[currentIndex].type == BRACKET_OPEN ||
                                tokenArray[currentIndex].type == BRACKET_CLOSE){
                    OperationToken newToken = new OperationToken(tokenArray[currentIndex].getValue().charAt(0));
                    operationStack.push(newToken);
                    stack.push(newToken);
                }else {
                    numberTokenStack.push((NumberToken) tokenArray[currentIndex]);
                    stack.push(tokenArray[currentIndex]);
                }
            }
        }
    }

    private NumberToken calc(NumberToken numberToken1, NumberToken numberToken2, OperationToken operationToken) {
        double double1 = Double.parseDouble(numberToken1.getValue());
        double double2 = Double.parseDouble(numberToken2.getValue());
        if (operationToken.getValue().equals(String.valueOf(PLUS))) {
            return new NumberToken(String.valueOf(double2 + double1));
        } else if (operationToken.getValue().equals(String.valueOf(MINUS))) {
            return new NumberToken(String.valueOf(double2 - double1));
        } else if (operationToken.getValue().equals(String.valueOf(DIVISION))) {
            return new NumberToken(String.valueOf(double2 / double1));
        }
        return new NumberToken(String.valueOf(double2 * double1));
    }

    private int getOperationPriority (Token token){
        return OperationToken.getPriority(token.value.charAt(0));
    }

    private OperationToken getOperationToken(){
        return new OperationToken(stack.pop().getValue().charAt(0));
    }


    private NumberToken getNumberToken(){
        return new NumberToken(stack.pop().value);
    }

    private boolean isOperationCurrentEquals(char operation){
        return operationCurrentStack.peek().getValue().equals(String.valueOf(operation));
    }

    private boolean isOperationStackEquals(char operation){
        return stack.peek().getValue().equals(String.valueOf(operation));
    }

    private void clearFromDeleteTokens(){
        List<Token> refreshList = Arrays.stream(tokenArray).
                filter(token -> token.type != DELETED).collect(Collectors.toList());
        Token [] refreshArray = new Token[refreshList.size()];
        for (int i = 0; i < refreshList.size(); i++) {
            refreshArray[i] = refreshList.get(i);
        }
        tokenArray = refreshArray;
    }

}
