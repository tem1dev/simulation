package field;

import entity.Entity;
import entity.creature.Creature;
import entity.creature.Turtle;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class Field {
    private final int height;
    private final int width;
    private final Map<Coordinates, Entity> field;

    public Field(int rows, int columns) {
        this.height = rows;
        this.width = columns;
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
        if (containsEntity(coordinates)) {
            field.remove(coordinates);
        }
        throw new NoSuchElementException("Field doesn't contain entity on these coordinates");
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    private boolean containsEntity(Coordinates coordinates) {
        return !(field.get(coordinates) == null);
    }

    public List<Coordinates> getFreeCoordinates() {
        List<Coordinates> freeCoordinates = new ArrayList<>();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                Coordinates currentCoordinates = new Coordinates(row, column);
                if (!containsEntity(currentCoordinates)) {
                    freeCoordinates.add(currentCoordinates);
                }
            }
        }
        return freeCoordinates;
    }

    public List<Creature> getAllCreaturesOnField() {
        List<Creature> creatures = new ArrayList<>();
        for (int row = 0; row < getHeight(); row++) {
            for (int column = 0; column < getWidth(); column++) {
                Entity entity = getEntity(new Coordinates(row, column));
                if (entity instanceof Creature creature) {
                    creatures.add(creature);
                }
            }
        }
        return creatures;
    }

    public boolean containsTurtles() {
        for (Creature creature : getAllCreaturesOnField()) {
            if (creature instanceof Turtle) {
                return true;
            }
        }
        return false;
    }
}

