
package utils;

import java.util.ArrayList;

public class OperandBuilder {

    private ArrayList<Integer> operands;
    private int currentOperand;

    public OperandBuilder() {
        operands = new ArrayList<>();
        currentOperand = 0;
    }

    public void addDigit(int d) {
        currentOperand *= 10;
        currentOperand += d;
    }

    public void newOperand() {
        operands.add(currentOperand);
        currentOperand = 0;
    }

    public ArrayList<Integer> getOperands() {
        newOperand();
        ArrayList<Integer> output = operands;
        operands = new ArrayList<>();
        return output;
    }

    public void reset() {
        getOperands();
    }
}
