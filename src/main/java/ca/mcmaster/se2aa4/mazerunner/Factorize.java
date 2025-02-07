package ca.mcmaster.se2aa4.mazerunner;

public class Factorize {
    public static String factorizePath(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        
        StringBuilder factorizedPath = new StringBuilder();
        char prevChar = input.charAt(0);
        int count = 1;
        
        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == prevChar) {
                count++;
            } else {
                factorizedPath.append(formatSegment(prevChar, count));
                prevChar = currentChar;
                count = 1;
            }
        }
        factorizedPath.append(formatSegment(prevChar, count));
        
        return factorizedPath.toString().trim();
    }

    private static String formatSegment(char direction, int count) {
        return (count > 1) ? count + String.valueOf(direction) + " " : direction + " ";
    }

    public static String unfactorizePath(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        
        StringBuilder newPath = new StringBuilder();
        StringBuilder numberBuffer = new StringBuilder();
        
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                numberBuffer.append(c);
            } else {
                int repeatCount = numberBuffer.length() > 0 ? Integer.parseInt(numberBuffer.toString()) : 1;
                newPath.append(String.valueOf(c).repeat(repeatCount));
                numberBuffer.setLength(0);
            }
        }
        return newPath.toString();
    }
}
