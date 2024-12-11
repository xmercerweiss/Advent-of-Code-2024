
import utils.Coordinate;
import utils.DirectionalMatrix;
import utils.Directions;
import utils.FileIO;

public class Day4Part2 {

    private static final String INP_PATH = "input.txt";
    private static final String SEARCHED_WORD = "X-MAS";

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
        Coordinate[] starts = matrix.find(Day4Part2.SEARCHED_WORD.charAt(0));
        for (Coordinate start : starts) {
            for (Directions d : Directions.values()) {
                matrix.move(start);
                output += doesResolveWord(d) ? 1 : 0;
            }
        }
        return output;
    }

    private static boolean doesResolveWord(Directions dir) {
        int length = SEARCHED_WORD.length();
        int index = 1;
        while (matrix.peekValue(dir) != null && index < length) {
            matrix.move(dir);
            char current = matrix.getValue();
            char needed = SEARCHED_WORD.charAt(index);
            if (current != needed && index != 1) return false;
            index++;
        }
        return index >= length;
    }
}
