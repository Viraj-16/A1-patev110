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
        }
    }

    private void findEntryAndExit() {
        
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
