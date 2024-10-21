package field;

import entity.*;
import entity.creature.Shark;
import entity.creature.Turtle;
import entity.stationary.Coral;
import entity.stationary.Sea;
import entity.stationary.Seaweed;
import entity.stationary.Shell;

public class ConsoleRenderer {
    private final String SHELL = "\uD83D\uDC1A";
    private final String SEA = "\uD83D\uDFE6";
    private final String CORAL = "\uD83E\uDEB8";
    private final String CRAB = "\uD83E\uDD80";
    private final String SEAWEED = "\uD83C\uDF3F";
    private final String SHARK = "\uD83E\uDD88";
    private final String TURTLE = "\uD83D\uDC22";

    public void render(Field field) {
        for (int row = 0; row < field.getHeight(); row++) {
            for (int column = 0; column < field.getWidth(); column++) {
                Entity currentEntity = field.getEntity(new Coordinates(row, column));
                System.out.print(getSpriteForEntity(currentEntity));
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
        throw new IllegalArgumentException("Unknown type of entity");
    }
}
