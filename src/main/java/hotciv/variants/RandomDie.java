package hotciv.variants;

import java.util.Random;

public class RandomDie implements DieRoll{

    private Random randomGenerator = new Random();

    public int rollDie()
    {
        return randomGenerator.nextInt(5) + 1;
    }

}
