package entity.creature;

import entity.Entity;
import entity.stationary.Sea;
import field.Coordinates;
import field.Field;
import search.PathFinder;

import java.util.List;

public class Shark extends Creature {
    private static final int SPEED = 4;
    private static final int HEALTH = 200;
    private static final Class<? extends Entity> FOOD = Turtle.class;
    private static final int ATTACK = 20;

    public Shark(Coordinates coordinates) {
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
}
