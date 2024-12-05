
import java.util.Arrays;
import java.util.List;

import utils.FileIO;
import utils.CommandParser;
import utils.CommandValidator;


class Day3Part2 {

	private static final String INP_PATH = "input.txt";
	private static final String CMD_SPLIT_REGEX = "[,()]";

	private static final String MUL_CMD = String.format("mul(%1$c,%1$c)", CommandValidator.INT_PLACEHOLDER);
	private static final String DO_CMD = "do()";
	private static final String DONT_CMD = "don't()";

	private static final String[] COMMANDS = {
			MUL_CMD,
			DO_CMD,
			DONT_CMD
	};

	private static final CommandParser parser = new CommandParser(COMMANDS);

	public static void main(String[] args) {
		String input = flatten(FileIO.getLinesOf(INP_PATH));
		String[] commands = parser.parse(input);
		int result = interpret(commands);
		System.out.println(result);
	}

	private static String flatten(List<String> lines) {
		StringBuilder mut = new StringBuilder();
		for (String line : lines)
			mut.append(line);
		return mut.toString();
	}

	private static int interpret(String[] commands) {
		int result = 0;
		boolean isApplied = true;
		for (String cmd : commands) {
			String[] split = cmd.split(CMD_SPLIT_REGEX);
			String operation = split[0] + "()";
			if (cmd.startsWith("mul")) System.out.printf("%-6b: %s\n", isApplied, cmd);
			else System.out.printf("%-6c: %s\n", '~', cmd);
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

