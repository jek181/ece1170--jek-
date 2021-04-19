package hotciv.standard;

import hotciv.factories.AlphaFactory;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.epsilonciv.EpsilonAttackStrategy;
import hotciv.variants.epsilonciv.EpsilonWinnerStrategy;
import hotciv.variants.epsilonciv.EpsilonWorldLayoutStrategy;
import hotciv.variants.epsilonciv.FixedDie;
import hotciv.variants.strategies.AttackStrategy;
import hotciv.variants.strategies.WinnerStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class TestEpsilonCivStrategy {
  private Game game;
  private WinnerStrategy ews;
  private AttackStrategy aas;
  FixedDie fixedDie;

  /**
   * Fixture for epsilonciv testing.
   * Reference Epsilon World Layout
   */
  @Before
  public void setUp() {
    game = new GameImpl(new AlphaFactory(), new EpsilonWorldLayoutStrategy());
    fixedDie = new FixedDie(1, 1);
    ews = new EpsilonWinnerStrategy();
    aas = new EpsilonAttackStrategy(fixedDie);

  }

  /**
   * Tests that after 3 successful attacks on opposing team
   * there should be a winner
   */
  @Test
  public void shouldBeAWinnerAfter3SuccessfulAttacks(){
    assertThat(game, is(notNullValue()));

    game.moveUnit(new Position(2,0), new Position(2,1));
    game.moveUnit(new Position(4,2), new Position(3,2));

    assertThat(null, is(ews.getWinner(game)));

    game.moveUnit(new Position(6,6), new Position(7, 6));

    assertThat(Player.RED, is(ews.getWinner(game)));

  }

}
