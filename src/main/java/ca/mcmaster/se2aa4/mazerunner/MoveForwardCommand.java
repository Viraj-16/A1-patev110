package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class MoveForwardCommand implements MazeCommand {
    @Override
    public void execute(MazeInfo info, ArrayList<ArrayList<String>> mazeGrid) {
        Position current = info.getCurrentPosition();
        Orientation direction = info.getCurrentOrientation();
        
        int newX = current.getX() + (direction == Orientation.RIGHT ? 1 : direction == Orientation.LEFT ? -1 : 0);
        int newY = current.getY() + (direction == Orientation.DOWN ? 1 : direction == Orientation.UP ? -1 : 0);
        
        if (newY >= 0 && newY < mazeGrid.size() &&
            newX >= 0 && newX < mazeGrid.get(0).size() &&
            !mazeGrid.get(newY).get(newX).equals("W")) {
            current.setX(newX);
            current.setY(newY);
        }
    }
}
