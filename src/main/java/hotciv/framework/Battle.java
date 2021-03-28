package hotciv.framework;

public class Battle {
    private Player attacker;
    private boolean successful;
    private int round;

    public Battle(Player attacker, boolean successful, int round)
    {
        this.attacker = attacker;
        this.successful = successful;
        this.round = round;
    }

    public Player getAttacker()
    {
        return attacker;
    }

    public boolean isSuccessful()
    {
        return successful;
    }

    public int getRound()
    {
        return round;
    }
}
