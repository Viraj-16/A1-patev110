package ca.mcmaster.se2aa4.mazerunner;

class Explorer {
    private Point position;
    private Direction facing;
    private Maze maze;

    public Explorer(Maze maze) {
        this.maze = maze;
        this.position = maze.getEntryPoint();
        this.facing = Direction.RIGHT;
    }

    public void turnRight() {
        facing = facing.turnRight();
    }

    public void turnLeft() {
        facing = facing.turnLeft();
    }

    public boolean moveForward() {
        Point nextPosition = getNextPosition();
        if (!maze.isWall(nextPosition)) {
            position = nextPosition;
            return true;
        }
        return false;
    }

    private Point getNextPosition() {
        switch (facing) {
            case UP: return new Point(position.x, position.y - 1);
            case RIGHT: return new Point(position.x + 1, position.y);
            case DOWN: return new Point(position.x, position.y + 1);
            case LEFT: return new Point(position.x - 1, position.y);
        }
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
        StringBuilder path = new StringBuilder();

        while (!explorer.isAtExit()) {
            if (explorer.moveForward()) {
                path.append("F");
            } else {
                explorer.turnRight();
                path.append("R");
            }
        }

        return path.toString();
    }
}
