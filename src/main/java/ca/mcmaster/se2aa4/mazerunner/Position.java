package ca.mcmaster.se2aa4.mazerunner;

public class Position {
    private int x;
    private int y;

    public Position(int xCoordinate, int yCoordinate) {
        this.x = xCoordinate;
        this.y = yCoordinate;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public static boolean areEqual(Position p1, Position p2) {
        return (p1.getX() == p2.getX()) && (p1.getY() == p2.getY());
    }
}
