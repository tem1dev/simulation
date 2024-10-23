package main.java.action;

import main.java.Coordinates;
import main.java.Field;
import main.java.entity.creature.Creature;
import main.java.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class AllCreaturesMover implements Action {

    @Override
    public void execute(Field field) {
        List<Creature> creatures = getAllCreaturesOnField(field);
        for (Creature creature : creatures) {
            creature.makeMove(field);
        }
    }

    public List<Creature> getAllCreaturesOnField(Field field) {
        List<Creature> creatures = new ArrayList<>();
        for (int row = 0; row < field.getHeight(); row++) {
            for (int column = 0; column < field.getWidth(); column++) {
                if (!field.isEmptySquare(new Coordinates(row, column))) {
                    Entity entity = field.getEntity(new Coordinates(row, column));
                    if (entity instanceof Creature creature) {
                        creatures.add(creature);
                    }
                }
            }
        }
        return creatures;
    }
}
