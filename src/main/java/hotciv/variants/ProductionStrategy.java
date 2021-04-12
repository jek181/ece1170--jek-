package hotciv.variants;

import hotciv.framework.*;
import hotciv.framework.Game;

public interface ProductionStrategy {


    public Production Produce(Game currentGame, Position p);
}
