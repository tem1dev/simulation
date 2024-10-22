package entity.creature;

import entity.Entity;
import coordinates.Coordinates;
import entity.stationary.Sea;
import field.Field;
import search.Search;

import java.util.List;

public abstract class Creature extends Entity {
    private final int speed;
    private int health;
    private final Class<? extends Entity> food;
    private Coordinates coordinates;
    protected Search pathFinder;

    public Creature(int speed, int health, Class<? extends Entity> food, Coordinates coordinates, Search pathFinder) {
        this.speed = speed;
        this.health = health;
        this.food = food;
        this.coordinates = coordinates;
        this.pathFinder = pathFinder;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public Class<? extends Entity> getFood() {
        return food;
    }

    public void makeMove(Field field) {
        for (int step = 0; step < getSpeed(); step++) {
            List<Coordinates> pathToTarget = pathFinder.execute(field, getCoordinates(), getFood());
            if (pathToTarget.size() < 2) {
                return;
            }
            Coordinates nextStep = pathToTarget.get(1);
            Entity currentEntity;
            if (!field.containsEntity(nextStep)) {
                currentEntity = new Sea();
            } else {
                currentEntity = field.getEntity(nextStep);
            }
            moving(field, nextStep, currentEntity);
        }
    }

    public abstract void moving(Field field, Coordinates nextStep, Entity currentEntity);
}
