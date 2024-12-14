
import java.util.HashSet;

import utils.Coordinate;
import utils.DirectionalMatrix;
import utils.Direction;
import utils.FileIO;

public class Day6Part1 {

    private static final String INPUT_PATH = "input.txt";

    private static final Character GUARD = '^';
    private static final Character OBSTACLE = '#';

    private static final HashSet<Coordinate> positions = new HashSet<>();
    private static final DirectionalMatrix matrix;

    static {
        String input = FileIO.getContentsOf(INPUT_PATH);
        matrix = new DirectionalMatrix(input);
    }

    public static void main(String[] args) {
        runSimulation();
        int positionCount = positions.size();
        System.out.println(positionCount);
    }

    private static void runSimulation() {
        Coordinate start = matrix.find(GUARD)[0];
        matrix.move(start);
        Direction heading = Direction.NORTH;
        Character next;
        while ((next = matrix.peekValue(heading)) != null) {
            positions.add(matrix.getCurrentCoordinates());
            if (next.equals(OBSTACLE))
                heading = getRightTurn(heading);
            else matrix.move(heading);
        }
        positions.add(matrix.getCurrentCoordinates());
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
