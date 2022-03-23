package com.lispinterpreter;

import org.junit.Assert;
import org.junit.Test;

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


}
