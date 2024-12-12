
import java.util.*;

import utils.FileIO;

public class Day5Part2 {

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
        for (ArrayList<Integer> update : updates) {
            ArrayList<Integer> corrected = getCorrectedUpdate(update);
            if (!update.equals(corrected)) {
                output += getMiddleNum(corrected);
            }
        }
        return output;
    }

    private static ArrayList<Integer> getCorrectedUpdate(ArrayList<Integer> update) {
        Integer[] corrected = new Integer[update.size()];
        int maxIndex = update.size() - 1;
        for (int num : update) {
            HashSet<Integer> afterNum = getNumsAllowedAfter(num);
            afterNum.retainAll(update);
            int index = maxIndex - afterNum.size();
            corrected[index] = num;
        }
        return new ArrayList<>(Arrays.asList(corrected));
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
