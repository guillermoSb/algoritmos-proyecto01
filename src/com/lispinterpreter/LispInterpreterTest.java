package com.lispinterpreter;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class LispInterpreterTest {
    @Test
    public void testProcessExpression() {
        LispInterpreter interpreter = new LispInterpreter();    // Interpreter instance
        String expression = "+ 4 4";
        Double result = interpreter.processExpression(LispParser.separator(expression)).dataF;
        // Value should not be null
        Assert.assertNotNull(result);
        Assert.assertTrue(result == 8.0); // Result should be 4 + 4
    }

    @Test
    public void testComplexExpression() {
        LispInterpreter interpreter = new LispInterpreter();    // Interpreter instance
        String expression = "+ 4 4 ( * 3 2 )";
        double result = interpreter.processExpression(LispParser.separator(expression)).dataF;
        Assert.assertTrue(result == 14.0);
    }


    @Test
    public void testFunction() {
        LispInterpreter interpreter = new LispInterpreter();
        String expression = "(defun sum (n1 n2) ( + n1 n2))";
        expression = Main.cleanExpression(expression);
        Node result = interpreter.processExpression(LispParser.separator(expression));
        String expression2 = "(sum 10 10)";
        expression2 = Main.cleanExpression(expression2);
        Node result2 = interpreter.processExpression(LispParser.separator(expression2));
        Assert.assertTrue(result2.dataF == 20.0);
    }

}
