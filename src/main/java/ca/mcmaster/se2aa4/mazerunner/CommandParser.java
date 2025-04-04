package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    public static List<MazeCommand> parseCommands(String userPath) {
        List<MazeCommand> commands = new ArrayList<>();
        for (char move : userPath.toCharArray()) {
            switch (move) {
                case 'F':
                    commands.add(new MoveForwardCommand());
                    break;
                case 'L':
                    commands.add(new RotateLeftCommand());
                    break;
                case 'R':
                    commands.add(new RotateRightCommand());
                    break;
                default:
                    break;
            }
        }
        return commands;
    }
}
