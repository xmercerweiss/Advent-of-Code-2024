package utils;

public record Position(Coordinate coord, Direction heading) {

    private static final String PRINT_FORMAT = "(%d, %d, %s)";

    @Override
    public String toString() {
        return PRINT_FORMAT.formatted(
                coord.x(),
                coord.y(),
                heading
        );
    }

}
