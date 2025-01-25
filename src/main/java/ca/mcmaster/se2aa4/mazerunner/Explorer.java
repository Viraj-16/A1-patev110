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

    Point getNextPosition() {
            switch (facing) {
                case UP:
                    return new Point(position.x, position.y - 1);
                case RIGHT:
                    return new Point(position.x + 1, position.y);
                case DOWN:
                    return new Point(position.x, position.y + 1);
                case LEFT:
                    return new Point(position.x - 1, position.y);
            }
            return position;
        }
    
        public Point getPosition() {
            return position;
        }
    
        public Direction getFacing() {
            return facing;
        }
    
        public boolean isAtExit() {
            return position.equals(maze.getExitPoint());
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
    
            // Check if the exit is directly in front of the explorer
            Point nextPosition = explorer.getNextPosition();
            if (!maze.isWall(nextPosition) && nextPosition.equals(maze.getExitPoint())) {
                path.append("F");
                return path.toString();
            }
    
            // Otherwise, follow the right-hand wall algorithm
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