package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.*;

import java.util.HashMap;

public class DeltaWorldLayoutStrategy implements WorldGeneration {

    int size = GameConstants.WORLDSIZE;

    public Tile[][] Tiles()
    {
        Tile[][] tiles = new TileImpl[size][size];

        HashMap<Position, Tile> theWorld = defineWorld();

        for(int r=0; r<size; r++)
        {
            for(int c=0; c<size; c++)
            {
                tiles[r][c] = theWorld.get(new Position(r,c));
            }
        }
     return tiles;

    }



    public City[][] Cities()
    {
        City[][] cities = new CityImpl[size][size];
        cities[8][12] = new CityImpl(Player.RED);
        cities[4][5] = new CityImpl(Player.BLUE);

        return cities;
    }

    public Unit[][] Units()
    {
        Unit[][] units =new UnitImpl[size][size];
        units[2][0] = new UnitImpl(GameConstants.ARCHER, Player.RED);
        units[4][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);
        units[3][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);

        return units;
    }


    private HashMap<Position,Tile> defineWorld() {
        // Basically we use a 'data driven' approach - code the
        // layout in a simple semi-visual representation, and
        // convert it to the actual Game representation.
        String[] layout = new String[]
                {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };
        // Conversion...
        HashMap<Position, Tile> theWorld = new HashMap<Position, Tile>();
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            line = layout[r];
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char tileChar = line.charAt(c);
                String type = "error";
                if (tileChar == '.') {
                    type = GameConstants.OCEANS;
                }
                if (tileChar == 'o') {
                    type = GameConstants.PLAINS;
                }
                if (tileChar == 'M') {
                    type = GameConstants.MOUNTAINS;
                }
                if (tileChar == 'f') {
                    type = GameConstants.FOREST;
                }
                if (tileChar == 'h') {
                    type = GameConstants.HILLS;
                }
                Position p = new Position(r, c);
                theWorld.put(p, new TileImpl(type));
            }
        }
        return theWorld;
    }

}
