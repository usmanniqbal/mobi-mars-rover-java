/**
 * Rover
 */
public abstract class Rover {
	public abstract int getPlanet();

	/// <summary>
	/// Name of rover.
	/// </summary>
	public String Name;

	/// <summary>
	/// Current position of rover on x-axis.
	/// </summary>
	public int X;

	/// <summary>
	/// Current position of rover on y-axis.
	/// </summary>
	public int Y;

	/// <summary>
	/// Current orientation of rover to which its heading.
	/// </summary>
	public int Orientation;

	public String toString() {
		return this.Name + " Standing: " + this.X + " " + this.Y + " " + strOrientation(this.Orientation);
	}

	private String strOrientation(final int orientation) {
		switch (orientation) {
			case 0:
				return "E";
			case 1:
				return "N";
			case 2:
				return "W";
			case 3:
				return "S";
			default:
				return null;
		}
	}
}
