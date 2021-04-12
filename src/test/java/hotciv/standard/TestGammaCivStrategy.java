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
   */
  @Before
  public void setUp() {
    game = new GameImpl(new GammaFactory(), new AlphaWorldLayoutStrategy());
    uas = new GammaUnitActionStrategy();

  }

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
