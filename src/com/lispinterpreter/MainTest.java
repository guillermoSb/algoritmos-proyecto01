package com.lispinterpreter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    @Test
    public void testCleanExpression() {
        assertEquals("+ 4 4", Main.cleanExpression("( + 4 4 )"));
        assertEquals("( + 4 4 4 )", Main.cleanExpression("(( + 4 4 4 ))"));
        assertEquals("( + 4 4 4 )", Main.cleanExpression("(( + 4 4 4 ) )         "));
        assertEquals("( + 4 4 4 ( + 4 4 ) )", Main.cleanExpression("(( + 4 4 4 (+ 4 4) ))"));
    }
}
