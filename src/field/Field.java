import entity.Coordinates;
import entity.Entity;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class Field {
    private final int rows;
    private final int columns;
    private final Map<Coordinates, Entity> field;

    public Field(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.field = new HashMap<>(rows * columns);
    }

    public void setEntity(Entity entity, Coordinates coordinates) {
        field.put(coordinates, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        if (containsEntity(coordinates)) {
            return field.get(coordinates);
        }
        throw new NoSuchElementException("Field doesn't contain entity on these coordinates");
    }

    public void removeEntity(Coordinates coordinates) {
        field.remove(coordinates);
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    private boolean containsEntity(Coordinates coordinates) {
        return !(field.get(coordinates) == null);
    }

    public List getFreeCoordinates() {
        List<Coordinates> freeCoordinates = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Coordinates currentCoordinates = new Coordinates(row, column);
                if (!containsEntity(currentCoordinates)) {
                    freeCoordinates.add(currentCoordinates);
                }
            }
        }
        return freeCoordinates;
    }
}

