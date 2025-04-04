package ca.mcmaster.se2aa4.mazerunner;

public class MazeInfo {
    private Position currentPosition;
    private Orientation currentOrientation;

    public MazeInfo(Position start, Orientation orientation) {
        this.currentPosition = start;
        this.currentOrientation = orientation;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Orientation getCurrentOrientation() {
        return currentOrientation;
    }

    public void setCurrentPosition(Position p) {
        this.currentPosition = p;
    }

    public void setCurrentOrientation(Orientation o) {
        this.currentOrientation = o;
    }
}
