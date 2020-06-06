public class RoverBuilder {
	private final Rover _rover;
	private final Plateau _plateau;

	private RoverBuilder(final Plateau plateau) {
		switch (plateau.getPlanet()) {
			case Planet.Mars:
				_rover = new MarsRover();
				break;
			case Planet.Venus:
				_rover = new VenueRover();
				break;
			default:
				_rover = null;
				break;
		}
		_plateau = plateau;
	}

	private RoverBuilder(final int planet, final int plateauX, final int plateauY) {
		final Plateau plateau = PlateauBuilder.Builder(planet).SetX(plateauX).SetY(plateauY).Create();

		switch (plateau.getPlanet()) {
			case Planet.Mars:
				_rover = new MarsRover();
				break;
			case Planet.Venus:
				_rover = new VenueRover();
				break;
			default:
				_rover = null;
				break;
		}
		_plateau = plateau;
	}

	public static RoverBuilder Builder(final Plateau plateau) {
		return new RoverBuilder(plateau);
	}

	public static RoverBuilder Builder(final int planet, final int plateauX, final int plateauY) {
		return new RoverBuilder(planet, plateauX, plateauY);
	}

	/// <summary>
	/// This method sets the name of rover.
	/// </summary>
	public RoverBuilder SetName(final String name) {
		_rover.Name = name;
		return this;
	}

	/// <summary>
	/// This method lands rover on specified position on the plateau.
	/// </summary>
	public RoverBuilder Landing(final int x, final int y, final int orientation) throws Exception {
		_rover.X = x;
		_rover.Y = y;

		if (x > _plateau.X || y > _plateau.Y) {
			throw new Exception(_rover.Name + "'s landing coordinates can not be outside Plateau's coordinates");
		} else if (_plateau.Rovers.contains(_rover)) {
			throw new Exception(_rover.Name + " can not land on " + x + "," + y
					+ " as there is already a rover on the provided coordinates");
		}

		_rover.Orientation = orientation;
		_plateau.Rovers.add(_rover);
		return this;
	}

	/// <summary>
	/// Navigates the rover on plateau according to the provided navigation
	/// instruction, possible values are ('L', 'R' and 'M').
	/// </summary>
	public RoverBuilder Navigate(final char[] navigations) throws Exception {
		for (int i = 0; i < navigations.length; i++) {
			switch (navigations[i]) {
				case 'L':
					_rover.Orientation = _rover.Orientation == RoverOrientation.S ? RoverOrientation.E
							: _rover.Orientation + 1;
					break;
				case 'R':
					_rover.Orientation = _rover.Orientation == RoverOrientation.E ? RoverOrientation.S
							: _rover.Orientation - 1;
					break;
				case 'M':
					int x = (int) _rover.X;
					int y = (int) _rover.Y;

					// Rover Moves forward on x-axis if its headed already towards East.
					x += _rover.Orientation == RoverOrientation.E ? 1 : 0;

					// Rover Moves backward on x-axis if its headed already towards West.
					x -= _rover.Orientation == RoverOrientation.W ? 1 : 0;

					// Rover Moves upward on y-axis if its headed already towards North.
					y += _rover.Orientation == RoverOrientation.N ? 1 : 0;

					// Rover Moves downward on y-axis if its headed already towards South.
					y -= _rover.Orientation == RoverOrientation.S ? 1 : 0;

					if ((x < 0 || x > _plateau.X) || (y < 0 || y > _plateau.Y)) {
						throw new Exception(_rover.Name + " can not navigate outside Plateau's coordinates");
					}

					_rover.X = (int) x;
					_rover.Y = (int) y;
					break;
				default:
					break;
			}
		}

		if (any()) {
			throw new Exception(_rover.Name + " can not navigate to " + _rover.X + "," + _rover.Y
					+ " as there is already a rover.");
		}

		return this;
	}

	private boolean any() {
		for (final Rover rover : _plateau.Rovers) {
			if (rover.Name != _rover.Name && rover.X == _rover.X && rover.Y == _rover.Y) {
				return true;
			}
		}

		return false;
	}

	public Rover Create() {
		return _rover;
	}
}
