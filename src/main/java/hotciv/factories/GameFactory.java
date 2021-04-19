package hotciv.factories;

import hotciv.variants.strategies.*;

public interface GameFactory {

    public WorldAgingStrategy makeAgeStrategy();

    public WinnerStrategy makeWinnerStrategy();

    public UnitActionStrategy makeUnitActionStrategy();

    public AttackStrategy makeAttackStrategy();

    public ProductionStrategy makeProductionStrategy();

    public PopulationStrategy makePopulationStrategy();
}
