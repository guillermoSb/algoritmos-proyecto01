package com.lispinterpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Programa que simula un interprete de LISP.
 * @author Guillermo Santos
 * @author Ximena Loarca
 * @author Sofia Lam
 */
public class Main {

    public static void main(String[] args) {
        LispInterpreter interpreter = new LispInterpreter();    // Interpreter
        Scanner scan = new Scanner(System.in);
        String exp = "";    // Variable to store the user input

        View.showWelcomeMessage();  // Show welcome message

        while (true) {
            Boolean validExpression = false;    // Boolean indicating if the expression is valid
            System.out.print(">>> ");
            exp = scan.nextLine();
            if (exp.equals("CERRAR")) break;    // Finish the loop if the user decides to exit
            validExpression = validateExpression(exp);
            if (!validExpression) {
                System.out.println("La expresion ingresada no es valida");
                continue;
            }
            exp = cleanExpression(exp);
            Node result = interpreter.processExpression(LispParser.separator(exp));
            if (result.tipo == 1) {
                System.out.println(result.dataF); // Process the expression and show result
            } else if (result.tipo == 2) {
                System.out.println(result.dataS); // Process the expression and show result
            }

        }
    }

    /**
     * Validates a LISP expression
     * @param expression
     * @return boolean indicating if the expression is valid
     */
    public static boolean validateExpression(String expression) {
        // Count the number of opening and closing parents
        int openParents = 0;    // Count for opening parents
        int closingParents = 0; // Count for closing parents
        for (String character:
             expression.split("")) {
            if (character.equals("(")) openParents ++;
            if (character.equals(")")) closingParents ++;
        }
        if (openParents != closingParents) return false;    // Return false if the parentheses are not the same
        return true;
    }

    /**
     * Cleans a LISP expression
     * @param expression
     * @return String with a cleaned expression
     */
    public static String cleanExpression(String expression) {
        String newExpression = "";
        // Remove the first parentheses from the expression
        String[] splittedExpression = expression.trim().split("");
        for (int i = 0; i < splittedExpression.length; i++) {
            if (!(i == 0 && splittedExpression[i].equals("(")) &&
                    !(i == (splittedExpression.length - 1) && splittedExpression[i].equals(")"))) {
                newExpression += splittedExpression[i];
            }
        }
        newExpression = newExpression.replaceAll("\\(", " ( ");
        newExpression = newExpression.replaceAll("\\)", " ) ");
        newExpression = newExpression.replaceAll(" +", " ");    // Remove extra spaces
        return newExpression.trim();
    }
}
