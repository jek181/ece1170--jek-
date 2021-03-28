package hotciv.standard;


import hotciv.factories.EtaFactory;
import hotciv.framework.*;
import hotciv.framework.GameConstants;

import hotciv.framework.Position;
import hotciv.variants.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class TestEtaCivStrategy {
    private Game game;


    /**
     * Fixture for etaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new EtaFactory(), new EtaWorldLayoutStrategy());

    }

    @Test
    public void shouldAddPopulationCorrectly()
    {
        City city = game.getCityAt(new Position(0,1));
        CityImpl c = (CityImpl) city;
        c.addPopulation(4);

        assertThat(city.getSize(), is(5));

        c.addFood(5 + city.getSize() * 3);
        assertThat(c.getFood(), is(20));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(city.getSize(), is(6));
        assertThat(c.getFood(), is(0));
    }

    @Test
    public void shouldNotGoOverPopulationLimit()
    {
        City city = game.getCityAt(new Position(0,4));
        CityImpl c = (CityImpl) city;
        c.addPopulation(8);

        assertThat(city.getSize(), is(9));
        c.addFood(5+ city.getSize()*3);
        assertThat(c.getFood(), is(32));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(city.getSize(), is(9));

    }

    @Test
    public void ShouldGiveCorrectCityProduction() {
        CityImpl city = (CityImpl) game.getCityAt(new Position(0,1));
        assertThat(0, is(city.getFood()));
        assertThat(0, is(city.getTreasury()));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(1, is(city.getFood()));
        assertThat(1, is(city.getTreasury()));
    }

    @Test
    public void shouldHaveCorrectFoodFocus()
    {
        Position p = new Position(1,2);
        game.changeWorkForceFocusInCityAt(p, GameConstants.foodFocus);

        CityImpl city = (CityImpl) game.getCityAt(p);
        city.addPopulation(2);
        game.endOfTurn();
        game.endOfTurn();
        assertThat(city.getFood(), is(7));
        assertThat(city.getTreasury(), is(1));
    }

    @Test
    public void shouldHaveCorrectProductionFocus()
    {
        Position p = new Position(1,2);
        game.changeWorkForceFocusInCityAt(p, GameConstants.productionFocus);

        CityImpl city = (CityImpl) game.getCityAt(p);
        city.addPopulation(2);
        assertThat(city.getFood(), is(0));
        assertThat(city.getTreasury(), is(0));

        game.endOfTurn();
        game.endOfTurn();
        assertThat(city.getFood(), is(1));
        assertThat(city.getTreasury(), is(6));
    }
}