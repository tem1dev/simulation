package main.java.action.search;

import main.java.action.Coordinates;
import main.java.action.Field;
import main.java.action.entity.Entity;

import java.util.List;

public interface Search {
    List<main.java.action.Coordinates> execute(Field field, Coordinates begin, Class<? extends Entity> target);
}
