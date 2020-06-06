import java.util.ArrayList;

public abstract class Plateau {

	public abstract int getPlanet();

	/// <summary>
	/// X coordinate of plateau specify the width.
	/// </summary>
	public int X;

	/// <summary>
	/// Y coordinate of plateau specify the height.
	/// </summary>
	public int Y;

	/// <summary>
	/// Rovers onboard.
	/// </summary>
	public ArrayList<Rover> Rovers = new ArrayList<Rover>();
}
