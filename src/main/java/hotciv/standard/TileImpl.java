package hotciv.standard;


import hotciv.framework.*;




public class TileImpl implements Tile {

    String type;
    Position p;

    public TileImpl(String type)
    {
        this.type = type;
    }
    public String getTypeString()
    {
        return type;
    }


    public Position getPosition()
    {

        if(type == "mountain")
        {
            return new Position(2,2);
        }
        else if(type == "hills")
        {
            return new Position(0, 1);
        }
        else if(type == "ocean")
        {
            return new Position(1,0);
        }

        return null;

    }

}
