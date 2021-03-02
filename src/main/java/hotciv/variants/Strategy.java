package hotciv.variants;


public class Strategy {

    public WorldAgingStrategy makeAlphaWorldAgingStrategy() { return new AlphaWorldAgingStrategy(); }

    public WinnerStrategy makeAlphaWinnerStrategy() { return new AlphaWinnerStrategy(); }

    public WinnerStrategy makeBetaWinnerStrategy()
    {
        return new BetaWinnerStrategy();
    }

    public WorldAgingStrategy makeBetaAgingStrategy()
    {
        return new BetaWorldAgingStrategy();
    }

    public UnitActionStrategy makeGammaActionStrategy() { return new GammaUnitActionStrategy(); }

    public WorldGeneration makeDeltaWorldLayoutStrategy() { return new DeltaWorldLayoutStrategy(); }

    public WorldGeneration makeAlphaWorldLayoutStrategy() { return new AlphaWorldLayoutStrategy(); }

}
