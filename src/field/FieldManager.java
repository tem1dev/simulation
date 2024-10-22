package field;

import coordinates.Coordinates;
import entity.Entity;
import entity.creature.Creature;
import entity.creature.Turtle;

import java.util.ArrayList;
import java.util.List;

public class FieldManager {

    public List<Coordinates> getFreeCoordinates(Field field) {
        List<Coordinates> freeCoordinates = new ArrayList<>();
        for (int row = 0; row < field.getHeight(); row++) {
            for (int column = 0; column < field.getWidth(); column++) {
                Coordinates currentCoordinates = new Coordinates(row, column);
                if (!field.containsEntity(currentCoordinates)) {
                    freeCoordinates.add(currentCoordinates);
                }
            }
        }
        return freeCoordinates;
    }

    public List<Creature> getAllCreaturesOnField(Field field) {
        List<Creature> creatures = new ArrayList<>();
        for (int row = 0; row < field.getHeight(); row++) {
            for (int column = 0; column < field.getWidth(); column++) {
                if (field.containsEntity(new Coordinates(row, column))) {
                    Entity entity = field.getEntity(new Coordinates(row, column));
                    if (entity instanceof Creature creature) {
                        creatures.add(creature);
                    }
                }
            }
        }
        return creatures;
    }

    public boolean containsTurtles(Field field) {
        for (Creature creature : getAllCreaturesOnField(field)) {
            if (creature instanceof Turtle) {
                return true;
            }
        }
        return false;
    }
}
