package hotciv.standard;

import hotciv.factories.AlphaFactory;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class TestEpsilonCivAttackStrategy {
    private Game game;
    WorldGeneration wg;
    private EpsilonAttackStrategy eas;
    FixedDie fixedDie;


    /**
     * Fixture for epsiloncivattack testing.
     */

    /**
     * Test Stub for Epsilon Civ With Die Roll ( Fixed )
     */
    @Before
    public void setUp() {
        wg = new EpsilonWorldLayoutStrategy();
        game = new GameImpl(new AlphaFactory(), wg);
        fixedDie = new FixedDie(1, 1);
        eas = new EpsilonAttackStrategy(fixedDie);

    }

    @Test
    public void shouldBeAbleToFixDie()
    {
        fixedDie = new FixedDie(4,6);

        assertThat(4, is(fixedDie.rollDie()));
        assertThat(6, is(fixedDie.rollDie()));

        fixedDie.setRollOne(2);
        fixedDie.setRollTwo(2);

        assertThat(2, is(fixedDie.rollDie()));
        assertThat(2, is(fixedDie.rollDie()));

    }

   @Test
    public void ShouldGetCorrectAtkAdjacencyBonus() {

        Position p1 = new Position( 3, 0 );
        assertThat(3, is(eas.modifiedAttack( game, p1 )));

        Position p2 = new Position( 2, 1 );
        assertThat(3, is(eas.modifiedAttack( game, p2 ) ));

        Position p3 = new Position( 6,6 );
        assertThat(4, is(eas.modifiedAttack( game, p3 )));
    }

    @Test
    public void ShouldGetCorrectDefAdjBonus() {

        Position p1 = new Position( 3, 0 );
        assertThat(4, is(eas.modifiedDefend( game, p1 )));

        Position p2 = new Position( 3, 3 );
        assertThat(9, is(eas.modifiedDefend( game, p2 )));

        Position p3 = new Position( 6,6 );
        assertThat(2, is(eas.modifiedDefend( game, p3 )));
    }

    @Test
    public void ShouldGrantAtkAndDefBonusOf3WhenInCity () {


        Position p0 = new Position( 1, 0 );
        Position p5 = new Position( 2, 0 );
        assertThat(GameConstants.ARCHER, is(game.getUnitAt(p0).getTypeString()));
        assertThat(Player.RED, is(game.getCityAt(p0).getOwner()));
        assertThat(GameConstants.ARCHER, is(game.getUnitAt(p5).getTypeString()));
        assertThat(Player.RED, is(game.getUnitAt(p5).getOwner()));


        assertThat(9, is(eas.modifiedAttack( game, p0 )));
        assertThat(12, is(eas.modifiedDefend( game, p0 )));

        Position p1 = new Position( 3, 3 );
        assertThat(15, is(eas.modifiedAttack( game, p1 )));
        assertThat(9, is(eas.modifiedDefend( game, p1 )));


    }

    @Test
    public void ShouldGrantTerrainDefAndAtkBonusOf2WhenForestOrHills() {

        Position p0 = new Position(4,2);
        assertThat(4, is(eas.modifiedDefend( game, p0 )));

        //Hill
        Position p1 = new Position( 7, 6 );
        assertThat(4, is(eas.modifiedDefend( game, p1 )));

        //Hill
        Position p2 = new Position( 7, 6 );
        assertThat(8, is(eas.modifiedAttack( game, p2 )));

        //Forest
        Position p3 = new Position(4,2);
        assertThat(8, is(eas.modifiedAttack( game, p3 )));


    }

    @Test
    public void ShouldGiveCorrectWinnerWhenRollingDies() {


        Position atk0 = new Position( 2, 1 ); // atk is 3
        Position def0 = new Position( 6, 3 ); // def is 3
        assertThat(3, is(eas.modifiedDefend( game, def0 )));

        assertThat(3, is(eas.modifiedAttack( game, atk0 )));

        assertThat(false, is(eas.attack( game, atk0, def0)));

        Position atk1 = new Position( 6, 6 );; // atk is 4
        Position def1 = new Position( 6, 3 ); // def is 3

        assertThat(3, is(eas.modifiedDefend( game, def1 )));
        assertThat(4, is(eas.modifiedAttack( game, atk1 )));

        assertThat(true, is(eas.attack( game, atk1, def1)));
        fixedDie.setRollOne( 4 );
        fixedDie.setRollTwo( 3 );
        // 4*3 > 3*3
        assertThat(true, is(eas.attack( game, atk0, def0)));

        fixedDie.setRollOne( 3 );
        fixedDie.setRollTwo( 4 );
        // 4*3 > 3*4
        assertThat(false, is(eas.attack( game, atk1, def1)));
    }
}
