package hotciv.variants.strategies;

import hotciv.framework.Player;
import hotciv.framework.Game;


public interface WinnerStrategy {
    public Player getWinner(Game currentGame);
}
