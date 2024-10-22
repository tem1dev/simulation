package action;

import coordinates.Coordinates;
import entity.stationary.*;
import entity.creature.*;
import field.Field;
import field.FieldManager;
import search.BreadthFirstSearch;

import java.util.Iterator;
import java.util.List;
import java.util.Collections;

public class RandomEntityPlacer implements Action {
    private final int COUNTER_OF_CORALS = 10;
    private final int COUNTER_OF_SHELLS = 15;
    private final int COUNTER_OF_SEAWEEDS = 20;
    private final int COUNTER_OF_TURTLES = 3;
    private final int COUNTER_OF_SHARKS = 2;
    private static final FieldManager fieldManager = new FieldManager();

    @Override
    public void execute(Field field) {
        List<Coordinates> freeCoordinates = fieldManager.getFreeCoordinates(field);
        Collections.shuffle(freeCoordinates);
        int totalOfEntities = COUNTER_OF_CORALS + COUNTER_OF_SHELLS + COUNTER_OF_SEAWEEDS + COUNTER_OF_TURTLES + COUNTER_OF_SHARKS;

        if (freeCoordinates.size() < totalOfEntities) {
            throw new IllegalStateException("Entities more than free coordinates on the field. Need more size of field");
        }

        Iterator<Coordinates> iterator = freeCoordinates.iterator();
        // place corals
        for (int i = 0; i < COUNTER_OF_CORALS; i++) {
            field.setEntity(new Coral(), iterator.next());
        }
        // place shells
        for (int i = 0; i < COUNTER_OF_SHELLS; i++) {
            field.setEntity(new Shell(), iterator.next());
        }
        // place seaweeds
        for (int i = 0; i < COUNTER_OF_SEAWEEDS; i++) {
            field.setEntity(new Seaweed(), iterator.next());
        }
        // place turtles
        for (int i = 0; i < COUNTER_OF_TURTLES; i++) {
            Coordinates coordinates = iterator.next();
            field.setEntity(new Turtle(coordinates, new BreadthFirstSearch()), coordinates);
        }
        // place sharks
        for (int i = 0; i < COUNTER_OF_SHARKS; i++) {
            Coordinates coordinates = iterator.next();
            field.setEntity(new Shark(coordinates, new BreadthFirstSearch()), coordinates);
        }
    }
}
