package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Second {
    private static final Map<String, Integer> numberHashed = new HashMap<String, Integer>(10);

    static {
        numberHashed.put("zero", 0);
        numberHashed.put("one", 1);
        numberHashed.put("two", 2);
        numberHashed.put("three", 3);
        numberHashed.put("four", 4);
        numberHashed.put("five", 5);
        numberHashed.put("six", 6);
        numberHashed.put("seven", 7);
        numberHashed.put("eight", 8);
        numberHashed.put("nine", 9);
    }

    private static Result startWithKey(String text, int index) {
        for (Map.Entry<String, Integer> entry : numberHashed.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if (text.startsWith(key, index)) {
                return new Result(true, key, value);
            }
        }
        return new Result(false, null, 0);
    }

    public static void main(String[] args) {
        int acum = 0;
        File inputText = new File("./src/main/java/org/example/1.1.txt");
        if (!inputText.exists()) {
            System.out.println("File not found");
        }
        try {
            Scanner reader = new Scanner(inputText, StandardCharsets.UTF_8);
            while (reader.hasNextLine()) {
                char firstNumber = '\u0000';
                char secondNumber = '\u0000';
                boolean firstNumberTaken = false;
                String line = reader.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    Result checkedLine = startWithKey(line, i);
                    if (Character.isDigit(line.charAt(i))) {
                        if (!firstNumberTaken) {
                            firstNumber = line.charAt(i);
                            secondNumber = firstNumber;
                            firstNumberTaken = true;
                        } else {
                            secondNumber = line.charAt(i);
                        }
                    } else if (checkedLine.startWithKey) {
                        if (!firstNumberTaken) {
                            firstNumber = (char) (checkedLine.value + '0');
                            secondNumber = firstNumber;
                            firstNumberTaken = true;
                        } else {
                            secondNumber = (char) (checkedLine.value + '0');
                        }
                    }
                }
                String concatenateNumbers = String.valueOf(firstNumber) + secondNumber;
                System.out.println(concatenateNumbers);
                acum += Integer.parseInt(concatenateNumbers);
            }
            reader.close();
            System.out.println(acum);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static class Result {
        public boolean startWithKey;
        public String key;
        public int value;

        public Result(boolean startWithKey, String key, int value) {
            this.startWithKey = startWithKey;
            this.key = key;
            this.value = value;
        }
    }
}