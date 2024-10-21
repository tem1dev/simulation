package entity;

public class Turtle extends Creature {
    private static final int SPEED = 3;
    private static final int HEALTH = 80;
    private static final Class<? extends Entity> FOOD = Seaweed.class;

    public Turtle() {
        super(SPEED, HEALTH, FOOD);
    }

    @Override
    public void makeMove() {

    }
}