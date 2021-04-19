package hotciv.standard;

import hotciv.factories.EtaFactory;
import hotciv.framework.*;
import hotciv.variants.etaciv.EtaWorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestLogDecorator {

    Game g;

    @Before
    public void setUp() {
        g = new LogDecorator(new GameImpl(new EtaFactory(), new EtaWorldLayoutStrategy()));

    }

    @Test
    public void shouldShowWorkForceChangeMoveUnitAndEndTurns() {

        g = new LogDecorator(new GameImpl(new EtaFactory(), new EtaWorldLayoutStrategy()));
        Position p = new Position(1,2);
        g.changeWorkForceFocusInCityAt(p, GameConstants.productionFocus);
        g.moveUnit(new Position(2,0), new Position(2,1));
        g.endOfTurn();
        g.moveUnit(new Position(4,3), new Position(4,4));
        g.endOfTurn();
        LogDecorator game = (LogDecorator) g;
        System.out.println(game.getLog());

    }

    @Test
    public void shouldBeAbleToShowMultipleWorkForceFocusTypes() {
        g = new LogDecorator(new GameImpl(new EtaFactory(), new EtaWorldLayoutStrategy()));
        Position p = new Position(0,4);
        g.changeWorkForceFocusInCityAt(p, GameConstants.productionFocus);
        g.changeWorkForceFocusInCityAt(p, GameConstants.foodFocus);
        LogDecorator game = (LogDecorator) g;
        System.out.println(game.getLog());

    }

    @Test
    public void shouldBeAbleToShowMultipleProductionFocusTypes() {
        g = new LogDecorator(new GameImpl(new EtaFactory(), new EtaWorldLayoutStrategy()));
        Position p = new Position(0,1);
        g.changeProductionInCityAt(p, GameConstants.ARCHER);
        g.changeProductionInCityAt(p, GameConstants.SETTLER);
        LogDecorator game = (LogDecorator) g;
        System.out.println(game.getLog());


    }

    @Test
    public void shouldBeAbleToShowUnitAction() {
        g = new LogDecorator(new GameImpl(new EtaFactory(), new EtaWorldLayoutStrategy()));
        Position p = new Position(2,0);
        g.moveUnit(p, new Position(2,1));
        g.endOfTurn();
        g.endOfTurn();
        g.moveUnit(new Position(2,1), new Position(2,2));
        g.endOfTurn();
        g.endOfTurn();
        g.performUnitActionAt(new Position(3,2));
        LogDecorator game = (LogDecorator) g;
        System.out.println(game.getLog());


    }

}
