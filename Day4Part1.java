
import utils.Coordinate;
import utils.DirectionalMatrix;
import utils.Direction;
import utils.FileIO;

public class Day4Part1 {

    private static final String INP_PATH = "input.txt";
    private static final String SEARCHED_WORD = "XMAS";

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
        Coordinate[] starts = matrix.find(Day4Part1.SEARCHED_WORD.charAt(0));
        for (Coordinate start : starts) {
            for (Direction d : Direction.values()) {
                matrix.move(start);
                output += doesResolveWord(d) ? 1 : 0;
            }
        }
        return output;
    }

    private static boolean doesResolveWord(Direction dir) {
        int length = SEARCHED_WORD.length();
        int index = 1;
        while (matrix.peekValue(dir) != null && index < length) {
            matrix.move(dir);
            char current = matrix.getValue();
            char needed = SEARCHED_WORD.charAt(index);
            if (current != needed) return false;
            index++;
        }
        return index >= length;
    }
}
