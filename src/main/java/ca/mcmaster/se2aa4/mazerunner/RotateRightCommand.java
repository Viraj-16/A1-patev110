package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class RotateRightCommand implements MazeCommand {
    @Override
    public void execute(MazeInfo info, ArrayList<ArrayList<String>> mazeGrid) {
        Orientation current = info.getCurrentOrientation();
        Orientation newOrientation = switch (current) {
            case UP -> Orientation.RIGHT;
            case RIGHT -> Orientation.DOWN;
            case DOWN -> Orientation.LEFT;
            case LEFT -> Orientation.UP;
        };
        info.setCurrentOrientation(newOrientation);
    }
}
