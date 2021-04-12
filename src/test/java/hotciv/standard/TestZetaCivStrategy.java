package hotciv.standard;


import hotciv.factories.ZetaFactory;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class TestZetaCivStrategy {
    private Game game;


    /**
     * Fixture for zetaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new ZetaFactory(), new EpsilonWorldLayoutStrategy());


    }

    @Test
    public void shouldBeAWinnerAfterConqueringAllCities() {
        assertThat(game, is(notNullValue()));
        assertThat(null, is(game.getWinner()));

        assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));
        game.moveUnit(new Position(4, 2), new Position(4, 1));
        assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.RED));

        assertThat(game.getCityAt(new Position(3, 3)).getOwner(), is(Player.BLUE));

        game.moveUnit(new Position(2, 0), new Position(3, 0));
        game.moveUnit(new Position(3, 0), new Position(3, 1));
        game.moveUnit(new Position(3, 1), new Position(3, 2));
        game.moveUnit(new Position(3, 2), new Position(3, 3));

        assertThat(game.getCityAt(new Position(3, 3)).getOwner(), is(Player.RED));

        assertThat(game.getWinner(), is(Player.RED));

    }

    @Test
    public void shouldNotBeAWinnerAfterRound20AfterConqueringAllCities() {
        assertThat(game, is(notNullValue()));
        assertThat(null, is(game.getWinner()));

        for (int i = 0; i < 20; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }


        assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));
        game.moveUnit(new Position(4, 2), new Position(4, 1));
        assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.RED));

        assertThat(game.getCityAt(new Position(3, 3)).getOwner(), is(Player.BLUE));

        game.moveUnit(new Position(2, 0), new Position(3, 0));
        game.moveUnit(new Position(3, 0), new Position(3, 1));
        game.moveUnit(new Position(3, 1), new Position(3, 2));
        game.moveUnit(new Position(3, 2), new Position(3, 3));

        assertThat(game.getCityAt(new Position(3, 3)).getOwner(), is(Player.RED));

        assertThat(null, is(game.getWinner()));
    }


    @Test
    public void shouldBeAWinnerAfterRound20With2Attacks() {
        assertThat(game, is(notNullValue()));
        assertThat(null, is(game.getWinner()));

        for (int i = 0; i < 20; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(null, is(game.getWinner()));

        game.moveUnit(new Position(2, 0), new Position(2, 1));
        game.moveUnit(new Position(4, 2), new Position(3, 2));
        game.moveUnit(new Position(6, 6), new Position(7, 6));

        assertThat(Player.RED, is(game.getWinner()));
    }
}