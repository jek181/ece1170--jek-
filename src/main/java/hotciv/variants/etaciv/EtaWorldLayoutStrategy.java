package hotciv.variants.etaciv;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.strategies.WorldGeneration;

public class EtaWorldLayoutStrategy implements WorldGeneration {

    int size = GameConstants.WORLDSIZE;

    @Override
    public Tile[][] Tiles()
    {
        Tile[][] tiles = new TileImpl[size][size];

        //This Places The Tiles
        for(int i=0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                if (i == 0 && j == 1) {
                    tiles[i][j] = new TileImpl(GameConstants.HILLS);
                } else if (i == 0 && j == 3) {
                    tiles[i][j] = new TileImpl(GameConstants.OCEANS);
                }
                else if(i == 0 && j == 4)
                {
                    tiles[i][j] = new TileImpl(GameConstants.OCEANS);
                }
                else if(i == 1 && j == 4)
                {
                    tiles[i][j] = new TileImpl(GameConstants.OCEANS);
                }
                else if (i == 1 && j == 1) {
                    tiles[i][j] = new TileImpl(GameConstants.MOUNTAINS);
                }
                else if(i == 1 && j == 2)
                {
                    tiles[i][j] = new TileImpl(GameConstants.FOREST);
                }
                else if(i == 1 && j == 3)
                {
                    tiles[i][j] = new TileImpl(GameConstants.FOREST);
                }

                else {
                    tiles[i][j] = new TileImpl(GameConstants.PLAINS);
                }
            }
        }

        return tiles;

    }

    @Override
    public City[][] Cities()
    {
        City[][] cities = new CityImpl[size][size];

        cities[0][4] = new CityImpl(Player.BLUE);
        cities[0][1] = new CityImpl(Player.RED);

        cities[1][2] = new CityImpl(Player.RED);

        return cities;
    }

    @Override
    public Unit[][] Units()
    {
        Unit[][] units =new UnitImpl[size][size];

        //This Places The Units
        units[2][0] = new UnitImpl(GameConstants.ARCHER, Player.RED);
        units[4][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);
        units[3][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);

        units[1][0] = new UnitImpl(GameConstants.ARCHER, Player.RED);

        units[3][0] = new UnitImpl(GameConstants.ARCHER, Player.RED);

        units[2][1] = new UnitImpl(GameConstants.ARCHER, Player.BLUE);
        units[6][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);

        units[4][2] = new UnitImpl(GameConstants.LEGION, Player.RED);

        units[3][3] = new UnitImpl(GameConstants.LEGION, Player.BLUE);

        units[6][6] = new UnitImpl(GameConstants.LEGION, Player.RED);
        units[7][6] = new UnitImpl(GameConstants.LEGION, Player.BLUE);

        return units;
    }
}
