package search;

import entity.Entity;
import entity.stationary.Sea;
import field.Coordinates;
import field.Field;

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
            for (Coordinates neighbor : Coordinates.getAvailableCoordinates(field, currentCoordinates)) {
                if (!usedCoordinates.contains(neighbor)) {
                    Entity currentEntity = field.getEntity(neighbor);
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
}
