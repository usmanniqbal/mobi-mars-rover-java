public class Command {
    private final String _roverName;
    private final int _x;
    private final int _y;
    private final int _orientation;
    private final char[] _navigations;

    public Command(final String roverName, final int x, final int y, final int orientation, final char[] navigations) {
        this._roverName = roverName;
        this._x = x;
        this._y = y;
        this._orientation = orientation;
        this._navigations = navigations;
    }

    public char[] get_navigations() {
        return _navigations;
    }

    public int get_orientation() {
        return _orientation;
    }

    public int get_y() {
        return _y;
    }

    public int get_x() {
        return _x;
    }

    public String get_roverName() {
        return _roverName;
    }
}