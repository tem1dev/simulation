import entity.*;

import java.util.List;
import java.util.Random;

public class RandomPlaceEntitiesOnField implements Action {
    private final int COUNTER_OF_CORALS = 3;
    private final int COUNTER_OF_SHELLS = 3;
    private final int COUNTER_OF_SEAWEEDS = 5;
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
            placeEntityRandomly(new Turtle(), freeCoordinates, field);
        }
        // place sharks
        for (int i = 0; i < COUNTER_OF_SHARKS; i++) {
            placeEntityRandomly(new Shark(), freeCoordinates, field);
        }
        // rest
        for (Coordinates coordinates : freeCoordinates) {
            field.setEntity(new Sea(), coordinates);
        }
    }

    private void placeEntityRandomly(Entity entity, List<Coordinates> freeCoordinates, Field field) {
        if (freeCoordinates.isEmpty()) {
            return;
        }
        Coordinates coordinates = freeCoordinates.get(random.nextInt(freeCoordinates.size()));
        field.setEntity(entity, coordinates);
        freeCoordinates.remove(coordinates);
    }
}
