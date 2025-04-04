package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolverFactory {
    public static SolveMaze getMazeSolver(String method) {
        if (method == null || method.isEmpty() || method.equalsIgnoreCase("righthand")) {
            return new RightHandSolver();
        }
        // Future: additional solving strategies
         return new RightHandSolver();
    }
}
