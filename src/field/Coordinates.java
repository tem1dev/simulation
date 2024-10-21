package field;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coordinates {
    private final int row;
    private final int column;

    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    private static boolean isValidCoordinates(Field field, Coordinates coordinates) {
        if (coordinates.getRow() < 0 || coordinates.getRow() >= field.getHeight()) {
            return false;
        }
        if (coordinates.getColumn() < 0 || coordinates.getColumn() >= field.getWidth()) {
            return false;
        }
        return true;
    }

    public static List<Coordinates> getAvailableCoordinates(Field field, Coordinates coordinates) {
        List<Coordinates> availableCoordinates = new ArrayList<>();

        int row = coordinates.getRow();
        int column = coordinates.getColumn();

        if (Coordinates.isValidCoordinates(field, new Coordinates(row + 1, column))) {
            availableCoordinates.add(new Coordinates(row + 1, column));
        }
        if (Coordinates.isValidCoordinates(field, new Coordinates(row - 1, column))) {
            availableCoordinates.add(new Coordinates(row - 1, column));
        }
        if (Coordinates.isValidCoordinates(field, new Coordinates(row, column + 1))) {
            availableCoordinates.add(new Coordinates(row, column + 1));
        }
        if (Coordinates.isValidCoordinates(field, new Coordinates(row, column - 1))) {
            availableCoordinates.add(new Coordinates(row, column - 1));
        }

        return availableCoordinates;
    }
}
