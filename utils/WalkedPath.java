package utils;

import java.util.HashSet;
import java.util.stream.Collectors;

public class WalkedPath {

    private final HashSet<Position> positions;
    private boolean isLoop = false;

    public WalkedPath() {
        positions = new HashSet<>();
    }

    public void addPosition(Position position) {
        positions.add(position);
    }

    public HashSet<Position> getPositions() {
        return new HashSet<>(positions);
    }

    public boolean passedBy(Position position) {
        return positions.contains(position);
    }

    public boolean getIsLoop() {
        return isLoop;
    }

    public void setIsLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

    public HashSet<Coordinate> getCoordinates() {
        return positions.stream()
                .map(Position::coord)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
