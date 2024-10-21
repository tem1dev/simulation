package entity;

public abstract class Creature extends Entity {
    private final int speed;
    private final int health;
    private final Class<? extends Entity> food;

    public Creature(int speed, int health, Class<? extends Entity> food) {
        this.speed = speed;
        this.health = health;
        this.food = food;
    }

    public abstract void makeMove();
}
