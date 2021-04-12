package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;

import java.util.List;

public class ThetaUnitActionStrategy implements UnitActionStrategy{

    @Override
    public boolean unitAction(Position p, Game currentGame) {

        Unit unit = currentGame.getUnitAt(p);
        GameImpl game = (GameImpl) currentGame;
        Tile tile = currentGame.getTileAt(p);

        String t = tile.getTypeString();

        int size = GameConstants.WORLDSIZE;
        Tile[][] tiles = new TileImpl[size][size];
        int row = p.getRow();
        int col = p.getColumn();


        //If there is no unit
        if (unit == null) {
            return false;
        }

        boolean Ufo = unit.getTypeString().equals(GameConstants.UFO);
        boolean forest = t.equals(GameConstants.FOREST);



        List<City> cities = game.getCities();
        if (Ufo)
        {
            for (City c : cities)
            {
                if (c != null)
                {
                    CityImpl city = (CityImpl) c;

                    if (city.getSize() > 1)
                    {
                        city.addPopulation(-1);
                    }
                    else if (city.getSize() == 1)
                    {
                        cities.remove(city);
                    }
                }

            }
            if(forest) {
            tiles[row][col] = new TileImpl(GameConstants.PLAINS);
        }
            return true;

        }


        return false;
    }
}
