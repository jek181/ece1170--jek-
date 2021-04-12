package hotciv.standard;

import hotciv.factories.ThetaFactory;
import hotciv.framework.*;
import hotciv.framework.Position;
import hotciv.variants.ThetaWorldLayoutStrategy;
import hotciv.variants.ThetaUnitActionStrategy;
import hotciv.variants.UnitActionStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TestThetaCiv {

    private Game game;
    private UnitActionStrategy uas;

    /**
     * Fixture for thetaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new ThetaFactory(), new ThetaWorldLayoutStrategy());
        uas = new ThetaUnitActionStrategy();

    }


    @Test
    public void shouldConquerCity()
    {
        Position p = new Position(4,1);
        City c = game.getCityAt(p);
        Tile[][] tiles = new ThetaWorldLayoutStrategy().Tiles();

        assertThat(c.getSize(), is(1));
        game.moveUnit(new Position(4,3), new Position(4,2));
        game.moveUnit(new Position(4,2), p);

        //This shows that the city was conquered because the UFO "conquered" it by decreasing the population to 0
        assertThat(GameConstants.PLAINS, is(tiles[4][1].getTypeString()));
    }

    /*@Test
    public void shouldDecreaseCityPopulation()
    {
        Position p = new Position(4,1);
        City city = game.getCityAt(p);
        CityImpl c = (CityImpl) city;

        c.addPopulation(4);

        assertThat(c.getSize(), is(5));
        game.moveUnit(new Position(4,3), new Position(4,2));
        game.moveUnit(new Position(4,2), p);
        uas.unitAction(p, game);
        assertThat(c.getSize(), is(4));

    }

    @Test
    public void shouldTurnForestToPlain()
    {

        Tile[][] tiles = new ThetaWorldLayoutStrategy().Tiles();

        assertThat(GameConstants.FOREST, is(tiles[4][2].getTypeString()));

        game.performUnitActionAt(new Position(4,2));


        assertThat(GameConstants.PLAINS, is(tiles[4][2].getTypeString()));

    }*/

    @Test
    public void shouldBeAbleToGoOverAMountainOrOcean()
    {
        Position o = new Position(2,1);
        Position m = new Position(2,2);

        assertThat(GameConstants.OCEANS, is(game.getTileAt(o).getTypeString()));
        assertThat(GameConstants.MOUNTAINS, is(game.getTileAt(m).getTypeString()));

        assertThat(true, is(game.moveUnit(new Position(1,1), o)));
        //game.moveUnit(new Position(1,1), o);


    }

    @Test
    public void shouldBeAbleToChangeProductionToUFO()
    {
        Position p = new Position(1, 1);
        game.getCityAt(p).setWorkforceFocus(GameConstants.productionFocus);
        game.changeProductionInCityAt(p, GameConstants.UFO);

        assertThat(GameConstants.productionFocus, is(game.getCityAt(p).getWorkforceFocus()));
        assertThat(GameConstants.UFO, is(game.getCityAt(p).getProduction()));

    }
}
