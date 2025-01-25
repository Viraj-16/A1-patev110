package ca.mcmaster.se2aa4.mazerunner;

class Explorer {
    private Point position;
    private Direction facing;
    private Maze maze;

    public Explorer(Maze maze) {
        
    }

    public void turnRight() {
        
    }

    public void turnLeft() {
        
    }

    public boolean moveForward() {
        Point nextPosition = getNextPosition();
            return false;
        
    }

    private Point getNextPosition() {
            return position;
    }

    public Point getPosition() { return position; }

    public Direction getFacing() { return facing; }

    public boolean isAtExit() {
        return position.x == maze.getExitPoint().x && position.y == maze.getExitPoint().y;
    }
}

class PathFinder {
    private Maze maze;
    private Explorer explorer;

    public PathFinder(Maze maze, Explorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }

    public String findPath() {
            return null;

    }
}
