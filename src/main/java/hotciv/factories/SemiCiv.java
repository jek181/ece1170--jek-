package hotciv.factories;

import hotciv.variants.betaciv.BetaWorldAgingStrategy;
import hotciv.variants.epsilonciv.EpsilonAttackStrategy;
import hotciv.variants.epsilonciv.EpsilonWinnerStrategy;
import hotciv.variants.epsilonciv.RandomDie;
import hotciv.variants.etaciv.EtaPopulationStrategy;
import hotciv.variants.etaciv.EtaProductionStrategy;
import hotciv.variants.gammaciv.GammaUnitActionStrategy;
import hotciv.variants.strategies.*;

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
