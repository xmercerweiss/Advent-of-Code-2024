
import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;


class Day1Part2 {
	
	static final String SEPARATOR = "   ";

	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<>();
		LinkedList<Integer> r = new LinkedList<>();
		fillLists(l, r);
		int similarity = getSimilarityScore(l, r);
		System.out.println(similarity);
	}

	private static void fillLists(LinkedList<Integer> left, LinkedList<Integer> right) {
		ArrayList<String> input = FileIO.getLinesOf("input.txt");
		for (String line : input) {
			String[] split = line.split(SEPARATOR);
			int first = Integer.valueOf(split[0]);
			int second = Integer.valueOf(split[1]);
			left.add(first);
			right.add(second); 
		}
		Collections.sort(left);
		Collections.sort(right);
	}

	private static int getSimilarityScore(LinkedList<Integer> left, LinkedList<Integer> right) {
		HashMap<Integer,Integer> counter = new HashMap<>();
		int output = 0;
		for (int n : left) {
			int count;
			if (counter.containsKey(n)) count = counter.get(n);
			else {count = countAndRemove(n, right); counter.put(n, count);}
			output += n * count;
		}
		return output;
	}

	private static int countAndRemove(int n, LinkedList<Integer> list) {
		int count = 0;
		while (list.contains(n)) {
			count++;
			list.removeFirstOccurrence(n);
		}
		return count;
	}
}

