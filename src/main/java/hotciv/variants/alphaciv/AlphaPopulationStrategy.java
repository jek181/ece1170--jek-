package hotciv.variants.alphaciv;

import hotciv.variants.strategies.PopulationStrategy;

public class AlphaPopulationStrategy implements PopulationStrategy {

    @Override
    public int populationIncrease(int citySize)
    {
        return -1;
    }

    @Override
    public int populationLimit() {
        return 1;
    }
}
