package ca.mcmaster.se2aa4.mazerunner;

public class Position {
    int x;
    int y;

    public Coordinate(int xCoordinate, int yCoordinate) {
        this.x = xCoordinate;
        this.y = yCoordinate;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    // Checks if two coordinates are equivalent
    public static boolean areEqual(Coordinate c1, Coordinate c2) {
        return (c1.getX() == c2.getX()) && (c1.getY() == c2.getY());
    }
}