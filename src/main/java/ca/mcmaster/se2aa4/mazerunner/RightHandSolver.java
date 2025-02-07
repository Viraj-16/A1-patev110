package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class RightHandSolver implements SolveMaze {
    
    public String[] findPath(ArrayList<ArrayList<String>> mazeGrid, Position start, Position end) {
        StringBuilder rawPath = new StringBuilder();
        Position current = new Position(start.getX(), start.getY());
        Orientation facing = Orientation.RIGHT;

        while (!Position.areEqual(current, end)) {
            facing = determineNextMove(mazeGrid, current, facing, rawPath);
        }

        String factorizedPath = Factorize.factorizePath(rawPath.toString());
        return new String[]{rawPath.toString(), factorizedPath};
    }

    private Orientation determineNextMove(ArrayList<ArrayList<String>> mazeGrid, Position current, 
                                          Orientation facing, StringBuilder pathBuilder) {
        switch (facing) {
            case RIGHT:
                return moveInPreferredOrder(mazeGrid, current, pathBuilder, 
                        Orientation.DOWN, Orientation.RIGHT, Orientation.UP, Orientation.LEFT, "RF", "F", "LF", "RRF");
            case UP:
                return moveInPreferredOrder(mazeGrid, current, pathBuilder, 
                        Orientation.RIGHT, Orientation.UP, Orientation.LEFT, Orientation.DOWN, "RF", "F", "LF", "RRF");
            case DOWN:
                return moveInPreferredOrder(mazeGrid, current, pathBuilder, 
                        Orientation.LEFT, Orientation.DOWN, Orientation.RIGHT, Orientation.UP, "RF", "F", "LF", "RRF");
            case LEFT:
                return moveInPreferredOrder(mazeGrid, current, pathBuilder, 
                        Orientation.UP, Orientation.LEFT, Orientation.DOWN, Orientation.RIGHT, "RF", "F", "LF", "RRF");
            default:
                return facing;
        }
    }

    private Orientation moveInPreferredOrder(ArrayList<ArrayList<String>> mazeGrid, Position current, 
                                             StringBuilder pathBuilder, Orientation first, Orientation second, 
                                             Orientation third, Orientation reverse, String firstMove, String secondMove, 
                                             String thirdMove, String reverseMove) {
        if (canMove(mazeGrid, current, first)) {
            updatePosition(current, first);
            pathBuilder.append(firstMove);
            return first;
        } else if (canMove(mazeGrid, current, second)) {
            updatePosition(current, second);
            pathBuilder.append(secondMove);
            return second;
        } else if (canMove(mazeGrid, current, third)) {
            updatePosition(current, third);
            pathBuilder.append(thirdMove);
            return third;
        } else {
            updatePosition(current, reverse);
            pathBuilder.append(reverseMove);
            return reverse;
        }
    }

    private boolean canMove(ArrayList<ArrayList<String>> mazeGrid, Position current, Orientation direction) {
        int newX = current.getX() + (direction == Orientation.RIGHT ? 1 : direction == Orientation.LEFT ? -1 : 0);
        int newY = current.getY() + (direction == Orientation.DOWN ? 1 : direction == Orientation.UP ? -1 : 0);
        return !mazeGrid.get(newY).get(newX).equals("W");
    }

    private void updatePosition(Position current, Orientation direction) {
        current.setX(current.getX() + (direction == Orientation.RIGHT ? 1 : direction == Orientation.LEFT ? -1 : 0));
        current.setY(current.getY() + (direction == Orientation.DOWN ? 1 : direction == Orientation.UP ? -1 : 0));
    }
}
