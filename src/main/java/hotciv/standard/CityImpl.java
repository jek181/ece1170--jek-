package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City{

    private Player owner;
    private String produce;
    private int production;
    private String focus;
    private Game game;
    private int food;
    private int pop;


    public CityImpl(Player owner)
    {
        this.owner = owner;
        production = 0;
        pop = 1;
        food = 0;
        focus = GameConstants.productionFocus;
        produce = GameConstants.SETTLER;

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
        return pop;
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

    public void addProduction(int amount)
    {
        production += amount;
    }

    public void addFood(int amount)
    {
        food += amount;
    }

    public int getFood()
    {
        return food;
    }

    public void addPopulation(int amount)
    {
        pop += amount;
    }


}
