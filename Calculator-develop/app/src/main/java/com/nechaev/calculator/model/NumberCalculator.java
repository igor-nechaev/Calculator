package com.nechaev.calculator.model;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.Stack;

public class NumberCalculator {
    private static Stack<Double> doubleReverseStack;
    private static Stack<Character> operationReverseStack;
    private static Stack<Object> stack;

    public NumberCalculator() {
    }

    @SuppressLint({"Answer is rounding to 3 decimal places after", "DefaultLocale"})
    public String calculate(String inputString) {
        unboxInputString(inputString.replace(',', '.'));
        Stack<Double> doubleStack = new Stack<>();
        doubleStack.push(doubleReverseStack.pop());
        Stack<Character> operationStack = new Stack<>();
        operationStack.push(operationReverseStack.pop());
        stack.pop();
        stack.pop();

        while (!stack.isEmpty()) {
            if (stack.peek() instanceof Character) {
                if (operationStack.isEmpty()) operationStack.push((Character) stack.pop());
                if (!(stack.peek() instanceof Character)) {
                    doubleStack.push((Double) stack.pop());
                    if (stack.isEmpty()) break;
                }

                if (getPriority((Character) stack.peek()) <= getPriority(operationStack.peek())) {
                    if (!operationStack.peek().equals('(')) {
                        if (doubleStack.size() == 1) doubleStack.push((Double) stack.pop());
                        doubleStack.push(calc(doubleStack.pop(), doubleStack.pop(), operationStack.pop()));
                    } else operationStack.push((Character) stack.pop());
                } else if (stack.peek().equals('(')) {
                    operationStack.push((Character) stack.pop());
                } else if (stack.peek().equals(')')) {
                    while (!operationStack.peek().equals('(')) {
                        doubleStack.push(calc(doubleStack.pop(), doubleStack.pop(), operationStack.pop()));
                    }
                    stack.pop();
                    operationStack.pop();
                } else {
                    operationStack.push((Character) stack.pop());
                }
            } else {
                doubleStack.push((Double) stack.pop());
            }
        }

        while (!operationStack.isEmpty()) {
            doubleStack.push(calc(doubleStack.pop(), doubleStack.pop(), operationStack.pop()));
        }

        return formatAnswer(doubleStack.pop()).replace('.',',');
    }

    private String formatAnswer(Double doubleInput) {
        @SuppressLint("DefaultLocale") String doubleString = String.format("%.3f", doubleInput);
        doubleString = doubleString.replace(',','.');
        String afterDot = doubleString.split("\\.")[1];
        if (afterDot.equals("000")) {
            return doubleString.split("\\.")[0];
        } else if (afterDot.charAt(1) == '0' && afterDot.charAt(2) == '0') {
            return doubleString.split("\\.")[0] + '.' + afterDot.charAt(0);
        } else if (afterDot.charAt(2) == '0') {
            return doubleString.split("\\.")[0] + '.' + afterDot.charAt(0) + afterDot.charAt(1);
        } else return doubleString;

    }

    private void unboxInputString(String inputString) {
        char[] inputCharArray = chechMinusToDouble(inputString.toCharArray());
        inputString = new String(inputCharArray);
        doubleReverseStack = new Stack<>();
        operationReverseStack = new Stack<>();
        stack = new Stack<>();

        int lastIndexOfDigit = inputCharArray.length - 1;

        for (int i = inputCharArray.length - 1; i > -1; i--) {

            if (inputCharArray[i] == '.') {
                continue;
            }
            if (!Character.isDigit(inputCharArray[i])) {
                stack.push(inputCharArray[i]);
                operationReverseStack.push(inputCharArray[i]);
                if (i != 0 && Character.isDigit(inputCharArray[i - 1])) {
                    lastIndexOfDigit = i - 1;
                }
            } else if (i != 0 && !(Character.isDigit(inputCharArray[i - 1])) && inputCharArray[i - 1] != '.') {
                stack.push(Double.parseDouble(inputString.substring(i, lastIndexOfDigit + 1)));
                doubleReverseStack.push(Double.parseDouble(inputString.substring(i, lastIndexOfDigit + 1)));
            } else if (i == 0) {
                doubleReverseStack.push(Double.parseDouble(inputString.substring(i, lastIndexOfDigit + 1)));
                stack.push(Double.parseDouble(inputString.substring(i, lastIndexOfDigit + 1)));
            }
        }
    }



    private int getPriority(Character calcOperation) {
        switch (calcOperation) {
            case '+':
            case '-':
                return 1;
            case '×':
            case '÷':
                return 2;
        }
        return 3;
    }

    private Double calc(Double double2, Double double1, Character characterOperation) {
        switch (characterOperation) {
            case '+':
                return double1 + double2;
            case '-':
                return double1 - double2;
            case '÷':
                return double1 / double2;
        }
        return double1 * double2;
    }

    private char[] chechMinusToDouble(char[] charArray) {
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '-') {
                if (i == 0) {
                    list.add('0');
                    list.add(charArray[0]);
                    continue;
                }
            }
            list.add(charArray[i]);
        }
        charArray = new char[list.size()];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = list.get(i);
        }
        return charArray;
    }
}