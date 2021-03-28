package hotciv.variants;

public class EtaPopulationStrategy implements PopulationStrategy{

    public int populationIncrease(int citySize)
    {
        return 5 + citySize*3;
    }

    @Override
    public int populationLimit() {
        return 9;
    }
}
