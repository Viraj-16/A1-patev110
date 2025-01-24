package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
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