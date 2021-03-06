package hotciv.standard;

import hotciv.factories.BetaFactory;
import hotciv.framework.*;
import hotciv.variants.alphaciv.AlphaWorldLayoutStrategy;
import hotciv.variants.betaciv.BetaWinnerStrategy;
import hotciv.variants.betaciv.BetaWorldAgingStrategy;
import hotciv.variants.strategies.WinnerStrategy;
import hotciv.variants.strategies.WorldAgingStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;




public class TestBetaCivStrategy {
  private Game game;
  private WorldAgingStrategy was;
  private WinnerStrategy betaws;

  /**
   * Fixture for betaciv testing.
   * Reference Alpha World layout
   */
  @Before
  public void setUp() {
    game = new GameImpl(new BetaFactory(), new AlphaWorldLayoutStrategy());
    was = new BetaWorldAgingStrategy();
    betaws = new BetaWinnerStrategy();
  }


  /**
   * Age should add 100 each round till BC era
   */
  @Test
  public void shouldBe100YearRoundsTillBC() {
    assertThat(was, is(notNullValue()));

    assertThat(-3900,is(was.calculateAge(-4000)));
    assertThat(-2500, is(was.calculateAge(-2600)));
    assertThat(-1100, is(was.calculateAge(-1200)));

  }

  /**
   * Age should be sequenced each round during the BC era
   */
  @Test
  public void shouldBeSequenceRoundsAtBC() {
    assertThat(was, is(notNullValue()));

    assertThat(-1, is(was.calculateAge(-100)));
    assertThat(1, is(was.calculateAge(-1)));
    assertThat(50, is(was.calculateAge(1)));
  }

  /**
   * Age should add 50 each round during 50 AD to 1750
   */
  @Test
  public void shouldBe50YearRoundsFrom50ADTo1750() {
    assertThat(was, is(notNullValue()));

    assertThat(100, is(was.calculateAge(50)));
    assertThat(1250, is(was.calculateAge(1200)));
    assertThat(1750, is(was.calculateAge(1700)));
  }

  /**
   * Age should add 25 each round during 1750 to 1900
   */

  @Test
  public void shouldBe25YearRoundsFrom1750To1900() {
    assertThat(was, is(notNullValue()));

    assertThat(1775, is(was.calculateAge(1750)));
    assertThat(1825, is(was.calculateAge(1800)));
    assertThat(1900, is(was.calculateAge(1875)));
  }

  /**
   * Age should add 5 each round during 1900 to 1970
   */
  @Test
  public void shouldBe5YearRoundsFrom1900To1970() {
    assertThat(was, is(notNullValue()));

    assertThat(1905, is(was.calculateAge(1900)));
    assertThat(1945, is(was.calculateAge(1940)));
    assertThat(1970, is(was.calculateAge(1965)));
  }

  /**
   * Age should add 1 each round after 1970
   */
  @Test
  public void shouldBe1YearRoundsAfter1970() {
    assertThat(was, is(notNullValue()));

    assertThat(1971, is(was.calculateAge(1970)));
    assertThat(2021, is(was.calculateAge(2020)));
    assertThat(4501, is(was.calculateAge(4500)));
  }

  /**
   * Should be no winner at the start of the game
   */
  @Test
  public void shouldBeNoWinnerAtStart() {
    assertThat(game, is(notNullValue()));
    assertThat(betaws, is(notNullValue()));

    assertThat(null, is(betaws.getWinner(game)));
  }

  /**
   * Player blue should win after conquering all red cities
   */
  @Test
  public void shouldBePlayerBlueWins() {
    assertThat(game, is(notNullValue()));
    assertThat(betaws, is(notNullValue()));

    Position blueCity = new Position(4,1);
    Position redCity = new Position(1,1);


    assertThat(Player.BLUE, is(game.getCityAt(blueCity).getOwner()));
    assertThat(Player.RED, is(game.getCityAt(redCity).getOwner()));

    ((GameImpl) game).conquerRedCity();

    assertThat(Player.BLUE, is(game.getCityAt(redCity).getOwner()));
    assertThat(Player.BLUE, is(betaws.getWinner(game)));
  }

  /**
   * Player red should win after conquering all blue cities
   */
  @Test
  public void shouldBePlayerRedWins() {
    assertThat(game, is(notNullValue()));
    assertThat(betaws, is(notNullValue()));

    Position blueCity = new Position(4,1);
    Position redCity = new Position(1,1);


    assertThat(Player.BLUE, is(game.getCityAt(blueCity).getOwner()));
    assertThat(Player.RED, is(game.getCityAt(redCity).getOwner()));

    ((GameImpl) game).conquerBlueCity();

    assertThat(Player.RED, is(game.getCityAt(blueCity).getOwner()));
    assertThat(Player.RED, is(betaws.getWinner(game)));
  }


}
