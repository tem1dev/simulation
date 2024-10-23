package main.java.entity.creature;

import main.java.Coordinates;
import main.java.entity.Entity;
import main.java.entity.stationary.Sea;
import main.java.Field;
import main.java.search.Search;

public class Shark extends Creature {
    private static final int SPEED = 4;
    private static final int HEALTH = 200;
    private static final Class<? extends Entity> FOOD = Turtle.class;
    private static final int ATTACK = 20;

    public Shark(Coordinates coordinates, Search pathFinder) {
        super(SPEED, HEALTH, FOOD, coordinates, pathFinder);
    }

    @Override
    public void moving(Field field, Coordinates nextStep, Entity currentEntity) {
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
