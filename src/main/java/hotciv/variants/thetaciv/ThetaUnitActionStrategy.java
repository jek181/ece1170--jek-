package hotciv.variants.thetaciv;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.strategies.UnitActionStrategy;

import java.util.List;

public class ThetaUnitActionStrategy implements UnitActionStrategy {

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

        boolean Ufo = unit.getTypeString().equals("ufo");
        boolean forest = t.equals("forest");
        boolean Settler = unit.getTypeString().equals("settler");
        boolean Archer  = unit.getTypeString().equals("archer");

        //If the unit is a settler
        if(Settler)
        {
            //Erase the unit
            game.eraseUnit(p);
            //Add a city belonging to the units owner
            game.addCity(p,new CityImpl(unit.getOwner()));

            return true;
        }

        //If the unit is an archer
        if(Archer)
        {
            //Acquire the unit implementation
            UnitImpl unitArcher = (UnitImpl) unit;
            //Set the fortification of the archer
            unitArcher.setFortify();
            return true;
        }


        List<City> cities = game.getCities();
        City ufoCity = currentGame.getCityAt(p);
        if (Ufo)

        if (ufoCity != null) {
            int population = ufoCity.getSize();
            if (population >= 1) {
                CityImpl city = (CityImpl) ufoCity;
                city.addPopulation(population -1);
            }
            if (ufoCity.getSize() == 0){
                for(City c : cities) {
                    CityImpl city = (CityImpl) c;

                    cities.remove(city);
                }
            }
        }
            /*
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

            }*/
            if(forest) {
            tiles[row][col] = new TileImpl(GameConstants.PLAINS);
        }
        return false;

        }



    }

