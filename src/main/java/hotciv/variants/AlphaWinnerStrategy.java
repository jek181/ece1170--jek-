package hotciv.variants;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.framework.*;

public class AlphaWinnerStrategy implements WinnerStrategy{

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
