package hotciv.variants.strategies;


import hotciv.framework.City;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

public interface WorldGeneration {

    public Tile[][] Tiles();

    public City[][] Cities();

    public Unit[][] Units();

}
