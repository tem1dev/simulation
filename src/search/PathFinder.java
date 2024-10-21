import field.Coordinates;
import entity.Entity;
import entity.stationary.Sea;
import field.Field;

import java.util.*;

public class PathFinder implements Search {
    private Coordinates targetCoordinates;

    @Override
    public Map<Coordinates, Coordinates> breadthFirstSearch(Field field, Coordinates begin, Class<?> target) {
        Map<Coordinates, Coordinates> pathToTarget = new HashMap<>();
        Set<Coordinates> usedCoordinates = new HashSet<>();
        Queue<Coordinates> searchQueue = new LinkedList<>();

        usedCoordinates.add(begin);
        searchQueue.add(begin);

        while (!searchQueue.isEmpty()) {
            Coordinates currentCoordinates = searchQueue.poll();

            for (Coordinates neighbor : getAvailableCoordinates(field, currentCoordinates)) {
                if (!usedCoordinates.contains(neighbor)) {
                    Entity currentEntity = field.getEntity(neighbor);
                    if (currentEntity instanceof Sea) {
                        pathToTarget.put(neighbor, currentCoordinates);
                        searchQueue.add(neighbor);
                    }
                    if (target.isAssignableFrom(currentEntity.getClass())) {
                        targetCoordinates = neighbor;
                        pathToTarget.put(neighbor, currentCoordinates);
                        return pathToTarget;
                    }
                    usedCoordinates.add(neighbor);
                }
            }
        }
        return pathToTarget;
    }

    public List<Coordinates> getPath(Map<Coordinates, Coordinates> pathToTarget) {
        List<Coordinates> result = new LinkedList<>();
        if (targetCoordinates == null) {
            return result;
        }
        result.add(targetCoordinates);
        Coordinates currentCoordinates = pathToTarget.get(targetCoordinates);
        while (currentCoordinates != null) {
            result.add(0, currentCoordinates);
            currentCoordinates = pathToTarget.get(currentCoordinates);
        }
        return result;
    }

    public Coordinates getTargetCoordinates() {
        return targetCoordinates;
    }

    private List<Coordinates> getAvailableCoordinates(Field field, Coordinates coordinates) {
        List<Coordinates> availableCoordinates = new ArrayList<>();

        int row = coordinates.getRow();
        int column = coordinates.getColumn();

        if (isAvailableCoordinates(new Coordinates(row + 1, column), field)) {
            availableCoordinates.add(new Coordinates(row + 1, column));
        }
        if (isAvailableCoordinates(new Coordinates(row - 1, column), field)) {
            availableCoordinates.add(new Coordinates(row - 1, column));
        }
        if (isAvailableCoordinates(new Coordinates(row, column + 1), field)) {
            availableCoordinates.add(new Coordinates(row, column + 1));
        }
        if (isAvailableCoordinates(new Coordinates(row, column - 1), field)) {
            availableCoordinates.add(new Coordinates(row, column - 1));
        }

        return availableCoordinates;
    }

    private boolean isAvailableCoordinates(Coordinates coordinates, Field field) {
        if (coordinates.getRow() < 0 || coordinates.getRow() >= field.getRows()) {
            return false;
        }
        if (coordinates.getColumn() < 0 || coordinates.getColumn() >= field.getColumns()) {
            return false;
        }
        return true;
    }
}
