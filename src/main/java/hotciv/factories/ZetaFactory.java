package hotciv.factories;

import hotciv.variants.*;

public class ZetaFactory implements GameFactory {

    @Override
    public WorldAgingStrategy makeAgeStrategy()
    {
        return new AlphaWorldAgingStrategy();
    }

    @Override
    public WinnerStrategy makeWinnerStrategy() {
        return new ZetaWinnerStrategy(new BetaWinnerStrategy(), new EpsilonWinnerStrategy(20));
    }

    @Override
    public UnitActionStrategy makeUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

    @Override
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
