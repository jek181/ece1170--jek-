package hotciv.variants.etaciv;

import hotciv.variants.strategies.PopulationStrategy;

public class EtaPopulationStrategy implements PopulationStrategy {

    @Override
    public int populationIncrease(int citySize)
    {
        return 5 + citySize*3;
    }

    @Override
    public int populationLimit() {
        return 9;
    }
}
