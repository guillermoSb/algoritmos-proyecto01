package com.lispinterpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LispInterpreter {

    static ArrayList<String> operators = new ArrayList<>(Arrays.asList("*", "+", "/", "-", "%"));
    HashMap<String, Node> functions = new HashMap<>();
    HashMap<String, Node> parametters = new HashMap<>();

    /**
     * Evaluates a LISP expression and returns the output to the user (this is the main method)
     * @param tokens
     * @return
     */
    public Node processExpression(ArrayList<Node> tokens) {
        Node expResult = new Node(0);
        ArrayList<Node> flatToken = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).tipo == 2 && tokens.get(i).dataS.equals("defun")) {
                return new Node(saveFunction(tokens));
            } else if (tokens.get(i).tipo == 2 && parametters.containsKey(tokens.get(i).dataS)) {
                flatToken.add(parametters.get(tokens.get(i).dataS));
            } else if (tokens.get(i).tipo != 3) {
                flatToken.add(tokens.get(i));
            }  else {
                Node t = processExpression(tokens.get(i).lista);
                flatToken.add(t);
            }
        }
        for (int i = 0; i < flatToken.size(); i++) {
            // The list is a math expression
            if (i == 0 && flatToken.get(i).tipo == 2 && LispInterpreter.isOperator(flatToken.get(i).dataS)) {
                String operator = flatToken.remove(0).dataS;
                double result = arithmeticOperation(flatToken, operator);
                expResult = new Node(result);
            } else if (i == 0 && flatToken.get(i).tipo == 2 && functions.containsKey(flatToken.get(i).dataS)){
                // The list is a function
                expResult = processFunction(flatToken);
            }
        }

        return expResult;   // Return the expected result
    }

    /**
     * Method that processes functions
     * @param tokens
     * @return
     */
    public Node processFunction(ArrayList<Node> tokens) {
        ArrayList<Node> paramsList = new ArrayList<>();   // Array list to save params
        Node function = functions.get(tokens.get(0).dataS); // Get the function from the tokens
        tokens.remove(0);   // Remove function keyword
        for (Node token :
                tokens) {
            paramsList.add(token);
        }

        for (int i = 0; i < function.lista.size(); i++) {
            Node node = (Node) function.lista.get(i);
            if (node.tipo == 3 && i == 0) {
                for (int j = 0; j < node.lista.size(); j++) {
                    String param = ((Node) node.lista.get(j)).dataS;
                    parametters.put(param, paramsList.remove(0));
                }
            } else if (node.tipo == 3) {
                Node functionResult = processExpression(node.lista);
                return functionResult;
            }
        }
        return null;
    }

    /**
     * Method that saves a function
     * @param tokens
     * @return
     */
    public String saveFunction(ArrayList<Node> tokens) {
        String funcName = "";
        ArrayList<Node> funcContent = new ArrayList<>();
        // Get the function name
        for (int i = 0; i < tokens.size(); i++) {
            if (i == 0 && !tokens.get(i).dataS.equals("defun"))  break; // First word must be defun
            if (i == 1 && tokens.get(i).tipo != 2) break;  // Second value must be string
            if (i == 1) funcName = tokens.get(i).dataS; // Set the func name
            if (i > 1) funcContent.add(tokens.get(i));
        }
        // Save the function with that name
        functions.put(funcName, new Node(funcContent));
        // Return a node with the name of the function
        return funcName;
    }

    /**
     * Process arithmetic operation
     * @param tokens
     * @return
     */
    public Double arithmeticOperation(ArrayList<Node> tokens, String operator) {
        double result = operator.equals("+") || operator.equals("-") ? 0 : 1;
        for (int i = 0; i < tokens.size(); i++) {
            if (i == 0 && operator.equals("/")) {
                result = tokens.get(i).dataF;
                continue;
            }
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
