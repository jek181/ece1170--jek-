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
        return moveCount;
    }

    public int getDefensiveStrength()
    {
        if(type == GameConstants.SETTLER)
        {
            strength = 3;
        }
        if(type == GameConstants.LEGION)
        {
            strength = 2;
        }
        else
        {
            strength = 3;
        }
        if(fortify)
        {
            return strength*2;
        }
        return strength;
    }

    public int getAttackingStrength()
    {
        return 0;
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
