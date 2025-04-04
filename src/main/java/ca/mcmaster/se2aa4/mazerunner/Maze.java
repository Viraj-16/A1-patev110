package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.util.ArrayList;

enum Orientation {
    UP, RIGHT, DOWN, LEFT
}

public class Maze {
    private ArrayList<ArrayList<String>> grid;
    private SolveMaze solver; 

    public Maze(String filePath, SolveMaze solver) throws FileNotFoundException {
        this.grid = MazeLoad.loadMaze(filePath);
        this.solver = solver;
    }

    public String validateUserPath(String userPath) {
        Position[] entryPoints = locateEntryPoints();
        return PathVerifier.verifyPath(userPath, grid, entryPoints[0], entryPoints[1]);
    }

    public String[] generateSolutions() {
        Position[] entryPoints = locateEntryPoints();
        return solver.findPath(grid, entryPoints[0], entryPoints[1]);
    }

    private Position[] locateEntryPoints() {
        return new Position[]{findOpenPosition(0), findOpenPosition(grid.get(0).size() - 1)};
    }

    private Position findOpenPosition(int columnIndex) {
        ArrayList<String> columnData = extractColumnData(columnIndex);
        for (int row = 0; row < columnData.size(); row++) {
            if (!columnData.get(row).equals("W")) {
                return new Position(columnIndex, row);
            }
        }
        return new Position(0, 0);
    }

    private ArrayList<String> extractColumnData(int columnIndex) {
        ArrayList<String> columnData = new ArrayList<>();
        for (ArrayList<String> row : grid) {
            columnData.add(row.get(columnIndex));
        }
        return columnData;
    }
}
