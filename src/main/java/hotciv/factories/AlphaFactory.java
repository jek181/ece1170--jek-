package hotciv.factories;

import hotciv.variants.alphaciv.*;
import hotciv.variants.strategies.*;

public class AlphaFactory implements GameFactory {

    public WorldAgingStrategy makeAgeStrategy()
    {
        return new AlphaWorldAgingStrategy();
    }

    public WinnerStrategy makeWinnerStrategy() {
        return new AlphaWinnerStrategy();
    }

    public UnitActionStrategy makeUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

    public AttackStrategy makeAttackStrategy() {
        return new AlphaAttackStrategy();
    }

    @Override
    public ProductionStrategy makeProductionStrategy() {
        return new AlphaProductionStrategy();
    }

    @Override
    public PopulationStrategy makePopulationStrategy() {
        return new AlphaPopulationStrategy();
    }
}
