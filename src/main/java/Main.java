package main.java;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Field field = new Field(10, 30);
        ConsoleRenderer consoleRenderer = new ConsoleRenderer();
        Simulation simulation = new Simulation(field, consoleRenderer);
        simulation.start();
    }
}