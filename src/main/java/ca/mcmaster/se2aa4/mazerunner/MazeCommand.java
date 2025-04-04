package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public interface MazeCommand {
    void execute(MazeInfo info, ArrayList<ArrayList<String>> mazeGrid);
}
