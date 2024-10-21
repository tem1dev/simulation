import field.ConsoleRenderer;
import field.Field;
import action.*;

public class Simulation {
    private static final int INITIAL_VALUE_OF_STEPS = 0;
    private static final String FINAL_TEXT = "Simulation end.";
    private final Action randomPlaceEntitiesOnField = new RandomPlaceEntitiesOnField();
    private final Action makeMoveAllCreatures = new MakeMoveAllCreatures();
    private Field field;
    private ConsoleRenderer consoleRenderer;
    private int steps;

    public Simulation(Field field, ConsoleRenderer consoleRenderer) {
        this.field = field;
        this.consoleRenderer = consoleRenderer;
        this.steps = INITIAL_VALUE_OF_STEPS;
    }

    public void startSimulation() throws InterruptedException {
        randomPlaceEntitiesOnField.execute(field);
        while (field.containsTurtles()) {
            showInformationAboutSteps();
            nextTurn();
            Thread.sleep(1200);
        }
        consoleRenderer.render(field);
        System.out.println(FINAL_TEXT);
    }

    public void nextTurn() {
        consoleRenderer.render(field);
        makeMoveAllCreatures.execute(field);
        steps++;
    }

    public void showInformationAboutSteps() {
        System.out.println("Step: " + steps);
    }
}