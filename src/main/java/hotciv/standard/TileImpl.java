package hotciv.standard;


import hotciv.framework.*;


public class TileImpl implements Tile {

    String type;

    public TileImpl(String type)
    {
        this.type = type;
    }
    public String getTypeString()
    {
        return type;
    }


}
