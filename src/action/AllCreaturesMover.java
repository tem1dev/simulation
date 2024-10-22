package action;

import entity.creature.Creature;
import field.Field;
import field.FieldManager;

import java.util.List;

public class AllCreaturesMover implements Action {
    private final FieldManager fieldManager;

    public AllCreaturesMover(FieldManager fieldManager) {
        this.fieldManager = fieldManager;
    }

    @Override
    public void execute(Field field) {
        List<Creature> creatures = fieldManager.getAllCreaturesOnField(field);
        for (Creature creature : creatures) {
            creature.makeMove(field);
        }
    }
}
