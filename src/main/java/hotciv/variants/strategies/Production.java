package hotciv.variants.strategies;

public class Production {

    private int food;
    private int produce;

    public Production(int food, int produce)
    {
        this.food = food;
        this.produce = produce;
    }

    public int getFood()
    {
        return food;
    }

    public int getProduce()
    {
        return produce;
    }
}
