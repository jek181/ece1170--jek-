package hotciv.variants;

import hotciv.framework.Player;
import hotciv.framework.Game;


public interface WinnerStrategy {
    public Player getWinner(Game currentGame);
}
