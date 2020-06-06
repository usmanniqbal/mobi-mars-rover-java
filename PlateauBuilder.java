public class PlateauBuilder {
	private final Plateau _plateau;

	private PlateauBuilder(final int planet) {
		switch (planet) {
			case Planet.Mars:
				_plateau = new MarsPlateau();
				break;
			case Planet.Venus:
				_plateau = new VenusPlateau();
				break;
			default:
				_plateau = null;
				break;
		}
	}

	public static PlateauBuilder Builder(final int planet) {
		return new PlateauBuilder(planet);
	}

	/// <summary>
	/// Sets maximum x-axis value of plateau
	/// </summary>
	public PlateauBuilder SetX(final int x) {
		_plateau.X = x;
		return this;
	}

	/// <summary>
	/// Sets maximum y-axis value of plateau
	/// </summary>
	public PlateauBuilder SetY(final int y) {
		_plateau.Y = y;
		return this;
	}

	public Plateau Create() {
		return _plateau;
	}
}
