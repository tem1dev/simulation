package coordinates;

import field.Field;

import java.util.ArrayList;
import java.util.List;


public class CoordinatesNavigator {

    public static List<Coordinates> getAvailableCoordinates(Field field, Coordinates coordinates) {
        List<coordinates.Coordinates> availableCoordinates = new ArrayList<>();

        int row = coordinates.getRow();
        int column = coordinates.getColumn();

        if (isValidCoordinates(field, new coordinates.Coordinates(row + 1, column))) {
            availableCoordinates.add(new coordinates.Coordinates(row + 1, column));
        }
        if (isValidCoordinates(field, new coordinates.Coordinates(row - 1, column))) {
            availableCoordinates.add(new coordinates.Coordinates(row - 1, column));
        }
        if (isValidCoordinates(field, new coordinates.Coordinates(row, column + 1))) {
            availableCoordinates.add(new coordinates.Coordinates(row, column + 1));
        }
        if (isValidCoordinates(field, new coordinates.Coordinates(row, column - 1))) {
            availableCoordinates.add(new coordinates.Coordinates(row, column - 1));
        }

        return availableCoordinates;
    }

    public static boolean isValidCoordinates(Field field, coordinates.Coordinates coordinates) {
        if (coordinates.getRow() < 0 || coordinates.getRow() >= field.getHeight()) {
            return false;
        }
        if (coordinates.getColumn() < 0 || coordinates.getColumn() >= field.getWidth()) {
            return false;
        }
        return true;
    }
}
