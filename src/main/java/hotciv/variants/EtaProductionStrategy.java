package hotciv.variants;


import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.*;
import hotciv.standard.GameImpl;

import java.util.*;

public class EtaProductionStrategy implements ProductionStrategy{

    @Override
    public Production Produce(Game currentGame, Position p) {

        GameImpl game = (GameImpl) currentGame;
        int food = 0;
        int production = 0;
        List<Position> position;
        City city = currentGame.getCityAt(p);
        int dist = 0;
        int population = city.getSize();

        while(population > 0)
        {
            //The production is based off just the city tile
            if(dist==0)
            {
                food += 1;
                production += 1;
                population--;
            }
            else
            {

                position = game.getPositions(p, dist);
                population -= position.size();
                int openFields;
                //Shows how many fields/tiles can get produced from
                if(population > 0)
                {
                    openFields = position.size();
                }
                else
                {
                    openFields = population + position.size();
                }

                //This goes to a method where the collection process collects for produce
                Production pro = produceCollection(game, position, openFields, city);
                //Add the collected food and produce
                food += pro.getFood();
                production += pro.getProduce();
            }

            dist++;
        }

        return new Production(food, production);
    }

    private Production produceCollection(Game game, List<Position> position, int size, City city)
    {
        List<Tile> tiles = new ArrayList<Tile>();
        int food = 0;
        int production = 0;
        //Add the tiles collecting from
        for(Position pos : position)
        {
            tiles.add(game.getTileAt(pos));
        }

        //Helps pick which tiles you should be collecting from depends on the work force focus
        if(city.getWorkforceFocus().equals(GameConstants.foodFocus))
        {
            Collections.sort(tiles, this::foodCompare);
        }
        else
        {
            Collections.sort(tiles, this::productionCompare);
        }
        for(int i=0; i<size; i++)
        {
            //Adds the constants from collection
            Tile t = tiles.get(i);
            food += foodConstants(t.getTypeString());
            production += productionConstants(t.getTypeString());
        }

        return new Production(food, production);
    }

    public int foodCompare(Tile tile1, Tile tile2)
    {
        int p1 = productionConstants(tile1.getTypeString());
        int p2 = productionConstants(tile2.getTypeString());

        int f1 = foodConstants(tile1.getTypeString());
        int f2 = foodConstants(tile2.getTypeString());

        if(f1 - f2 == 0)
        {
            return p2 - p1;
        }
        return f2 - f1;
    }

    public int productionCompare(Tile tile1, Tile tile2)
    {
        int p1 = productionConstants(tile1.getTypeString());
        int p2 = productionConstants(tile2.getTypeString());

        int f1 = foodConstants(tile1.getTypeString());
        int f2 = foodConstants(tile2.getTypeString());

        if(p1 - p2 == 0)
        {
            return f2 - f1;
        }
        return p2 - p1;
    }

    public static int productionConstants(String tileType)
    {
        if(tileType.equals(GameConstants.FOREST))
        {
            return 3;
        }
        if(tileType.equals(GameConstants.HILLS))
        {
            return 2;
        }
        if(tileType.equals(GameConstants.MOUNTAINS))
        {
            return 1;
        }
        return 0;
    }

    public static int foodConstants(String tileType)
    {
        if(tileType.equals(GameConstants.PLAINS))
        {
            return 3;
        }
        if(tileType.equals(GameConstants.OCEANS))
        {
            return 1;
        }
        return 0;
    }
}
