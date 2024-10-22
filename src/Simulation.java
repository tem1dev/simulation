import field.ConsoleRenderer;
import field.Field;
import action.*;
import field.FieldManager;

public class Simulation {
    private static final int INITIAL_VALUE_OF_STEPS = 1;
    private static final String FINAL_TEXT = "Simulation end.";
    private final FieldManager fieldManager = new FieldManager();
    private final Action randomEntityPlacer = new RandomEntityPlacer();
    private final Action allCreaturesMover = new AllCreaturesMover(fieldManager);
    private final ConsoleRenderer consoleRenderer;
    private final Field field;
    private int steps;

    public Simulation(Field field, ConsoleRenderer consoleRenderer) {
        this.field = field;
        this.consoleRenderer = consoleRenderer;
        this.steps = INITIAL_VALUE_OF_STEPS;
    }

    public void start() throws InterruptedException {
        randomEntityPlacer.execute(field);
        while (fieldManager.containsTurtles(field)) {
            showInformationAboutSteps();
            nextTurn();
            Thread.sleep(1200);
        }
        showInformationAboutSteps();
        consoleRenderer.render(field);
        System.out.println(FINAL_TEXT);
    }

    private void nextTurn() {
        consoleRenderer.render(field);
        allCreaturesMover.execute(field);
        steps++;
    }

    private void showInformationAboutSteps() {
        System.out.println("Step: " + steps);
    }
}