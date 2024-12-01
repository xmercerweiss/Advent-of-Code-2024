
import java.util.Collections;
import java.util.ArrayList;


class Day1Part1 {
	
	static final String SEPARATOR = "   ";

	public static void main(String[] args) {
		ArrayList<String> input = FileIO.getLinesOf("input.txt");
		ArrayList<Integer> left = new ArrayList<>();
		ArrayList<Integer> right = new ArrayList<>();

		for (String line : input) {
			String[] split = line.split(SEPARATOR);
			int first = Integer.valueOf(split[0]);
			int second = Integer.valueOf(split[1]);
			left.add(first);
			right.add(second); 
		}

		Collections.sort(left);
		Collections.sort(right);
		int output = 0;
		for (int i = 0; i < left.size(); i++)
			output += Math.abs(left.get(i) - right.get(i));

		System.out.println(output);
	}
}

