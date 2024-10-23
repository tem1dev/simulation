package main.java.search;

import main.java.Coordinates;
import main.java.Field;
import main.java.entity.Entity;

import java.util.List;

public interface Search {
    List<Coordinates> execute(Field field, Coordinates begin, Class<? extends Entity> target);
}
