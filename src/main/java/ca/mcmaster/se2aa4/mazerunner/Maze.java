package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.util.ArrayList;

enum Orientation {
    UP, RIGHT, DOWN, LEFT
}

public class Maze {
    private ArrayList<ArrayList<String>> grid;

    public Maze(String filePath) throws FileNotFoundException {
        this.grid = Reader.read(filePath);
    }

    public String validateUserPath(String userPath) {
        Coordinate[] entryPoints = locateEntryPoints();
        return Verifier.verifyPath(userPath, grid, entryPoints);
    }

    // Generates a path using the Right-Hand Rule
    public String[] generateSolutions() {
        Coordinate[] entryPoints = locateEntryPoints();
        MazeSolver solver = new RightHand();
        return solver.solveMaze(grid, entryPoints[0], entryPoints[1]);
    }

    // Identifies the maze entry and exit points
    private Coordinate[] locateEntryPoints() {
        return new Coordinate[]{findOpenPosition(0), findOpenPosition(grid.get(0).size() - 1)};
    }

    private Coordinate findOpenPosition(int columnIndex) {
        ArrayList<String> columnData = extractColumnData(columnIndex);
        for (int row = 0; row < columnData.size(); row++) {
            if (!columnData.get(row).equals("W")) {
                return new Coordinate(columnIndex, row);
            }
        }
        return new Coordinate(0, 0); // Default coordinate if no entry is found
    }

    private ArrayList<String> extractColumnData(int columnIndex) {
        ArrayList<String> columnData = new ArrayList<>();
        for (ArrayList<String> row : grid) {
            columnData.add(row.get(columnIndex));
        }
        return columnData;
    }
}
