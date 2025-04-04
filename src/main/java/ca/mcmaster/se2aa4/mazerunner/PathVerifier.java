package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class PathVerifier {
    public static boolean[] validatePathFormat(String userPath) {
        boolean hasValidCharacters = userPath.matches("[FLR0-9 ]*");
        boolean isFactorized = userPath.matches(".*\\d.*");
        return new boolean[]{hasValidCharacters, isFactorized};
    }

    public static String verifyPath(String userPath, ArrayList<ArrayList<String>> mazeGrid, Position start, Position end) {
        userPath = userPath.replaceAll("\\s", "");
        if (!validatePathFormat(userPath)[0]) {
            return "incorrect";
        }

        if (simulatePath(userPath, mazeGrid, start, end) || simulatePath(userPath, mazeGrid, end, start)) {
            return "correct";
        }
        return "incorrect";
    }

    private static boolean simulatePath(String userPath, ArrayList<ArrayList<String>> mazeGrid, Position start, Position end) {
    // Determine initial orientation based on start and end positions
        Orientation initialOrientation = (start.getX() < end.getX()) ? Orientation.RIGHT : Orientation.LEFT;
        MazeInfo info = new MazeInfo(new Position(start.getX(), start.getY()), initialOrientation);
        
        // Parse the userPath into a list of commands
        List<MazeCommand> commands = CommandParser.parseCommands(userPath);
        
        // Execute each command sequentially
        for (MazeCommand command : commands) {
            command.execute(info, mazeGrid);
            if (Position.areEqual(info.getCurrentPosition(), end)) {
                return true;
            }
        }
        return false;
    }
}
