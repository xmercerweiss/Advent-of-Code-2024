
import java.util.ArrayList;
import java.util.List;

import utils.FileIO;
import utils.OperandBuilder;
import utils.CommandValidator;


class Day3Part1 {

	private static final String INP_PATH = "input.txt";

	private static final String OP_FORMAT = "mul(%1$c,%1$c)".formatted(CommandValidator.INT_PLACEHOLDER);
	private static final char OPERAND_SEP = ',';

	private static final CommandValidator commandValidator = new CommandValidator(OP_FORMAT);
	private static final OperandBuilder opBuilder = new OperandBuilder();
	private static final ArrayList<ArrayList<Integer>> operands = new ArrayList<>();

	public static void main(String[] args) {
		String input = flatten(FileIO.getLinesOf(INP_PATH));
		getOperands(input);
		int result = getSumOfProducts();
		System.out.println(result);
	}

	private static String flatten(List<String> lines) {
		StringBuilder mut = new StringBuilder();
		for (String line : lines)
			mut.append(line);
		return mut.toString();
	}

	private static void getOperands(String input) {
		for (int i = 0; i < input.length(); i++) {
		    char c = input.charAt(i);
			if (!commandValidator.isValidChar(c)) {
				resetUtilities();
				continue;
			}
			if (Character.isDigit(c)) {
				int digit = (int) c - 48;
				opBuilder.addDigit(digit);
				if (!Character.isDigit(input.charAt(i + 1)))
					incrementValidator();
				continue;
			}
			if (c == OPERAND_SEP) opBuilder.newOperand();
			incrementValidator();
		}
	}

	private static int getSumOfProducts() {
		int output = 0;
		for (ArrayList<Integer> ints : operands) {
			int product = 1;
			for (int i : ints)
				product *= i;
			output += product;
		}
		return output;
	}

	private static void resetUtilities() {
		commandValidator.reset();
		opBuilder.reset();
	}

	private static void incrementValidator() {
		int validatorIndex = commandValidator.incrementIndex();
		if (validatorIndex == 0)
			operands.add(opBuilder.getOperands());
	}
}

