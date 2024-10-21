package entity;

public class Shark extends Creature {
    private static final int SPEED = 2;
    private static final int HEALTH = 100;
    private static final Class<? extends Entity> FOOD = Turtle.class;
    private static final int ATTACK = 20;

    public Shark() {
        super(SPEED, HEALTH, FOOD);
    }

    @Override
    public void makeMove() {

    }
}
