package hotciv.variants.strategies;


import hotciv.variants.alphaciv.AlphaWinnerStrategy;
import hotciv.variants.alphaciv.AlphaWorldAgingStrategy;
import hotciv.variants.alphaciv.AlphaWorldLayoutStrategy;
import hotciv.variants.betaciv.BetaWinnerStrategy;
import hotciv.variants.betaciv.BetaWorldAgingStrategy;
import hotciv.variants.deltaciv.DeltaWorldLayoutStrategy;
import hotciv.variants.gammaciv.GammaUnitActionStrategy;

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

    /*public WorldGeneration makeEpsilonWorldLayoutStrategy() { return new EpsilonWorldLayoutStrategy(String[] Tiles,
            String[] Cities, String[] Units); }*/


}
