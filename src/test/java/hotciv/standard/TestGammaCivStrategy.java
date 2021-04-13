package hotciv.standard;

import hotciv.factories.GammaFactory;
import hotciv.framework.*;
import hotciv.variants.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class TestGammaCivStrategy {
  private Game game;
  private UnitActionStrategy uas;

  /**
   * Fixture for gammaciv testing.
   * Uses Alpha World Layout
   */
  @Before
  public void setUp() {
    game = new GameImpl(new GammaFactory(), new AlphaWorldLayoutStrategy());
    uas = new GammaUnitActionStrategy();

  }

  /**
   * Tests that when the settler does its unit action it
   * should build a new city on the tile it performs
   */
  @Test
  public void shouldBuildCityWithSettlerUnitAction() {
    Position p = new Position(4,3);
    Unit u = game.getUnitAt(p);
    Player unitOwner = u.getOwner();

    assertThat(GameConstants.SETTLER, is(u.getTypeString()));
    uas.unitAction(p, game);
    Player cityOwner = game.getCityAt(p).getOwner();

    City c = game.getCityAt(p);

    assertThat(c, is(game.getCityAt(p)));
    assertThat(null, is(game.getUnitAt(p)));
    assertThat(cityOwner, is(unitOwner));

  }

  /**
   * Tests that when the archer performs its unit action it should
   * fortify and its defense strength increases
   */
  @Test
  public void shouldRemoveFortification() {
    Position p = new Position(2,0);
    Unit u = game.getUnitAt(p);

    assertThat(GameConstants.ARCHER, is(u.getTypeString()));
    assertThat(3, is(u.getDefensiveStrength()));
    assertThat(1, is(u.getMoveCount()));

    uas.unitAction(p, game);

    assertThat(6, is(u.getDefensiveStrength()));
    assertThat(0, is(u.getMoveCount()));

  }


}
