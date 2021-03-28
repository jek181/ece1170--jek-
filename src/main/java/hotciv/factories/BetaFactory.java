package hotciv.factories;

import hotciv.variants.*;

public class BetaFactory implements GameFactory {

    public WorldAgingStrategy makeAgeStrategy()
    {
        return new BetaWorldAgingStrategy();
    }

    public WinnerStrategy makeWinnerStrategy()
    {
        return new BetaWinnerStrategy();
    }

    public UnitActionStrategy makeUnitActionStrategy()
    {
        return new GammaUnitActionStrategy();
    }

    @Override
    public AttackStrategy makeAttackStrategy() {
        RandomDie randomDie = new RandomDie();
        return new EpsilonAttackStrategy(randomDie);
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
