import java.util.LinkedList;
import java.util.regex.Pattern;

class Program {
    /// <summary>
    /// Regex for navigation instructions
    /// </summary>
    static String planetInputFormat = "^[12]$";

    /// <summary>
    /// Regex for landing instructions
    /// </summary>
    static String landingInputFormat = "^[0-9]{1,7}\s[0-9]{1,7}\s[eEwWnNsS]{1}$";

    /// <summary>
    /// Regex for plateau instructions
    /// </summary>
    static String plateauInputFormat = "^[0-9]{1,7}\s[0-9]{1,7}$";

    /// <summary>
    /// Regex for navigation instructions
    /// </summary>
    static String navigationInputFormat = "^[LlrRmM]*$";

    static LinkedList<Command> commands = new LinkedList<>();

    /// <summary>
    /// Entry point of program
    /// </summary>
    public static void main(final String[] args) {
        while (true) {
            try {
                System.out.println();
                final int noOfRovers = 2;
                final int planet = GetPlanet();
                final Plateau plateau = GetPlateau(planet);
                // Loop for getting information of each rover from user.
                for (int i = 0; i < noOfRovers; i++) {
                    final String roverName = "Rover " + (i + 1);
                    final RoverLanding roverLanding = GetRoverLanding(roverName, plateau);
                    final char[] roverNav = GetNavCommands(roverName);
                    commands.push(new Command(roverName, roverLanding.getX(), roverLanding.getY(),
                            roverLanding.getOrientation(), roverNav));
                }

                System.out.println();
                System.out.println("Output");

                // Loop for performing commands associated with each rover.
                for (final Command command : commands) {
                    final Rover rover = RoverBuilder.Builder(plateau).SetName(command.get_roverName())
                            .Landing(command.get_x(), command.get_y(), command.get_orientation())
                            .Navigate(command.get_navigations()).Create();

                    System.out.println(rover.toString() + "");
                }
            } catch (final Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    /// <summary>
    /// Gets input for plateau from user and create its object accordingly.
    /// </summary>
    private static int GetPlanet() {
        System.out.println("Select Planet:");
        System.out.println("1 - Mars:");
        System.out.println("2 - Venus:");
        final String input = System.console().readLine();

        if (!Pattern.matches(planetInputFormat, input)) {
            System.out.println("Invalid planet.");
            return GetPlanet();
        }

        return Integer.parseInt(input);
    }

    /// <summary>
    /// Gets input for plateau from user and create its object accordingly.
    /// </summary>
    private static Plateau GetPlateau(final int planet) {
        System.out.println("Plateau Coordinates: ");
        final String input = System.console().readLine();

        // Checking if the provided input matches the format. If input does not match
        // the pattern it recalls itself to get input again.
        if (!Pattern.matches(plateauInputFormat, input)) {
            System.out.println("Invalid size, input should only be numbers seperated by space.");
            return GetPlateau(planet);
        }

        final String[] plateauSize = input.toUpperCase().split(" ");
        final int plateauX = Integer.parseInt(plateauSize[0]);
        final int plateauY = Integer.parseInt(plateauSize[1]);
        return PlateauBuilder.Builder(planet).SetX(plateauX).SetY(plateauY).Create();
    }

    /// <summary>
    /// Gets navigation commands from user and validates it.
    /// </summary>
    private static char[] GetNavCommands(final String roverName) {
        System.out.println(roverName + " Instructions: ");
        final String input = System.console().readLine();

        // Checking if the provided input matches the format. If input does not match
        // the pattern it recalls itself to get input again.
        if (!Pattern.matches(navigationInputFormat, input)) {
            System.out.println("Invalid Commands, navigations instrcution can only have 'L' 'R' or 'M'");
            return GetNavCommands(roverName);
        }
        return input.toUpperCase().toCharArray();
    }

    /// <summary>
    /// Gets landing commands from user and validates it.
    /// </summary>
    private static RoverLanding GetRoverLanding(final String roverName, final Plateau plateau) {
        System.out.println(roverName + " Landing: ");
        final String input = System.console().readLine();

        // Checking if the provided input matches the format. If input does not match
        // the pattern it recalls itself to get input again.
        if (!Pattern.matches(landingInputFormat, input)) {
            System.out.println(
                    "Invalid landing commands, input can only be coordinates and orientation e.g. 1 2 E/W/N/S");
            return GetRoverLanding(roverName, plateau);
        }

        final String[] roverLanding = input.toUpperCase().split(" ");
        final int roverX = Integer.parseInt(roverLanding[0]);
        final int roverY = Integer.parseInt(roverLanding[1]);

        return new RoverLanding(roverX, roverY, intOrientation(roverLanding[2]));
    }

    private static Integer intOrientation(final String orientation) {
        switch (orientation) {
            case "E":
                return 0;
            case "N":
                return 1;
            case "W":
                return 2;
            case "S":
                return 3;
            default:
                return null;
        }
    }
}
