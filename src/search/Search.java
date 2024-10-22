package search;

import coordinates.Coordinates;
import entity.Entity;
import field.Field;

import java.util.List;

public interface Search {
    List<Coordinates> execute(Field field, Coordinates begin, Class<? extends Entity> target);
}
