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
            SimpleLinkedList<String> tokenList = LispParser.parseProgram(programString);
            // * 3. Print the result (PRINT)
            while(!tokenList.isEmpty()) {
                System.out.println(tokenList.removeFirst());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
