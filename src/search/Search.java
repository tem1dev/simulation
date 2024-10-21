import field.Coordinates;
import field.Field;

import java.util.Map;

public interface Search {
    Map<Coordinates, Coordinates> breadthFirstSearch(Field field, Coordinates begin, Class<?> target);
}
