
import java.util.ArrayList;

import utils.FileIO;


class Day2Part1 {

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
		for (ArrayList<Integer> report : reports)
			if (isSafe(report)) output++;
		return output;
	}	

	private static boolean isSafe(ArrayList<Integer> report) {
		boolean isIncr = true;
		boolean isDecr = true;
		for (int i = 0; i < report.size() - 1; i++) {
			int diff = report.get(i) - report.get(i+1);
			int absDiff = Math.abs(diff);
			isIncr &= diff > 0;
			isDecr &= diff < 0;
			if (absDiff < 1 || absDiff > 3 || (!isIncr && !isDecr)) return false;
		}
		return true;
	}
}

