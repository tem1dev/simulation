package action;

import entity.*;
import entity.stationary.*;
import entity.creature.*;
import field.Coordinates;
import field.Field;

import java.util.List;
import java.util.Random;

public class RandomPlaceEntitiesOnField implements Action {
    private final int COUNTER_OF_CORALS = 10;
    private final int COUNTER_OF_SHELLS = 15;
    private final int COUNTER_OF_SEAWEEDS = 20;
    private final int COUNTER_OF_TURTLES = 3;
    private final int COUNTER_OF_SHARKS = 2;
    private static final Random random = new Random();

    @Override
    public void execute(Field field) {
        List<Coordinates> freeCoordinates = field.getFreeCoordinates();
        // place corals
        for (int i = 0; i < COUNTER_OF_CORALS; i++) {
            placeEntityRandomly(new Coral(), freeCoordinates, field);
        }
        // place shells
        for (int i = 0; i < COUNTER_OF_SHELLS; i++) {
            placeEntityRandomly(new Shell(), freeCoordinates, field);
        }
        // place seaweeds
        for (int i = 0; i < COUNTER_OF_SEAWEEDS; i++) {
            placeEntityRandomly(new Seaweed(), freeCoordinates, field);
        }
        // place turtles
        for (int i = 0; i < COUNTER_OF_TURTLES; i++) {
            if (freeCoordinates.isEmpty()) {
                return;
            }
            Coordinates coordinates = freeCoordinates.get(random.nextInt(freeCoordinates.size()));
            placeEntityRandomly(new Turtle(coordinates), freeCoordinates, field);
        }
        // place sharks
        for (int i = 0; i < COUNTER_OF_SHARKS; i++) {
            if (freeCoordinates.isEmpty()) {
                return;
            }
            Coordinates coordinates = freeCoordinates.get(random.nextInt(freeCoordinates.size() - 1));
            placeEntityRandomly(new Shark(coordinates), freeCoordinates, field);
        }
        // rest of the entities
        for (Coordinates coordinates : freeCoordinates) {
            field.setEntity(new Sea(), coordinates);
        }
    }

    private void placeEntityRandomly(Entity entity, List<Coordinates> freeCoordinates, Field field) {
        if (freeCoordinates.isEmpty()) {
            return;
        }
        Coordinates coordinates = freeCoordinates.get(random.nextInt(freeCoordinates.size() - 1));
        field.setEntity(entity, coordinates);
        freeCoordinates.remove(coordinates);
    }

    private void placeEntityRandomly(Creature creature, List<Coordinates> freeCoordinates, Field field) {
        field.setEntity(creature, creature.getCoordinates());
        freeCoordinates.remove(creature.getCoordinates());
    }
}
