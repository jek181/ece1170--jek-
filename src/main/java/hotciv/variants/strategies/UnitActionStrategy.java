package hotciv.variants.strategies;


import hotciv.framework.Game;
import hotciv.framework.Position;

public interface UnitActionStrategy {
    public boolean unitAction(Position p, Game currentGame);


}
