package hotciv.variants.alphaciv;

import hotciv.framework.*;
import hotciv.framework.Game;
import hotciv.variants.strategies.Production;
import hotciv.variants.strategies.ProductionStrategy;

public class AlphaProductionStrategy implements ProductionStrategy {

    @Override
    public Production Produce(Game currentGame, Position p)
    {
        return new Production(0, 6);
    }
}
