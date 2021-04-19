package hotciv.variants.epsilonciv;

import hotciv.variants.epsilonciv.DieRoll;

import java.util.Random;

public class RandomDie implements DieRoll {

    private Random randomGenerator = new Random();

    @Override
    public int rollDie()
    {
        return randomGenerator.nextInt(5) + 1;
    }

}
