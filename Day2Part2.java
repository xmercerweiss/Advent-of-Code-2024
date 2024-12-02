
import java.util.ArrayList;


class Day2Part2 {

	private static final String INP_PATH = "input.txt";
	private static final String SEP = "\s";

	public static void main(String args[]) {
		ArrayList<ArrayList<Integer>> reports = getReports();
		int safeCount = countSafeReports(reports);
		System.out.println(safeCount);
	}

	private static ArrayList<ArrayList<Integer>> getReports() {
		ArrayList<ArrayList<Integer>> output = new ArrayList<>();
		for (String line : FileIO.getLinesOf(INP_PATH)) {
			ArrayList<Integer> report = new ArrayList<>();
			for (String s : line.split(SEP)) 
				report.add(Integer.valueOf(s));
			output.add(report);
		}
		return output;
	}

	private static int countSafeReports(ArrayList<ArrayList<Integer>> reports) {
		int output = 0;
		int tolerance = 1;
		for (ArrayList<Integer> report : reports) {
			if (isSafe(report, tolerance)) output++;
		}
		return output;
	}	

	private static boolean isSafe(ArrayList<Integer> report, int tolerance) {
		System.out.printf("\n%s", report.toString());
		boolean isIncr = isIncreasing(report);
		int length = report.size();
		outer:
		for (int j = 0; j < length; j++) {
			System.out.println();
			for (int i = 0; i < length - 1; i++) {
				int from = i;
				int to = i + 1;
				if (from == j) continue;
				else if (to == j && to + 1 == length) break;
				else if (to == j) to++;
				System.out.printf("%d -> %d\n", report.get(from), report.get(to));
				int diff = report.get(to) - report.get(from);
				if (!isAllowedDifference(diff, isIncr)) {
					System.out.println("breaking!");
					continue outer;
				}
			}
			return true;
		}
		return false;
	}

	private static boolean isIncreasing(ArrayList<Integer> ints) {
		int sum = 0;
		for (int i = 0; i < ints.size() - 1; i++) {
			int diff = ints.get(i+1) - ints.get(i);
			sum += diff > 0 ? 1 : -1;
		}
		return sum > 0;
	}

	private static boolean isAllowedDifference(int diff, boolean isIncr) {
		int absDiff = Math.abs(diff);
		if ((isIncr && diff <= 0) || (!isIncr && diff >= 0)) return false;
		return absDiff >= 1 && absDiff <= 3;
	}
}

