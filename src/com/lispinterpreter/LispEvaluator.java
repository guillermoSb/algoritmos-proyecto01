package com.lispinterpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LispEvaluator {

    HashMap<String, String> functions = new HashMap<>();    // map containing functions
    ArrayList<String> operators = new ArrayList<String>(Arrays.asList("*", "+", "/", "-", "%"));    // operators allowed

    /**
     * Evaluates a LISP expression
     * @param expressions
     * @return
     */
    public String evaluate(ArrayList<Node> expressions) {

        boolean foundOperator = false;  // boolean indicating if the program found an operator

        while(!expressions.isEmpty()) {
            Node n = expressions.remove(0);
            // Evaluate the list
            if (n.tipo == 3) {
                evaluate(n.lista);
            }
            // Evaluate the operator
            if (n.tipo == 2) {
                foundOperator = operators.contains(n.dataS);    // Check if the data is an operator
            }
        }
        return "";
    }

}
