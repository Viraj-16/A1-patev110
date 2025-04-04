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


    private static Orientation executeMove(ArrayList<ArrayList<String>> mazeGrid, Position current, Orientation direction, char move) {
        switch (move) {
            case 'F':
                if (canAdvance(mazeGrid, current, direction)) {
                    updatePosition(current, direction);
                }
                break;
            case 'R':
                direction = rotateRight(direction);
                break;
            case 'L':
                direction = rotateLeft(direction);
                break;
        }
        return direction;
    }

    private static boolean canAdvance(ArrayList<ArrayList<String>> mazeGrid, Position current, Orientation direction) {
    int nextX = current.getX() + (direction == Orientation.RIGHT ? 1 : direction == Orientation.LEFT ? -1 : 0);
    int nextY = current.getY() + (direction == Orientation.DOWN ? 1 : direction == Orientation.UP ? -1 : 0);

    //bounds check
    if (nextY < 0 || nextY >= mazeGrid.size() || nextX < 0 || nextX >= mazeGrid.get(0).size()) {
        return false;
    }

    return !mazeGrid.get(nextY).get(nextX).equals("W");
}


    private static void updatePosition(Position current, Orientation direction) {
        current.setX(current.getX() + (direction == Orientation.RIGHT ? 1 : direction == Orientation.LEFT ? -1 : 0));
        current.setY(current.getY() + (direction == Orientation.DOWN ? 1 : direction == Orientation.UP ? -1 : 0));
    }

    private static Orientation rotateRight(Orientation direction) {
        return switch (direction) {
            case UP -> Orientation.RIGHT;
            case RIGHT -> Orientation.DOWN;
            case DOWN -> Orientation.LEFT;
            case LEFT -> Orientation.UP;
        };
    }

    private static Orientation rotateLeft(Orientation direction) {
        return switch (direction) {
            case UP -> Orientation.LEFT;
            case LEFT -> Orientation.DOWN;
            case DOWN -> Orientation.RIGHT;
            case RIGHT -> Orientation.UP;
        };
    }
}
