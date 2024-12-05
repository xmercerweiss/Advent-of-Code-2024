
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.FileIO;


class Day3Part2 {

	private static final String INP_PATH = "input.txt";
	private static final String CMD_PARSE_REGEX = "mul\\([0-9]*,[0-9]*\\)|don't\\(\\)|do\\(\\)";
	private static final String CMD_SPLIT_REGEX = "[,()]";

	private static final String DO_CMD = "do()";
	private static final String DONT_CMD = "don't()";

	public static void main(String[] args) {
		String input = flatten(FileIO.getLinesOf(INP_PATH));
		String[] commands = parseCommands(input);
		int result = interpret(commands);
		System.out.println(result);
	}

	private static String flatten(List<String> lines) {
		StringBuilder mut = new StringBuilder();
		for (String line : lines)
			mut.append(line);
		return mut.toString();
	}

	private static String[] parseCommands(String input) {
		ArrayList<String> found = new ArrayList<>();
		Pattern p = Pattern.compile(CMD_PARSE_REGEX);
		Matcher m = p.matcher(input);
		while (m.find())
			found.add(m.group());
		return found.toArray(new String[0]);
	}

	private static int interpret(String[] commands) {
		int result = 0;
		boolean isApplied = true;
		for (String cmd : commands) {
			String[] split = cmd.split(CMD_SPLIT_REGEX);
			String operation = split[0] + "()";
			switch (operation) {
				case DO_CMD:
					isApplied = true; break;
				case DONT_CMD:
					isApplied = false; break;
				default:
					if (isApplied) {
						Integer[] operands = getOperands(Arrays.copyOfRange(split, 1, split.length));
						int product = 1;
						for (int o : operands) product *= o;
						result += product;
					}
			}
		}
		return result;
	}

	private static Integer[] getOperands(String[] opStrings) {
		Integer[] output = new Integer[opStrings.length];
		for (int i = 0; i < opStrings.length; i++)
			output[i] = Integer.valueOf(opStrings[i]);
		return output;
	}
}

