package main.java;

import main.java.action.Action;
import main.java.action.AllCreaturesMover;
import main.java.action.RandomEntityPlacer;
import main.java.entity.creature.Turtle;

public class Simulation {
    private static final int INITIAL_VALUE_OF_STEPS = 1;
    private static final String FINAL_TEXT = "The end of simulation.";
    private final Action randomEntityPlacer = new RandomEntityPlacer();
    private final AllCreaturesMover allCreaturesMover = new AllCreaturesMover();
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
        while (containsTurtles(field)) {
            showInformationAboutStep();
            nextTurn();
            Thread.sleep(1200);
        }
        showInformationAboutStep();
        consoleRenderer.render(field);
        System.out.println(FINAL_TEXT);
    }

    private void nextTurn() {
        consoleRenderer.render(field);
        allCreaturesMover.execute(field);
        steps++;
    }

    private void showInformationAboutStep() {
        System.out.println("Step: " + steps);
    }

    private boolean containsTurtles(Field field) {
        return allCreaturesMover.getAllCreaturesOnField(field).stream().anyMatch(creature -> creature instanceof Turtle);
    }
}