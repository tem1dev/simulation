package entity.creature;

import entity.Entity;
import entity.stationary.Sea;
import entity.stationary.Seaweed;
import field.Coordinates;
import field.Field;
import search.PathFinder;

import java.util.List;

public class Turtle extends Creature {
    private static final int SPEED = 2;
    private static int HEALTH = 120;
    private static final int BONUS_FOR_EATING = 20;
    private static final Class<? extends Entity> FOOD = Seaweed.class;

    public Turtle(Coordinates coordinates) {
        super(SPEED, HEALTH, FOOD, coordinates);
    }

    @Override
    public void makeMove(Field field) {
        PathFinder pathFinder = new PathFinder();
        for (int step = 0; step < getSpeed(); step++) {
            List<Coordinates> pathToTarget = pathFinder.get(field, getCoordinates(), FOOD);
            if (pathToTarget.size() < 2) {
                return;
            }
            Coordinates nextStep = pathToTarget.get(1);
            Entity currentEntity = field.getEntity(nextStep);
            if (currentEntity.getClass().isAssignableFrom(getFood())) {
                setHealth(getHealth() + BONUS_FOR_EATING);
            }
            field.setEntity(new Sea(), getCoordinates());
            setCoordinates(nextStep);
            field.setEntity(this, nextStep);
        }
    }
}