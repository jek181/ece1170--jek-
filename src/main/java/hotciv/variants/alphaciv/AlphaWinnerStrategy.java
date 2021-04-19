package hotciv.variants.alphaciv;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.framework.*;
import hotciv.variants.strategies.WinnerStrategy;

public class AlphaWinnerStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(Game currentGame)
    {
        Player win = null;
        GameImpl game = (GameImpl) currentGame;

        if(game.getAge() == -3000)
        {
            return Player.RED;
        }
        return null;
    }
}
