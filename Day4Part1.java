
import utils.Coordinate;
import utils.DirectionalMatrix;
import utils.Directions;
import utils.FileIO;

import java.util.Arrays;

public class Day4Part1 {

    private static final String INP_PATH = "input.txt";

    public static void main(String[] args) {
        String input = FileIO.getContentsOf(INP_PATH);
        DirectionalMatrix dm = new DirectionalMatrix(input);
        Coordinate[] exes = dm.find('X');
        System.out.println(Arrays.toString(exes));
    }
}
