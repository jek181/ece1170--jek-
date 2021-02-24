package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City{

    private Player owner;
    private String produce;
    private int production;
    private String focus;
    private Game game;


    public CityImpl(Player owner)
    {
        this.owner = owner;
        production = 0;


    }

    public void setProduction(String unitType)
    {
        produce = unitType;
    }

    public void setWorkforceFocus(String balance)
    {
        focus = balance;
    }

    public Player getOwner()
    {
        return owner;
    }

    public int getSize()
    {
        return 1;
    }

    public int getTreasury()
    {
        return production;
    }

    public String getProduction()
    {
        return produce;
    }

    public String getWorkforceFocus()
    {
        return focus;
    }

    public void addTreasury(int treasure)
    {
        production += treasure;
    }
}
