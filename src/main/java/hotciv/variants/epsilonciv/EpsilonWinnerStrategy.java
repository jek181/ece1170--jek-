package hotciv.variants.epsilonciv;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.variants.strategies.WinnerStrategy;

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

    @Override
    public Player getWinner(Game currentGame)
    {
        GameImpl game = (GameImpl) currentGame;
        List<Battle> battles = game.getBattles();
        int redWins = 0;
        int blueWins = 0;
        int yellowWins = 0;
        int greenWins = 0;

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
                    else if(battle.getAttacker().equals(Player.YELLOW))
                    {
                        yellowWins++;
                    }
                    else if(battle.getAttacker().equals(Player.GREEN))
                    {
                        greenWins++;
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
                else if(yellowWins >= 3)
                {
                    return Player.YELLOW;
                }
                else if(greenWins >= 3)
                {
                    return Player.GREEN;
                }
            }
        }
        return null;
    }
}
