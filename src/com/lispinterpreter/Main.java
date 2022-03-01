package com.lispinterpreter;

import java.io.File;
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
            SimpleLinkedList<String> tokenList = LispParser.parseProgram(programString);

            // * 2. Parse the program (EVALUATE)
            VectorStack<String> data = new VectorStack<>();
            while(!tokenList.isEmpty()) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
