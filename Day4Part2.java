
import utils.Coordinate;
import utils.DirectionalMatrix;
import utils.Direction;
import utils.FileIO;

import static utils.Direction.*;

public class Day4Part2 {

    private static final String INP_PATH = "input.txt";

    private static final char FIRST = 'M';
    private static final char MIDDLE = 'A';
    private static final char LAST = 'S';

    private static final DirectionalMatrix matrix;

    // Init block used to statically initialize matrix.
    static {
        String input = FileIO.getContentsOf(INP_PATH);
        matrix = new DirectionalMatrix(input);
    }

    public static void main(String[] args) {
        int count = countWord();
        System.out.println(count);
    }

    private static int countWord() {
        int output = 0;
        Coordinate[] starts = matrix.find(MIDDLE);
        outer:
        for (Coordinate start : starts) {
            matrix.move(start);
            for (Direction d : Direction.values())
                if (matrix.peekValue(d) == null) continue outer;
            output += isAtMasX() ? 1 : 0;
        }
        return output;
    }

    private static boolean isAtMasX() {
        return (matrix.peekValue(NORTHWEST) == FIRST && matrix.peekValue(SOUTHEAST) == LAST
                || matrix.peekValue(NORTHWEST) == LAST && matrix.peekValue(SOUTHEAST) == FIRST)
                &&
                (matrix.peekValue(SOUTHWEST) == FIRST && matrix.peekValue(NORTHEAST) == LAST
                || matrix.peekValue(SOUTHWEST) == LAST && matrix.peekValue(NORTHEAST) == FIRST);
    }

}
