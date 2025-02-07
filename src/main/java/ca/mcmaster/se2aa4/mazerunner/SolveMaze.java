package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public interface SolveMaze {
    String[] findPath(ArrayList<ArrayList<String>> mazeGrid, Position start, Position end);
}
