package hotciv.factories;

import hotciv.variants.alphaciv.AlphaPopulationStrategy;
import hotciv.variants.alphaciv.AlphaProductionStrategy;
import hotciv.variants.betaciv.BetaWinnerStrategy;
import hotciv.variants.betaciv.BetaWorldAgingStrategy;
import hotciv.variants.epsilonciv.EpsilonAttackStrategy;
import hotciv.variants.epsilonciv.RandomDie;
import hotciv.variants.gammaciv.GammaUnitActionStrategy;
import hotciv.variants.strategies.*;

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
