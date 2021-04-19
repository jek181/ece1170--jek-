package hotciv.factories;

import hotciv.variants.alphaciv.AlphaPopulationStrategy;
import hotciv.variants.alphaciv.AlphaProductionStrategy;
import hotciv.variants.alphaciv.AlphaWorldAgingStrategy;
import hotciv.variants.epsilonciv.EpsilonAttackStrategy;
import hotciv.variants.epsilonciv.EpsilonWinnerStrategy;
import hotciv.variants.epsilonciv.RandomDie;
import hotciv.variants.gammaciv.GammaUnitActionStrategy;
import hotciv.variants.strategies.*;

public class EpsilonFactory implements GameFactory{

    @Override
    public WorldAgingStrategy makeAgeStrategy()
    {
        return new AlphaWorldAgingStrategy();
    }

    @Override
    public WinnerStrategy makeWinnerStrategy() {
        return new EpsilonWinnerStrategy();
    }

    @Override
    public UnitActionStrategy makeUnitActionStrategy() {
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
