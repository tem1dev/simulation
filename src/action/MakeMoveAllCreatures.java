package action;

import entity.creature.Creature;
import field.Field;

import java.util.List;

public class MakeMoveAllCreatures implements Action {

    @Override
    public void execute(Field field) {
        List<Creature> creatures = field.getAllCreaturesOnField();
        for (Creature creature : creatures) {
            creature.makeMove(field);
        }
    }
}
