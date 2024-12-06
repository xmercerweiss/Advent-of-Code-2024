package utils;

public record Coordinate(int x, int y) {

    private static final String PRINT_FORMAT = "(%d, %d)";

    @Override
    public String toString() {
        return PRINT_FORMAT.formatted(x, y);
    }
}
