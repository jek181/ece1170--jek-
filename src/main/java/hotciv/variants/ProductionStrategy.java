package hotciv.variants;

import hotciv.framework.*;

public interface ProductionStrategy {


    public Production Produce(Game currentGame, Position p);
}
