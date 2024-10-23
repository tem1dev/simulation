package main.java.search;

import main.java.Coordinates;
import main.java.Field;
import main.java.entity.Entity;
import main.java.entity.stationary.Sea;

import java.util.*;

public class BreadthFirstSearch implements Search {

    @Override
    public List<Coordinates> execute(Field field, Coordinates begin, Class<? extends Entity> target) {
        Map<Coordinates, Coordinates> pathToTarget = new HashMap<>();
        Set<Coordinates> usedCoordinates = new HashSet<>();
        Queue<Coordinates> searchQueue = new LinkedList<>();
        Coordinates targetCoordinates = null;
        boolean isFindTarget = false;

        usedCoordinates.add(begin);
        searchQueue.add(begin);

        while (!searchQueue.isEmpty()) {
            if (isFindTarget) {
                break;
            }
            Coordinates currentCoordinates = searchQueue.poll();
            for (Coordinates neighbor : getAvailableCoordinates(field, currentCoordinates)) {
                if (!usedCoordinates.contains(neighbor)) {
                    Entity currentEntity;
                    if (field.isEmptySquare(neighbor)) {
                        currentEntity = new Sea();
                    } else {
                        currentEntity = field.getEntity(neighbor);
                    }

                    if (currentEntity instanceof Sea) {
                        pathToTarget.put(neighbor, currentCoordinates);
                        searchQueue.add(neighbor);
                    }
                    if (currentEntity.getClass().isAssignableFrom(target)) {
                        targetCoordinates = neighbor;
                        pathToTarget.put(neighbor, currentCoordinates);
                        isFindTarget = true;
                        break;
                    }
                    usedCoordinates.add(neighbor);
                }
            }
        }

        List<Coordinates> result = new LinkedList<>();

        result.add(targetCoordinates);

        Coordinates currentCoordinates = pathToTarget.get(targetCoordinates);
        while (currentCoordinates != null) {
            result.add(0, currentCoordinates);
            currentCoordinates = pathToTarget.get(currentCoordinates);
        }

        return result;
    }

    private List<Coordinates> getAvailableCoordinates(Field field, Coordinates coordinates) {
        List<Coordinates> availableCoordinates = new ArrayList<>();

        int row = coordinates.getRow();
        int column = coordinates.getColumn();

        if (field.isValidCoordinates(new Coordinates(row + 1, column))) {
            availableCoordinates.add(new Coordinates(row + 1, column));
        }
        if (field.isValidCoordinates(new Coordinates(row - 1, column))) {
            availableCoordinates.add(new Coordinates(row - 1, column));
        }
        if (field.isValidCoordinates(new Coordinates(row, column + 1))) {
            availableCoordinates.add(new Coordinates(row, column + 1));
        }
        if (field.isValidCoordinates(new Coordinates(row, column - 1))) {
            availableCoordinates.add(new Coordinates(row, column - 1));
        }

        return availableCoordinates;
    }
}
