
package utils;

import java.util.ArrayList;


public class CommandParser {

    private final ArrayList<CommandValidator> validators = new ArrayList<>();

    public CommandParser(String[] commands) {
        for (String cmd : commands)
            validators.add(new CommandValidator(cmd));
    }

    public String[] parse(String input) {
        ArrayList<String> found = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            boolean matchesAny = false;
            for (CommandValidator v : validators) {
                String current = buffer.toString();
                if (v.isFullCommand(current)) {
                    found.add(current);
                    break;
                }
                if (v.isValidChar(c)) {
                    matchesAny = true;
                    if (Character.isDigit(c) && Character.isDigit(input.charAt(i+1))) continue;
                    v.incrementIndex();
                }
            }
            if (!matchesAny) {
                buffer = new StringBuilder();
                resetValidators();
            }
            else buffer.append(c);
        }
        return found.toArray(new String[0]);
    }

    private void resetValidators() {
        for (CommandValidator v : validators) {
            v.reset();
        }
    }
}
