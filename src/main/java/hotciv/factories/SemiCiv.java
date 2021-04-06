package hotciv.factories;

import hotciv.variants.*;

public class SemiCiv implements GameFactory{

    public WorldAgingStrategy makeAgeStrategy()
    {
        return new BetaWorldAgingStrategy();
    }

    public WinnerStrategy makeWinnerStrategy() {
        return new EpsilonWinnerStrategy();
    }

    public UnitActionStrategy makeUnitActionStrategy() {
        return new GammaUnitActionStrategy();
    }

    public AttackStrategy makeAttackStrategy() {
        return new EpsilonAttackStrategy(new RandomDie());
    }

    @Override
    public ProductionStrategy makeProductionStrategy() {
        return new EtaProductionStrategy();
    }

    @Override
    public PopulationStrategy makePopulationStrategy() {
        return new EtaPopulationStrategy();
    }

}
