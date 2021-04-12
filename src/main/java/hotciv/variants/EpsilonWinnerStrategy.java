package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.*;
import java.util.*;

public class EpsilonWinnerStrategy implements WinnerStrategy {

    int round;

    public EpsilonWinnerStrategy(int round)
    {
        this.round = round;
    }

    public EpsilonWinnerStrategy()
    {
        round = 0;
    }

    public Player getWinner(Game currentGame)
    {
        GameImpl game = (GameImpl) currentGame;
        List<Battle> battles = game.getBattles();
        int redWins = 0;
        int blueWins = 0;

        for(Battle battle: battles)
        {
            if(battle.getRound() > round)
            {
                if(battle.isSuccessful())
                {
                    if(battle.getAttacker().equals(Player.RED))
                    {
                        redWins++;
                    }
                    else if(battle.getAttacker().equals(Player.BLUE))
                    {
                        blueWins++;
                    }
                }
                if(redWins >= 3)
                {
                    return Player.RED;
                }
                else if(blueWins >= 3)
                {
                    return Player.BLUE;
                }
            }
        }
        return null;
    }
}
