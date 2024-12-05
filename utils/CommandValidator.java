
package utils;

public class CommandValidator {

    public static final char INT_PLACEHOLDER = '‽';
    public static final String INT_REGEX = "[0-9]+";

    private final String format;
    private final int format_length;
    private int index;

    public CommandValidator(String format) {
        this.format = format;
        format_length = format.length();
        index = 0;
    }

    public int incrementIndex() {
        index++;
        if (index >= format_length)
            index = 0;
        return index;
    }

    public boolean isValidChar(char c) {
        char formatChar = format.charAt(index);
        if (format.charAt(index) == INT_PLACEHOLDER)
            return Character.isDigit(c);
        else return c == formatChar;
    }

    public void reset() {
        index = 0;
    }

    public boolean isFullCommand(String cmd) {
        String input = collapseInts(cmd);
        if (input.length() != format_length) return false;
        for (int i = 0; i < format_length; i++) {
            if (input.charAt(i) != format.charAt(i)) return false;
        }
        return true;
    }

    public boolean isPartialCommand(String cmd) {
        String input = collapseInts(cmd);
        return format.startsWith(input);
    }

    private String collapseInts(String s) {
        return s.replaceAll(INT_REGEX, String.valueOf(INT_PLACEHOLDER));
    }
}
