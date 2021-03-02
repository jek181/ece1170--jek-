package hotciv.variants;


import hotciv.framework.*;

import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;


public class AlphaWorldLayoutStrategy implements WorldGeneration{

    int size = GameConstants.WORLDSIZE;

    public Tile[][] Tiles()
    {
        Tile[][] tiles = new TileImpl[size][size];

        //This Places The Tiles
        for(int i=0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                if (i == 0 && j == 1) {
                    tiles[i][j] = new TileImpl(GameConstants.HILLS);
                } else if (i == 1 && j == 0) {
                    tiles[i][j] = new TileImpl(GameConstants.OCEANS);
                } else if (i == 2 && j == 2) {
                    tiles[i][j] = new TileImpl(GameConstants.MOUNTAINS);
                } else {
                    tiles[i][j] = new TileImpl(GameConstants.PLAINS);
                }
            }
        }

        return tiles;

    }

    public City[][] Cities()
    {
        City[][] cities = new CityImpl[size][size];

        cities[4][1] = new CityImpl(Player.BLUE);
        cities[1][1] = new CityImpl(Player.RED);

        return cities;
    }

    public Unit[][] Units()
    {
        Unit[][] units =new UnitImpl[size][size];

        //This Places The Units
        units[2][0] = new UnitImpl(GameConstants.ARCHER, Player.RED);
        units[4][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);
        units[3][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);

        return units;
    }
}
