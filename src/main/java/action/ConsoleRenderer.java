package main.java.action;

import main.java.action.entity.*;
import main.java.action.entity.creature.Shark;
import main.java.action.entity.creature.Turtle;
import main.java.action.entity.stationary.Coral;
import main.java.action.entity.stationary.Sea;
import main.java.action.entity.stationary.Seaweed;
import main.java.action.entity.stationary.Shell;

public class ConsoleRenderer {
    private final String SHELL = "\uD83D\uDC1A";
    private final String SEA = "\uD83D\uDFE6";
    private final String CORAL = "\uD83E\uDEB8";
    private final String SEAWEED = "\uD83C\uDF3F";
    private final String SHARK = "\uD83E\uDD88";
    private final String TURTLE = "\uD83D\uDC22";

    public void render(Field field) {
        for (int row = 0; row < field.getHeight(); row++) {
            for (int column = 0; column < field.getWidth(); column++) {
                Coordinates currentCoordinates = new Coordinates(row, column);
                if (field.isEmptySquare(currentCoordinates)) {
                    System.out.print(getSpriteForEntity(new Sea()));
                } else {
                    System.out.print(getSpriteForEntity(field.getEntity(currentCoordinates)));
                }
            }
            System.out.println();
        }
    }

    private String getSpriteForEntity(Entity entity) {
        if (entity instanceof Seaweed) {
            return SEAWEED;
        } else if (entity instanceof Shell) {
            return SHELL;
        } else if (entity instanceof Sea) {
            return SEA;
        } else if (entity instanceof Coral) {
            return CORAL;
        } else if (entity instanceof Shark) {
            return SHARK;
        } else if (entity instanceof Turtle) {
            return TURTLE;
        }
        throw new IllegalArgumentException("Unknown type of main.java.action.entity");
    }
}
