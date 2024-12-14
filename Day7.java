
import utils.*;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Day7 {

    private static final String INPUT_PATH = "input.txt";
    private static final String INPUT_SPLIT_REGEX = "[ :]";

    private static final Operator[] OPERATORS = {
            Operator.ADD,
            Operator.MUL,
            // Comment/uncomment CONCAT for part 2
            Operator.CONCAT
    };

    public static void main(String[] args) {
        List<List<Long>> input = getInput();
        long sum = sumValidOperations(input);
        System.out.println(sum);
    }

    private static List<List<Long>> getInput() {
        List<List<Long>> output = new ArrayList<>();
        List<String> lines = FileIO.getLinesOf(INPUT_PATH);
        for (String line : lines) {
            ArrayList<Long> row = new ArrayList<>();
            for (String s : line.split(INPUT_SPLIT_REGEX))
                if (!s.isEmpty()) row.add(Long.parseLong(s));
            output.add(row);
        }
        return output;
    }

    private static long sumValidOperations(List<List<Long>> input) {
        long output = 0;
        for (List<Long> operation : input)
            if (isOperationValid(operation))
                output += operation.getFirst();
        return output;
    }

    private static boolean isOperationValid(List<Long> operation) {
        List<Long> operands = operation.subList(1, operation.size());
        long desired = operation.getFirst();
        Queue<Long> results = new LinkedList<>(List.of(operands.getFirst()));
        for (int i = 1; i < operands.size(); i++)
            applyOperandToQueue(operands.get(i), results);
        return results.contains(desired);
    }

    private static void applyOperandToQueue(long operand, Queue<Long> nums) {
        int initLength = nums.size();
        for (int i = 0; i < initLength; i++) {
            long polled = nums.remove();
            for (Operator operator : OPERATORS)
                nums.offer(operator.execute(polled, operand));
        }
    }
}
