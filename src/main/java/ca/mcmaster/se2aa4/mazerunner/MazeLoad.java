package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MazeLoad {
    public static ArrayList<ArrayList<String>> loadMaze(String filename) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            logger.error("Maze file not found: " + filename);
        } catch (IOException e) {
            logger.error("Error reading the maze file: " + filename);
        }
    
        // Determine the maximum row length
        int maxLen = 0;
        for (String l : lines) {
            maxLen = Math.max(maxLen, l.length());
        }
    
        ArrayList<ArrayList<String>> mazeGrid = new ArrayList<>();
        for (String l : lines) {
            String paddedLine = String.format("%-" + maxLen + "s", l);
            mazeGrid.add(parseMazeLine(paddedLine));
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

    private static final Logger logger = LogManager.getLogger(MazeLoad.class);

    
}
