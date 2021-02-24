package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    private String type;
    private Player owner;
    private int moveCount=1;

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
        return 0;
    }

    public int getAttackingStrength()
    {
        return 0;
    }

}
