package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class ZetaWinnerStrategy implements WinnerStrategy {

    public WinnerStrategy before20rounds, after20rounds, currentStrategy;

    public ZetaWinnerStrategy(WinnerStrategy before20rounds, WinnerStrategy after20rounds)
    {
        this.before20rounds = before20rounds;
        this.after20rounds = after20rounds;
        this.currentStrategy = null;
    }

    @Override
    public Player getWinner(Game currentGame) {
        GameImpl game = (GameImpl) currentGame;
        int currentRound = game.getCurrentRound();
        if(currentRound > 20)
        {
            currentStrategy = after20rounds;
        }
        else
        {
            currentStrategy = before20rounds;
        }

        return currentStrategy.getWinner(currentGame);

    }
}
