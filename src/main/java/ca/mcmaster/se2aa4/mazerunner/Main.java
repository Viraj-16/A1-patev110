package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", "input", true, "Input maze file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            if (!cmd.hasOption("i")) {
                logger.error("Missing required option: -i <input file>");
                System.err.println("Usage: -i <input file>");
                return;
            }

            String inputFile = cmd.getOptionValue("i");
            logger.info("Starting Maze Runner with input file: " + inputFile);

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    StringBuilder lineRepresentation = new StringBuilder();
                    for (char c : line.toCharArray()) {
                        if (c == '#') {
                            lineRepresentation.append("WALL ");
                        } else if (c == ' ') {
                            lineRepresentation.append("PASS ");
                        }
                    }
                    logger.info(lineRepresentation.toString());
                }
            } catch (Exception e) {
                logger.error("An error occurred while reading the maze file.", e);
            }

            logger.info("** Computing path");
            logger.info("PATH NOT COMPUTED");
        } catch (ParseException e) {
            logger.error("Error parsing command-line arguments", e);
            System.err.println("Error parsing command-line arguments: " + e.getMessage());
        }

        logger.info("End of Maze Runner");
    }
}


// Represents a point in the maze
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
        // TODO: Implement right turn logic
        return this;
    }

    public Direction turnLeft() {
        // TODO: Implement left turn logic
        return this;
    }
}

// Maze representation class
class Maze {
    private char[][] grid;
    private Point entryPoint;
    private Point exitPoint;

    // Constructor to read maze from file
    public Maze(String inputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            
            // Convert to 2D grid
            grid = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                grid[i] = lines.get(i).toCharArray();
            }

            // TODO: Implement entry and exit point identification
            findEntryAndExit();
        } catch (Exception e) {
            throw new RuntimeException("Error reading maze file", e);
        }
    }

    // Method to find entry and exit points
    private void findEntryAndExit() {
        // TODO: Implement logic to find entry and exit points
    }

    // Utility methods
    public char getCell(Point p) {
        return grid[p.y][p.x];
    }

    public boolean isWall(Point p) {
        return getCell(p) == '#';
    }

    // Getters for entry and exit points
    public Point getEntryPoint() { return entryPoint; }
    public Point getExitPoint() { return exitPoint; }
}

// navigate the maze
class Explorer {
    private Point position;
    private Direction facing;
    private Maze maze;

    public Explorer(Maze maze) {
        this.maze = maze;
        this.position = maze.getEntryPoint();
        
        // TODO: Set initial facing direction based on entry point
    }

    public void turnRight() {
        // TODO: Implement right turn
    }

    public void turnLeft() {
        // TODO: Implement left turn
    }

    public boolean moveForward() {
        // TODO: Implement move forward logic
        // Check if next cell is a wall
        return false;
    }

    // Getters
    public Point getPosition() { return position; }
    public Direction getFacing() { return facing; }

    public boolean isAtExit() {
        // TODO: Implement exit check
        return false;
    }
}

// Path finding class
class PathFinder {
    private Maze maze;
    private Explorer explorer;

    public PathFinder(Maze maze, Explorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }

    public String findPath() {
        // TODO: Implement path finding algorithm
        // Placeholder implementation
        return "";
    }
}