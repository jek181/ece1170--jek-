package hotciv.variants.strategies;

import hotciv.framework.*;
import hotciv.framework.Game;
import hotciv.variants.strategies.Production;

public interface ProductionStrategy {


    public Production Produce(Game currentGame, Position p);
}
