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

public class SecondSecondPart {
    private static int productOfMaxValues(String game) {
        int productOfMaxes = 1;
        int maxRed = 0;
        int maxGreen = 0;
        int maxBlue = 0;
        String[] gameSets = game.split(";");
        Pattern greenPattern = Pattern.compile("\\b(\\d+) green\\b");
        Pattern bluePattern = Pattern.compile("\\b(\\d+) blue\\b");
        Pattern redPattern = Pattern.compile("\\b(\\d+) red\\b");

        for (String gameSet : gameSets) {
            Matcher greenMatcher = greenPattern.matcher(gameSet);
            Matcher blueMatcher = bluePattern.matcher(gameSet);
            Matcher redMatcher = redPattern.matcher(gameSet);
            if (greenMatcher.find()) {
                maxGreen = Integer.max(maxGreen, Integer.parseInt(greenMatcher.group(1)));
            }
            if (blueMatcher.find()) {
                int maxBlueNumber = Integer.parseInt(blueMatcher.group(1));
                //System.out.println("maxBlue Number " + maxBlueNumber + " & " + maxBlue);
                maxBlue = Integer.max(maxBlue, maxBlueNumber);
            }
            if (redMatcher.find()) {
                maxRed = Integer.max(maxRed, Integer.parseInt(redMatcher.group(1)));
            }
        }
        productOfMaxes = maxRed * maxGreen * maxBlue;
        //System.out.println("max Red " + maxRed + ", maxGreen " + maxGreen + ", maxBlue " + maxBlue);
        //System.out.println("Product of maxes" + productOfMaxes);
        return productOfMaxes;
    }

    public static void main(String[] args) {
        int result = 0;
        Set<Integer> possibleGameIds = new HashSet<Integer>();
        Map<Integer, String[]> allGames = new HashMap<Integer, String[]>();
        Path filePath = Path.of("./src/main/java/org/example/2.1.txt");
        Pattern pattern = Pattern.compile("\\bGame \\d+: (.+)\\b");
        try {
            String inputFileString = Files.readString(filePath);
            String[] lines = inputFileString.split("\\n");
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    result += productOfMaxValues(matcher.group(1));
                }
            }
        } catch (IOException er) {
            System.out.println("Error reading File: " + er);
        }
        System.out.println(result);
    }
}
//public static void main(String[] args) {
//    int result = 0;
//    Set<Integer> possibleGameIds = new HashSet<Integer>();
//    Map<Integer, String[]> allGames = new HashMap<Integer, String[]>();
//    Path filePath = Path.of("./src/main/java/org/example/2.txt");
//    Pattern pattern = Pattern.compile("\\bGame (\\d+): (.+)\\b");
//    try {
//        String inputFileString = Files.readString(filePath);
//        String[] lines = inputFileString.split("\\n");
//        for (String line : lines) {
//            Matcher matcher = pattern.matcher(line);
//            if (matcher.find()) {
//                Integer gameId = Integer.parseInt(matcher.group(1));
//                String[] gameHistory = matcher.group(2).split(";");
//                allGames.put(gameId, gameHistory);
//            }
//        }
//    } catch (IOException er) {
//        System.out.println("Error reading File: " + er);
//    }
//    for (Map.Entry<Integer, String[]> entry : allGames.entrySet()) {
//        Integer key = entry.getKey();
//
//    }
//
//    for (Integer idGame : possibleGameIds) {
//        result += idGame;
//    }
//    System.out.println(result);
//}
