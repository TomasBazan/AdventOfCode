package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Second {
    private static boolean isInvalidGame(String game) {
        boolean isInvalid = false;
        Pattern greenPattern = Pattern.compile("\\b(\\d+) green\\b");
        Pattern bluePattern = Pattern.compile("\\b(\\d+) blue\\b");
        Pattern redPattern = Pattern.compile("\\b(\\d+) red\\b");
        Matcher greenMatcher = greenPattern.matcher(game);
        Matcher blueMatcher = bluePattern.matcher(game);
        Matcher redMatcher = redPattern.matcher(game);
        boolean isGreenMatch = greenMatcher.find();
        boolean isBlueMatch = blueMatcher.find();
        boolean isRedMatch = redMatcher.find();
        if (isGreenMatch || isBlueMatch || isRedMatch) {
            int greenValue = 0;
            int blueValue = 0;
            int redValue = 0;
            if (isGreenMatch) {
                greenValue = Integer.parseInt(greenMatcher.group(1));
            }
            if (isBlueMatch) {
                blueValue = Integer.parseInt(blueMatcher.group(1));
            }
            if (isRedMatch) {
                redValue = Integer.parseInt(redMatcher.group(1));
            }
            if (greenValue > 13 || blueValue > 14 || redValue > 12) {
                isInvalid = true;
            }
        }
        return isInvalid;
    }

    public static void main(String[] args) {
        int result = 0;
        //List<Integer> possibleGameIds = new ArrayList<Integer>();
        Set<Integer> possibleGameIds = new HashSet<Integer>();
        Map<Integer, String[]> allGames = new HashMap<Integer, String[]>();
        Path filePath = Path.of("./src/main/java/org/example/2.txt");
        Pattern pattern = Pattern.compile("\\bGame (\\d+): (.+)\\b");
        try {
            String inputFileString = Files.readString(filePath);
            String[] lines = inputFileString.split("\\n");
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    Integer gameId = Integer.parseInt(matcher.group(1));
                    String[] gameHistory = matcher.group(2).split(";");
                    allGames.put(gameId, gameHistory);
                }
            }
        } catch (IOException er) {
            System.out.println("Error reading File: " + er);
        }
        for (Map.Entry<Integer, String[]> entry : allGames.entrySet()) {
            Integer key = entry.getKey();
            boolean isAddeable = true;
            for (String gameSet : entry.getValue()) {
                if (isInvalidGame(gameSet)) {
                    isAddeable = false;
                    break;
                }
            }
            if (isAddeable) {
                possibleGameIds.add(key);
            }
        }
        for (Integer idGame : possibleGameIds) {
            result += idGame;
        }
        System.out.println(result);
    }
}
