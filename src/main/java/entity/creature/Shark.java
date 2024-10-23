package main.java.action.entity.creature;

import main.java.action.Coordinates;
import main.java.action.entity.Entity;
import main.java.action.entity.stationary.Sea;
import main.java.action.Field;
import main.java.action.search.Search;

public class Shark extends Creature {
    private static final int SPEED = 4;
    private static final int HEALTH = 200;
    private static final Class<? extends Entity> FOOD = Turtle.class;
    private static final int ATTACK = 20;

    public Shark(Coordinates coordinates, Search pathFinder) {
        super(SPEED, HEALTH, FOOD, coordinates, pathFinder);
    }

    @Override
    public void moving(Field field, main.java.action.Coordinates nextStep, Entity currentEntity) {
        if (currentEntity.getClass().isAssignableFrom(getFood())) {
            int targetHealth = ((Turtle) (currentEntity)).getHealth() - ATTACK;
            if (targetHealth > 0) {
                ((Turtle) (currentEntity)).setHealth(targetHealth);
                return;
            }
        }
        field.setEntity(new Sea(), getCoordinates());
        setCoordinates(nextStep);
        field.setEntity(this, nextStep);
    }
}
