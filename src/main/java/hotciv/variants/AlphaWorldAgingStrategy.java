package hotciv.variants;

public class AlphaWorldAgingStrategy implements WorldAgingStrategy{

    @Override
    public int calculateAge(int age) {
        return age+100;
    }
}
