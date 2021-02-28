package hotciv.standard;

import hotciv.variants.WorldAgingStrategy;
import hotciv.variants.BetaWorldAgingStrategy;
import hotciv.framework.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;




public class TestBetaStrategy {
  private WorldAgingStrategy was;

  /**
   * Fixture for betacivstrat testing.
   */
  @Before
  public void setUp() {
    was = new BetaWorldAgingStrategy();
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



}
