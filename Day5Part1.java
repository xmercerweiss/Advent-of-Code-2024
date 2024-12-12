
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import utils.FileIO;

public class Day5Part1 {

    private static final String INPUT_PATH = "input.txt";

    private static final String RULE_SEP = "\\|";
    private static final String UPDATE_SEP = ",";
    private static final String BLANK_LINE = "";

    private static final HashMap<Integer,HashSet<Integer>> afters = new HashMap<>();
    private static final ArrayList<ArrayList<Integer>> updates = new ArrayList<>();

    static {
        ArrayList<String> input = FileIO.getLinesOf(INPUT_PATH);
        populateAfters(input);
        populateUpdates(input);
    }

    private static void populateAfters(ArrayList<String> input) {
        for (String s : input) {
            if (s.isEmpty()) break;
            String[] split = s.split(RULE_SEP);
            int first = Integer.parseInt(split[0]);
            int second = Integer.parseInt(split[1]);
            afters.putIfAbsent(first, new HashSet<>());
            afters.get(first).add(second);
        }
    }

    private static void populateUpdates(ArrayList<String> input) {
        for (int index = input.indexOf(BLANK_LINE) + 1; index < input.size(); index++) {
            ArrayList<Integer> update = new ArrayList<>();
            String line = input.get(index);
            for (String s : line.split(UPDATE_SEP)) {
                int n = Integer.parseInt(s);
                update.add(n);
            }
            updates.add(update);
        }
    }

    public static void main(String[] args) {
        int sum = getValidUpdateSum();
        System.out.println(sum);
    }

    private static int getValidUpdateSum() {
        int output = 0;
        outer:
        for (ArrayList<Integer> update : updates) {
            for (int i = 0; i < update.size(); i++) {
                int num = update.get(i);
                HashSet<Integer> afterNum = new HashSet<>(update.subList(i+1, update.size()));
                HashSet<Integer> allowed = getNumsAllowedAfter(num);
                allowed.retainAll(afterNum);
                if (!allowed.equals(afterNum))
                    continue outer;
            }
            output += getMiddleNum(update);
        }
        return output;
    }

    private static HashSet<Integer> getNumsAllowedAfter(int num) {
        if (afters.containsKey(num))
            return new HashSet<>(afters.get(num));
        return new HashSet<>();
    }

    private static int getMiddleNum(ArrayList<Integer> nums) {
        int middleIndex = (nums.size() / 2);
        return nums.get(middleIndex);
    }

}
