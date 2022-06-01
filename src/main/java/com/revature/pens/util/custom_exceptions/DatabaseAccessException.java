package com.revature.pens.util.custom_exceptions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DatabaseAccessException extends RuntimeException{
    public DatabaseAccessException(String message) {
        //super(message);
        super();
        appendFile(message);
    }

        private void appendFile(String message) {
            try {
                //File logFile = new File("log.txt");
                FileWriter fW = new FileWriter("src/main/resources/log.txt", true);
                BufferedWriter bf = new BufferedWriter(fW);
                bf.newLine();
                bf.append(message);
                bf.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
}
