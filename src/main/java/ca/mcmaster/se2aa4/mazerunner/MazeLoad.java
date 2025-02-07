package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeLoad {
    private static final Logger logger = LogManager.getLogger(MazeLoad.class);

    public static ArrayList<ArrayList<String>> loadMaze(String filename) {
        ArrayList<ArrayList<String>> mazeGrid = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                mazeGrid.add(parseMazeLine(line));
            }
        } catch (FileNotFoundException e) {
            logger.error("Maze file not found: " + filename);
        } catch (IOException e) {
            logger.error("Error reading the maze file: " + filename);
        }

        return mazeGrid;
    }

    private static ArrayList<String> parseMazeLine(String line) {
        ArrayList<String> row = new ArrayList<>();

        for (char cell : line.toCharArray()) {
            row.add(cell == '#' ? "W" : "P");
        }

        return row;
    }
}
