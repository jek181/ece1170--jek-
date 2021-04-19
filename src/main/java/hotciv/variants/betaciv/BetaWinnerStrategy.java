package hotciv.variants.betaciv;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.framework.*;
import hotciv.variants.strategies.WinnerStrategy;

import java.util.List;

public class BetaWinnerStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(Game currentGame) {
        Player win = null;

        GameImpl game = (GameImpl) currentGame;

        List<City> cities = game.getCities();

        for(City c: cities)
        {
            if(c != null)
            {
                if(win == null)
                {
                    win = c.getOwner();
                }
                else if(win != c.getOwner())
                {
                    return null;
                }
            }
        }
        return win;
    }
}
