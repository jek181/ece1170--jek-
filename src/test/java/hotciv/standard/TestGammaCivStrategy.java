package hotciv.standard;

import hotciv.variants.*;
import hotciv.framework.*;
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
   * Fixture for betacivstrat testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl(new Strategy());
    was = new BetaWorldAgingStrategy();
    betaws = new BetaWinnerStrategy();
  }


  @Test
  public void shouldBe100YearRoundsTillBC() {
    assertThat(was, is(notNullValue()));

    assertThat(-3900,is(was.calculateAge(-4000)));
    assertThat(-2500, is(was.calculateAge(-2600)));
    assertThat(-1100, is(was.calculateAge(-1200)));

  }

  @Test
  public void shouldBeSequenceRoundsAtBC() {
    assertThat(was, is(notNullValue()));

    assertThat(-1, is(was.calculateAge(-100)));
    assertThat(1, is(was.calculateAge(-1)));
    assertThat(50, is(was.calculateAge(1)));
  }

  @Test
  public void shouldBe50YearRoundsFrom50ADTo1750() {
    assertThat(was, is(notNullValue()));

    assertThat(100, is(was.calculateAge(50)));
    assertThat(1250, is(was.calculateAge(1200)));
    assertThat(1750, is(was.calculateAge(1700)));
  }


  @Test
  public void shouldBe25YearRoundsFrom1750To1900() {
    assertThat(was, is(notNullValue()));

    assertThat(1775, is(was.calculateAge(1750)));
    assertThat(1825, is(was.calculateAge(1800)));
    assertThat(1900, is(was.calculateAge(1875)));
  }


  @Test
  public void shouldBe5YearRoundsFrom1900To1970() {
    assertThat(was, is(notNullValue()));

    assertThat(1905, is(was.calculateAge(1900)));
    assertThat(1945, is(was.calculateAge(1940)));
    assertThat(1970, is(was.calculateAge(1965)));
  }


  @Test
  public void shouldBe1YearRoundsAfter1970() {
    assertThat(was, is(notNullValue()));

    assertThat(1971, is(was.calculateAge(1970)));
    assertThat(2021, is(was.calculateAge(2020)));
    assertThat(4501, is(was.calculateAge(4500)));
  }

  @Test
  public void shouldBeNoWinnerAtStart() {
    assertThat(game, is(notNullValue()));
    assertThat(betaws, is(notNullValue()));

    assertThat(null, is(betaws.getWinner(game)));
  }

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
