package hotciv.variants;

import hotciv.framework.*;
import hotciv.framework.Game;

public class AlphaProductionStrategy implements ProductionStrategy{

    public Production Produce(Game currentGame, Position p)
    {
        return new Production(0, 6);
    }
}
