package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class First {
    public static void main(String[] args) {
        int acum = 0;
        File inputText = new File("./src/main/java/org/example/1.txt");
        if(!inputText.exists()){
            System.out.println("File not found");
        }
        try {
            Scanner reader = new Scanner(inputText, StandardCharsets.UTF_8);
            while(reader.hasNextLine()){
                char firstNumber = '\u0000';
                char secondNumber = '\u0000';
                boolean firstNumberTaken = false;

                String line = reader.nextLine();
                for(int i = 0; i < line.length();i++){
                    if (Character.isDigit(line.charAt(i))) {
                        if (!firstNumberTaken) {
                            firstNumber = line.charAt(i);
                            secondNumber = firstNumber;
                            firstNumberTaken = true;
                        } else {
                            secondNumber = line.charAt(i);
                        }
                    }
                }

                String concatenateNumbers = String.valueOf(firstNumber)+String.valueOf(secondNumber);
                acum += Integer.parseInt(concatenateNumbers);
            }
            reader.close();
            System.out.println(acum);
        }catch (IOException e) {
            System.out.println(e);
        }
    }
}