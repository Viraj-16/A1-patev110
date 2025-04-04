package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public interface MazeCommand {
    void execute(MazeInfo context, ArrayList<ArrayList<String>> mazeGrid);
}
