package hotciv.factories;

import hotciv.variants.*;

public class GammaFactory implements GameFactory {

    public WorldAgingStrategy makeAgeStrategy()
    {
        return new AlphaWorldAgingStrategy();
    }

    public WinnerStrategy makeWinnerStrategy() {
        return new AlphaWinnerStrategy();
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
