package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    private String type;
    private Player owner;
    private boolean fortify=false;
    private int moveCount=1;
    private int strength;


    public UnitImpl(String type, Player owner)
    {
        this.type = type;
        this.owner = owner;

    }

    public void setMoveCount(int moveCount)
    {
        this.moveCount = moveCount;
    }

    public String getTypeString()
    {
        return type;
    }

    public Player getOwner()
    {
        return owner;
    }

    public int getMoveCount()
    {
        if(fortify)
        {
            return 0;
        }
        return moveCount;
    }

    public int getDefensiveStrength()
    {
        if(type.equals(GameConstants.SETTLER))
        {
            strength = 3;
        }
        if(type.equals(GameConstants.LEGION))
        {
            strength = 2;
        }
        if(type.equals(GameConstants.ARCHER))
        {
            strength = 3;
            if(fortify)
            {
                return strength*2;
            }
        }
        if(type.equals(GameConstants.UFO))
        {
            strength = 8;
        }

        return strength;
    }

    public int getAttackingStrength()
    {
        strength = 0;
        if(type.equals(GameConstants.ARCHER))
        {
            strength = 2;
        }
        if(type.equals(GameConstants.LEGION))
        {
            strength = 4;
        }
        if(type.equals(GameConstants.SETTLER))
        {
            strength = 0;
        }
        if(type.equals(GameConstants.UFO))
        {
            strength = 1;
        }

        return strength;
    }

    public void setFortify() {
        if(fortify)
        {
            fortify = false;
        }
        else
        {
            fortify = true;
            setMoveCount(0);
        }
    }

}
