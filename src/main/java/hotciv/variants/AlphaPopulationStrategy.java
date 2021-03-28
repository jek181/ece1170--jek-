package hotciv.variants;

public class AlphaPopulationStrategy implements PopulationStrategy{

    public int populationIncrease(int citySize)
    {
        return -1;
    }

    @Override
    public int populationLimit() {
        return 1;
    }
}
