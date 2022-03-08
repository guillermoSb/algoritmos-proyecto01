package com.lispinterpreter;

import java.util.ArrayList;
import java.util.Arrays;

public final class LispParser {

    /**
     * Parse the LISP program into a list of tokens
     * @param program
     * @return
     */
    public static SimpleLinkedList<String> parseProgram(String program) {
        SimpleLinkedList<String> parsedProgram = new SimpleLinkedList<>();
        for (int i = 0; i < program.split("").length; i++) {
            if (!program.split("")[i].equals(" ")) {
                parsedProgram.add(program.split("")[i]);
                System.out.printf("Added to the list: %s \n", program.split("")[i] );
            }
        }
        return parsedProgram;

    }

    /**Método para separar por tokens en un ArrayLists */
    public static ArrayList<String> Separador(String program) {
        String[] programa= program.split("");
        ArrayList<String> tokensList= new ArrayList<String>(Arrays.asList(programa));
        System.out.println("The arraylist:" + tokensList);
        return tokensList;
            
    }
}
