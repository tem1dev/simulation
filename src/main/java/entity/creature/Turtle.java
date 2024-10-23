package main.java.action.entity.creature;

import main.java.action.entity.Entity;
import main.java.action.entity.stationary.Sea;
import main.java.action.entity.stationary.Seaweed;
import main.java.action.Field;
import main.java.action.search.Search;

public class Turtle extends Creature {
    private static final int SPEED = 2;
    private static final int HEALTH = 120;
    private static final int BONUS_FOR_EATING = 20;
    private static final Class<? extends Entity> FOOD = Seaweed.class;

    public Turtle(main.java.action.Coordinates coordinates, Search pathFinder) {
        super(SPEED, HEALTH, FOOD, coordinates, pathFinder);
    }

    @Override
    public void moving(Field field, main.java.action.Coordinates nextStep, Entity currentEntity) {
        if (currentEntity.getClass().isAssignableFrom(getFood())) {
            setHealth(getHealth() + BONUS_FOR_EATING);
        }
        field.setEntity(new Sea(), getCoordinates());
        setCoordinates(nextStep);
        field.setEntity(this, nextStep);
    }
}