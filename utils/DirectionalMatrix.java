
package utils;

import java.util.ArrayList;

public class DirectionalMatrix {

    private final Character[][] matrix;
    private final int width;
    private final int height;
    private Coordinate position;

    public DirectionalMatrix(String input) {
        this.matrix = buildMatrix(input);
        validateMatrix();
        this.width = this.matrix[0].length;
        this.height = this.matrix.length;
        this.position = new Coordinate(0,0);
    }

    public void move(Coordinate coord) {
        assert coord.x() >= 0 && coord.x() < width;
        assert coord.y() >= 0 && coord.y() < height;
        position = coord;
    }

    public void move(Directions dir) {
        int x = position.x();
        int y = position.y();
        switch (dir) {
            case NORTH: y--; break;
            case NORTHEAST: x++; y--; break;
            case EAST: x++; break;
            case SOUTHEAST: x++; y++; break;
            case SOUTH: y++; break;
            case SOUTHWEST: x--; y++; break;
            case WEST: x--; break;
            case NORTHWEST: x--; y--; break;
        }
        move(new Coordinate(x, y));
    }

    public Coordinate[] find(char c) {
        ArrayList<Coordinate> found = new ArrayList<>();
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if (matrix[y][x] == c)
                    found.add(new Coordinate(x, y));
        return found.toArray(new Coordinate[0]);
    }

    public Coordinate getPosition() {
        return position;
    }

    public Character getValue() {
        return matrix[position.y()][position.x()];
    }

    private Character[][] buildMatrix(String input) {
        ArrayList<Character[]> matrixBuilder = new ArrayList<>();
        for (String line : input.split("\n")) {
            int length = line.length();
            Character[] row = new Character[length];
            for (int i = 0; i < length; i++) row[i] = line.charAt(i);
            matrixBuilder.add(row);
        }
        return matrixBuilder.toArray(new Character[0][]);
    }

    private void validateMatrix() {
        assert matrix.length == matrix[0].length;
        for (Character[] row : matrix)
            assert row.length == matrix.length;
    }
}
