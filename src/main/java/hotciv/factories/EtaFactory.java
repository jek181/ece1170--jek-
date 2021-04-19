package hotciv.factories;

import hotciv.variants.alphaciv.AlphaAttackStrategy;
import hotciv.variants.alphaciv.AlphaUnitActionStrategy;
import hotciv.variants.alphaciv.AlphaWorldAgingStrategy;
import hotciv.variants.betaciv.BetaWinnerStrategy;
import hotciv.variants.epsilonciv.EpsilonWinnerStrategy;
import hotciv.variants.etaciv.EtaPopulationStrategy;
import hotciv.variants.etaciv.EtaProductionStrategy;
import hotciv.variants.strategies.*;
import hotciv.variants.zetaciv.ZetaWinnerStrategy;

public class EtaFactory implements GameFactory {

    @Override
    public WorldAgingStrategy makeAgeStrategy() {
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
        return new EtaProductionStrategy();
    }

    @Override
    public PopulationStrategy makePopulationStrategy() {
        return new EtaPopulationStrategy();
    }
}
