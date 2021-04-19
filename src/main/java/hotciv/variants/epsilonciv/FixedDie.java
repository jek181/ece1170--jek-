package hotciv.variants.epsilonciv;

import hotciv.variants.epsilonciv.DieRoll;

public class FixedDie implements DieRoll{

    private int rollOne;
    private int rollTwo;

    private boolean shouldBeRollOne = true;

    public FixedDie(int fixedRollOne, int fixedRollTwo)
    {
        this.rollOne = fixedRollOne;
        this.rollTwo = fixedRollTwo;
    }

    @Override
    public int rollDie()
    {
        int result = 0;
        if(shouldBeRollOne)
        {
            result = rollOne;
        }
        else
        {
            result = rollTwo;
        }
        shouldBeRollOne = !shouldBeRollOne;
        return result;
    }

    public void setRollOne(int fixedRoll)
    {
        this.rollOne = fixedRoll;
    }

    public void setRollTwo(int fixedRoll)
    {
        this.rollTwo = fixedRoll;
    }

}
