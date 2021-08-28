package com.nechaev.calculator.calculateTest;
import org.junit.Test;

import static org.junit.Assert.*;

import com.nechaev.calculator.core.CoreCalculator;
import com.nechaev.calculator.model.ResourceConstants;
import com.nechaev.calculator.model.tokenizer.Tokenizer;

public class calculate {
    char MULTIPLY = ResourceConstants.MULTIPLY;
    char DIVISION = ResourceConstants.DIVISION;

    @Test
    public void calculate1(){
        Tokenizer tokenizer = new Tokenizer("(1+44)-2");
        CoreCalculator calcutate = new CoreCalculator(tokenizer.tokenize());
        assertEquals("43,0",calcutate.calculate());
    }

    @Test
    public void calculate2(){
        Tokenizer tokenizer = new Tokenizer("10" + MULTIPLY + "6");
        CoreCalculator calcutate = new CoreCalculator(tokenizer.tokenize());
        assertEquals("60,0",calcutate.calculate());
    }

    @Test
    public void calculate3(){
        Tokenizer tokenizer = new Tokenizer("1-(5*2+3)+23");
        CoreCalculator calcutate = new CoreCalculator(tokenizer.tokenize());
        assertEquals("11,0",calcutate.calculate());
    }



    @Test
    public void calculate4(){
        Tokenizer tokenizer = new Tokenizer("1-(5" + MULTIPLY + "2)+23");
        CoreCalculator calcutate = new CoreCalculator(tokenizer.tokenize());
        assertEquals("14,0",calcutate.calculate());
    }

    @Test
    public void calculate5(){
        Tokenizer tokenizer = new Tokenizer("(1+2)*4-4*2+23");
        CoreCalculator calcutate = new CoreCalculator(tokenizer.tokenize());
        assertEquals("27,0",calcutate.calculate());
    }

    @Test
    public void calculate6(){
        Tokenizer tokenizer = new Tokenizer("48,0" + DIVISION + "8");
        CoreCalculator calcutate = new CoreCalculator(tokenizer.tokenize());
        assertEquals("6,0",calcutate.calculate());
    }

    @Test
    public void calculate7(){
        Tokenizer tokenizer = new Tokenizer("48,0+4*5-(2*3+4)" + MULTIPLY + "8");
        CoreCalculator calcutate = new CoreCalculator(tokenizer.tokenize());
        assertEquals("-12,0",calcutate.calculate());
    }

}
