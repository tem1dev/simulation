package search;

import field.Coordinates;
import entity.Entity;
import field.Field;

import java.util.List;


public class PathFinder {

    public List<Coordinates> get(Field field, Coordinates begin, Class<? extends Entity> target) {
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        return breadthFirstSearch.execute(field, begin, target);
    }
}
