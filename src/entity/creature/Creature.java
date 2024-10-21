package entity.creature;

import entity.Entity;
import field.Coordinates;
import field.Field;

public abstract class Creature extends Entity {
    private final int speed;
    private int health;
    private final Class<? extends Entity> food;
    private Coordinates coordinates;

    public Creature(int speed, int health, Class<? extends Entity> food, Coordinates coordinates) {
        this.speed = speed;
        this.health = health;
        this.food = food;
        this.coordinates = coordinates;
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

    public abstract void makeMove(Field field);
}
