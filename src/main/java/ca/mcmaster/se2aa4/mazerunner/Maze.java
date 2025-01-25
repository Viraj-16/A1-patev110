package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

enum Direction {
    UP, RIGHT, DOWN, LEFT;

    public Direction turnRight() {
        switch (this) {
            case UP: return RIGHT;
            case RIGHT: return DOWN;
            case DOWN: return LEFT;
            case LEFT: return UP;
        }
        return this;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP: return LEFT;
            case LEFT: return DOWN;
            case DOWN: return RIGHT;
            case RIGHT: return UP;
        }
        return this;
    }
}

class Maze {
    private char[][] grid;
    private Point entryPoint;
    private Point exitPoint;

    public Maze(String inputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            grid = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                grid[i] = lines.get(i).toCharArray();
            }

            findEntryAndExit();
        } catch (Exception e) {
            throw new RuntimeException("Error reading maze file", e);
        }
    }

    private void findEntryAndExit() {
        for (int y = 0; y < grid.length; y++) {
            if (grid[y][0] == ' ') {
                entryPoint = new Point(0, y);
                break;
            }
        }
    
        for (int y = 0; y < grid.length; y++) {
            if (grid[y][grid[0].length - 1] == ' ') {
                exitPoint = new Point(grid[0].length - 1, y);
                break;
            }
        }
    }

    public char getCell(Point p) {
        return grid[p.y][p.x];
    }

    public boolean isWall(Point p) {
        return getCell(p) == '#';
    }

    public Point getEntryPoint() { return entryPoint; }

    public Point getExitPoint() { return exitPoint; }
}
