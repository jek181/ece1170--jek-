package hotciv.standard;

import hotciv.factories.EtaFactory;
import hotciv.framework.*;
import hotciv.variants.EtaWorldLayoutStrategy;
import hotciv.variants.WorldGeneration;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestLogDecorator {

    Game g;

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
}
