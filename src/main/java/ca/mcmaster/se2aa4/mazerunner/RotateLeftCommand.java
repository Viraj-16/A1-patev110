package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class RotateLeftCommand implements MazeCommand {
    @Override
    public void execute(MazeInfo info, ArrayList<ArrayList<String>> mazeGrid) {
        Orientation current = info.getCurrentOrientation();
        Orientation newOrientation = switch (current) {
            case UP -> Orientation.LEFT;
            case LEFT -> Orientation.DOWN;
            case DOWN -> Orientation.RIGHT;
            case RIGHT -> Orientation.UP;
        };
        info.setCurrentOrientation(newOrientation);
    }
}
