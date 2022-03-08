package com.lispinterpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    View.showWelcomeMessage();  // Show welcome message
        try {
            // * 1. Load the program as a string (READ)
            File program = new File("program.lisp");
            Scanner scanner = new Scanner(program); // Scanner to load the file data
            String programString = "";

            // * 1.1 Go through all the lines on the program
            while(scanner.hasNextLine()) {
                programString = scanner.nextLine();
            }

            // * 2. Parse the program (EVALUATE)
            ArrayList<Node> tokenList = LispParser.separator(programString);

            //  * 3. Suse recursivinness to show list
            printTokens(tokenList, 0);
            // * 3. Print the result (PRINT)

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void printTokens(ArrayList<Node> tokens, int tabs) {

        while (!tokens.isEmpty()) {
            Node n = tokens.remove(0);
            if (n.tipo == 1) {
                for (int i = 0; i < tabs; i++) {
                    System.out.print("  ");
                }
                System.out.printf("%f \n", n.dataF);
            } else if (n.tipo == 2) {
                for (int i = 0; i < tabs; i++) {
                    System.out.print("  ");
                }
                System.out.printf("%s \n", n.dataS);
            } else if (n.tipo == 3) {
                printTokens(n.lista, tabs + 2);
            }
        }
    }
}
