package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            logger.info("** Starting Maze Solver");
            CommandLine cmd = parseArguments(args);
            String inputFile = cmd.getOptionValue("i");
            String userPath = cmd.getOptionValue("p", "");
            String method = cmd.getOptionValue("method", "righthand");

            // Use the factory to get the desired maze solver
            SolveMaze solver = MazeSolverFactory.getMazeSolver(method);
            Maze maze = new Maze(inputFile, solver);
            
            if (!userPath.isEmpty()) {
                validateAndCheckPath(maze, userPath);
            } else {
                String[] paths = maze.generateSolutions();
                System.out.println(paths[1]);
            }
            
            logger.info("** Maze Solver Completed");
        } catch (Exception e) {
            logger.error("An error occurred: ", e);
            System.exit(1);
        }
    }

    private static CommandLine parseArguments(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("i", true, "Maze input file");
        options.addOption("p", true, "User path to verify");
        options.addOption("method", true, "Solving method (righthand)");
        
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    private static void validateAndCheckPath(Maze maze, String userPath) {
        boolean[] pathValidation = PathVerifier.validatePathFormat(userPath);
        boolean isValid = pathValidation[0];
        boolean isFactorized = pathValidation[1];
        
        if (isFactorized) {
            userPath = Factorize.unfactorizePath(userPath);
            isValid = PathVerifier.validatePathFormat(userPath)[0];
        }
        
        if (isValid) {
            String result = maze.validateUserPath(userPath);
            System.out.println(result + " path");
        } else {
            logger.error("Invalid path format provided.");
        }
    }
}
