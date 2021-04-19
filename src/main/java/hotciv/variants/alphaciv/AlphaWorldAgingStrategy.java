package hotciv.variants.alphaciv;

import hotciv.variants.strategies.WorldAgingStrategy;

public class AlphaWorldAgingStrategy implements WorldAgingStrategy {

    @Override
    public int calculateAge(int age) {
        return age+100;
    }
}
