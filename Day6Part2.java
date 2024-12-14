
import utils.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Stream;

public class Day6Part2 {

    private static final String INPUT_PATH = "input.txt";

    private static final Character GUARD = '^';
    private static final Character OBSTACLE = '#';

    private static final DirectionalMatrix matrix;

    static {
        String input = FileIO.getContentsOf(INPUT_PATH);
        matrix = new DirectionalMatrix(input);
    }

    private static Coordinate tempObstacle = null;
    private static Direction heading = null;

    public static void main(String[] args) {
        WalkedPath path = runSimulation();
        HashSet<Coordinate> coords = path.getCoordinates();
        int loopCount = countLoops(coords);
        System.out.println(loopCount);
    }

    private static WalkedPath runSimulation() {
        WalkedPath output = new WalkedPath();
        initSimulation();
        Character next;
        while ((next = matrix.peekValue(heading)) != null) {
            Position current = getCurrentPosition();
            //System.out.println(current);
            if (output.passedBy(current)) {
                output.setIsLoop(true);
                //System.out.println("LOOP!");
                break;
            }
            output.addPosition(current);
            if (next.equals(OBSTACLE) || Objects.equals(matrix.peekCoordinate(heading), tempObstacle))
                heading = getRightTurn(heading);
            else matrix.move(heading);
        }
        output.addPosition(getCurrentPosition());
        return output;
    }

    private static int countLoops(HashSet<Coordinate> path) {
        int output = 0;
        for (Coordinate c : path) {
            tempObstacle = c;
            WalkedPath newPath = runSimulation();
            output += newPath.getIsLoop() ? 1 : 0;
        }
        return output;
    }

    private static void initSimulation() {
        //System.out.println("\n=== NEW SIMULATION! ===");
        //System.out.println(tempObstacle);
        Coordinate start = matrix.find(GUARD)[0];
        matrix.move(start);
        heading = Direction.NORTH;
    }

    private static Position getCurrentPosition() {
        return new Position(
          matrix.getCurrentCoordinates(),
          heading
        );
    }

    private static Direction getRightTurn(Direction d) {
        return switch (d) {
            case Direction.NORTH -> Direction.EAST;
            case Direction.EAST -> Direction.SOUTH;
            case Direction.SOUTH -> Direction.WEST;
            default -> Direction.NORTH;
        };
    }
}
