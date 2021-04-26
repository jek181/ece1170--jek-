package hotciv.framework;

public class SubjectObserver implements GameObserver{

    private boolean worldChanged;
    private Position position;
    private Player nextPlayer;
    private int age;
    private boolean tileFocusChanged;

    public SubjectObserver(Position position)
    {
        worldChanged = false;
        tileFocusChanged = false;
        this.position = position;
    }

    @Override
    public void worldChangedAt(Position pos) {
        if(pos.equals(position))
        {
            worldChanged = true;
        }
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        this.nextPlayer = nextPlayer;
        this.age = age;
    }

    @Override
    public void tileFocusChangedAt(Position pos) {
        if(pos.equals(position))
        {
            tileFocusChanged = true;
        }
    }

    //public boolean wor
}
