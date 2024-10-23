package main.java.action.action;

import main.java.action.Field;
import main.java.action.entity.Entity;
import main.java.action.entity.creature.Creature;

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
                if (field.containsEntity(new main.java.action.Coordinates(row, column))) {
                    Entity entity = field.getEntity(new main.java.action.Coordinates(row, column));
                    if (entity instanceof Creature creature) {
                        creatures.add(creature);
                    }
                }
            }
        }
        return creatures;
    }
}
