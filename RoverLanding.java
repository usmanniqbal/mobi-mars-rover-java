public class RoverLanding {
    private final int x;
    private final int y;
    private final int orientation;

    public RoverLanding(final int x, final int y, final int orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getOrientation() {
        return orientation;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}