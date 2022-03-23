package com.lispinterpreter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class LispInterpreter {

    static ArrayList<String> operators = new ArrayList<>(Arrays.asList("*", "+", "/", "-", "%"));

    /**
     * Evaluates a LISP expression and returns the output to the user (this is the main method)
     * @param tokens
     * @return
     */
    public Node processExpression(ArrayList<Node> tokens) {
        Node expResult = new Node(0);
        ArrayList<Node> flatToken = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).tipo != 3) {
                flatToken.add(tokens.get(i));
            } else {
                Node t = processExpression(tokens.get(i).lista);
                flatToken.add(t);
            }
        }
        for (int i = 0; i < flatToken.size(); i++) {
            if (i == 0 && flatToken.get(i).tipo == 2 && LispInterpreter.isOperator(flatToken.get(i).dataS)) {
                String operator = flatToken.remove(0).dataS;
                double result = arithmeticOperation(flatToken, operator);
                expResult = new Node(result);
            }
        }

        return expResult;   // Return the expected result
    }

    /**
     * Process arithmetic operation
     * @param tokens
     * @return
     */
    public Double arithmeticOperation(ArrayList<Node> tokens, String operator) {
        double result = operator.equals("+") || operator.equals("-") ? 0 : 1;
        for (int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).tipo != 1) {
                return null;    // Return null as an indicator of an issue
            };
            if (operator.equals("-") && result == 0) {
                result = tokens.get(i).dataF;
            } else if (operator.equals("-")) result -= tokens.get(i).dataF;

            if (operator.equals("+")) result += tokens.get(i).dataF;
            if (operator.equals("*")) result = result * tokens.get(i).dataF;
            if (operator.equals("/")) result = result / tokens.get(i).dataF;
        }
        return result;
    }

    /**
     * Checks if a value is an operator
     * @param value
     * @return
     */
    public static boolean isOperator(String value) {
        return operators.contains(value);
    }

    /**
     * Checks if a value is a number
     * @param value
     * @return
     */
    public static boolean isNumber(String value) {
        try {
            Double number = Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return  false;
        }
    }
}
