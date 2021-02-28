package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;

import javax.lang.model.type.NullType;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class TestAlphaCiv {
  private Game game;
  private WorldAgingStrategy betawas;
  private WinnerStrategy betaws;
  private UnitActionStrategy uas;

  /**
   * Fixture for alphaciv testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl(new Strategy());
    betawas = new BetaWorldAgingStrategy();
    betaws = new BetaWinnerStrategy();
    uas = new GammaUnitActionStrategy();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    // TODO: reenable the assert below to get started...
     assertThat(game.getPlayerInTurn(), is(Player.RED));
     game.endOfTurn();
     assertThat(game.getPlayerInTurn(), is(Player.BLUE));
     game.endOfTurn();
     assertThat(game.getPlayerInTurn(), is(Player.RED));
  }


  /**
   * This test shows the game starting at 4000 BC
   */
  @Test
  public void shouldBe4000BCStartingGame() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getAge(), is(-4000));
  }


  /**
   * This test shows that the next player is in turn after the other
   * player ends their turn
   */
  @Test
  public void shouldBeBlueAsNextPlayer() {
    assertThat(game, is(notNullValue()));

    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  /**
   * This test shows that there is a mountain at(2,2)
   */
  @Test
  public void shouldBeAMountain2_2() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(2,2);


    assertThat(GameConstants.MOUNTAINS, is(game.getTileAt(p).getTypeString()));
    assertThat(new Position(2,2), is(game.getTileAt(p).getPosition()));
  }

  /**
   * This test shows that there is a hill at(0,1)
   */
  @Test
  public void shouldBeAHill0_1() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(0,1);

    assertThat(GameConstants.HILLS, is(game.getTileAt(p).getTypeString()));
    assertThat(new Position(0,1), is(game.getTileAt(p).getPosition()));
  }

  /**
   * This test shows that there is an ocean at(1,0)
   */
  @Test
  public void shouldBeAnOcean1_0() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(1,0);

    assertThat(GameConstants.OCEANS, is(game.getTileAt(p).getTypeString()));
    assertThat(new Position(1,0), is(game.getTileAt(p).getPosition()));
  }

  /**
   * This test shows that there is a plain at(4,5)
   */
  @Test
  public void shouldBeAPlain4_5() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(4,5);

    assertThat(GameConstants.PLAINS, is(game.getTileAt(p).getTypeString()));
    //assertEquals(new Position(4,5), game.getTileAt(p).getPosition());
  }

  /**
   * This test shows that there is a plain at(10,14)
   */
  @Test
  public void shouldBeAPlain10_14() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(10,14);
    Tile t = game.getTileAt(p);

    assertEquals(GameConstants.PLAINS, game.getTileAt(p).getTypeString());
    assertThat(t, is(notNullValue()));
  }

  /**
   * This test shows that after each round the years chang
   * by an increment of 100
   */
  @Test
  public void roundShouldChangeBy100Years() {
    assertThat(game, is(notNullValue()));

    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    game.endOfTurn();
    assertThat(game.getAge(), is(-3900));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    game.endOfTurn();
    assertThat(game.getAge(), is(-3800));

  }

  /**
   * This test is to show that player red wins at 3000 BC
   */
  @Test
  public void shouldBeRedAsWinner() {
    assertThat(game, is(notNullValue()));

    for(int i=0; i<10; i++)
    {
      assertThat(game.getPlayerInTurn(), is(Player.RED));
      game.endOfTurn();
      assertThat(game.getPlayerInTurn(), is(Player.BLUE));
      game.endOfTurn();
    }

    assertThat(game.getWinner(), is(Player.RED));

  }

  /**
   * This test shows there should be a red archer at (2,0)
   */
  @Test
  public void shouldBeRedArcher2_0() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(2,0);

    assertThat(GameConstants.ARCHER, is(game.getUnitAt(p).getTypeString()));
    assertThat(Player.RED, is(game.getUnitAt(p).getOwner()));

  }

  /**
   * This test shows there should be a red settler at (4,3)
   */
  @Test
  public void shouldBeRedSettler4_3() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(4,3);

    assertThat(GameConstants.SETTLER, is(game.getUnitAt(p).getTypeString()));
    assertThat(Player.RED, is(game.getUnitAt(p).getOwner()));

  }

  /**
   * This test shows there should be a blue legion at (3,2)
   */
  @Test
  public void shouldBeBlueLegion3_2() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(3,2);

    assertThat(GameConstants.LEGION, is(game.getUnitAt(p).getTypeString()));
    assertThat(Player.BLUE, is(game.getUnitAt(p).getOwner()));

  }


  /**
   * This test shows that there is a blue city at (4,1)
   */
  @Test
  public void shouldBeBlueCity4_1() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(4, 1);
    City c = game.getCityAt(p);

    assertThat(c, is(game.getCityAt(p)));
    assertThat(Player.BLUE, is(game.getCityAt(p).getOwner()));
  }


  /**
   * This test shows there is a red city at (1,1)
   */
  @Test
  public void shouldBeRedCity1_1() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(1, 1);
    City c = game.getCityAt(p);

    assertThat(c, is(game.getCityAt(p)));
    assertThat(Player.RED, is(game.getCityAt(p).getOwner()));
  }


  /**
   * This test shows there is only a population of one
   * in the blue city
   */
  @Test
  public void shouldBePopulation1BlueCity() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(4, 1);

    assertThat(1, is(game.getCityAt(p).getSize()));
  }


  /**
   * This test shows there is only a population of one
   * in the red city
   */
  @Test
  public void shouldBePopulation1RedCity() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(1, 1);

    assertThat(1, is(game.getCityAt(p).getSize()));
  }


  /**
   * This test shows that you are able to change the production of the
   * city to a SETTLE, LEGION, and ARCHER
   */
  @Test
  public void changeProductionRedCitySettlerToLegion() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(1, 1);
    game.changeProductionInCityAt(p,GameConstants.SETTLER);

    assertThat(GameConstants.SETTLER, is(game.getCityAt(p).getProduction()));
    game.changeProductionInCityAt(p,GameConstants.LEGION);
    assertThat(Player.RED, is(game.getCityAt(p).getOwner()));
    assertThat(GameConstants.LEGION, is(game.getCityAt(p).getProduction()));

  }


  /**
   * This test shows that after each round each city will
   * gain a 6 production
   */
  @Test
  public void shouldGain6ProductionEachRound() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(1, 1);

    assertThat(0, is(game.getCityAt(p).getTreasury()));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(-3900, is(game.getAge()));
    assertThat(6, is(game.getCityAt(p).getTreasury()));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(-3800, is(game.getAge()));
    assertThat(12, is(game.getCityAt(p).getTreasury()));
  }


  /**
   * This test you should be able to change workforce focus
   * within the plaer city
   */
  @Test
  public void changeWorkforceFocusRedCity() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(1, 1);
    game.getCityAt(p).setWorkforceFocus(GameConstants.productionFocus);

    assertThat(GameConstants.productionFocus, is(game.getCityAt(p).getWorkforceFocus()));

  }

  /**
   * This test you should be able to move a unit
   */
  @Test
  public void shouldBeAbleToMoveAUnit() {
    assertThat(game, is(notNullValue()));
    //Blue Legion
    Position from = new Position(3,2);
    Position to = new Position(3,3);

    assertThat(GameConstants.LEGION, is(game.getUnitAt(from).getTypeString()));
    assertThat(Player.BLUE, is(game.getUnitAt(from).getOwner()));
    game.moveUnit(from, to);
    assertThat(GameConstants.LEGION, is(game.getUnitAt(from).getTypeString()));

  }






  /**
   * This test is to show that when moving to a mountain you won't be able
   * to get placed there, it will keep you in your original tile and the
   * function will return false
   */
  @Test
  public void shouldNotBeAbleToMoveToMountain() {
    assertThat(game, is(notNullValue()));
    Position from = new Position(2,0);
    Position to = new Position(2, 1);
    //Location of mountain
    Position moun = new Position(2,2);


    assertThat(GameConstants.ARCHER, is(game.getUnitAt(from).getTypeString()));
    assertThat(Player.RED, is(game.getUnitAt(from).getOwner()));
    game.moveUnit(from, to);
    assertThat(GameConstants.ARCHER, is(game.getUnitAt(to).getTypeString()));
    game.moveUnit(to, moun);
    assertThat(false, is(game.moveUnit(to, moun)));
    assertThat(GameConstants.ARCHER, is(game.getUnitAt(to).getTypeString()));

  }


  /**
   * This test is to show that when moving to an ocean you won't be able
   * to get placed there, it will keep you in your original tile and the
   * function will return false
   */
  @Test
  public void shouldNotBeAbleToMoveToOcean() {
    assertThat(game, is(notNullValue()));
    Position from = new Position(2,0);
    //Location of Ocean
    Position to = new Position(1, 0);


    assertThat(GameConstants.ARCHER, is(game.getUnitAt(from).getTypeString()));
    assertThat(Player.RED, is(game.getUnitAt(from).getOwner()));
    game.moveUnit(from, to);
    assertThat(GameConstants.ARCHER, is(game.getUnitAt(from).getTypeString()));
    assertThat(false, is(game.moveUnit(from, to)));

  }

  /**
   * This test shows that you can't move more than your move count which
   * is 1, so you can only move to an adjacent block
   */
  @Test
  public void shouldNotBeAbleToMovePlayerUnitsToANonAdjacentTile() {
    assertThat(game, is(notNullValue()));
    Position from = new Position(2,0);
    Position to = new Position(5,5);

    assertThat(GameConstants.ARCHER, is(game.getUnitAt(from).getTypeString()));
    assertThat(Player.RED, is(game.getUnitAt(from).getOwner()));
    game.moveUnit(from, to);
    assertThat(false, is(game.moveUnit(from, to)));

  }

  /**
   * This test should show that when a Red unit encounters
   * a Blue unit the Red unit automatically attacks taking the
   * Blue units tile.
   */
  @Test
  public void shouldBeAbleToAttackBlueUnitWithRedUnit() {
    assertThat(game, is(notNullValue()));
    Position Archer = new Position(2,0);
    Position Legion = new Position(3,2);
    Position Tile2 = new Position(2,1);
    Position Tile3 = new Position(3,1);

    assertThat(GameConstants.LEGION, is(game.getUnitAt(Legion).getTypeString()));
    assertThat(GameConstants.ARCHER, is(game.getUnitAt(Archer).getTypeString()));
    game.moveUnit(Archer, Tile2);
    assertThat(GameConstants.ARCHER, is(game.getUnitAt(Tile2).getTypeString()));
    game.moveUnit(Tile2, Tile3);
    assertThat(GameConstants.ARCHER, is(game.getUnitAt(Tile3).getTypeString()));
    game.moveUnit(Tile3,Legion);

    assertThat(GameConstants.ARCHER, is(game.getUnitAt(Legion).getTypeString()));

  }

  @Test
  public void shouldBe100YearRoundsTillBC() {
    assertThat(game, is(notNullValue()));
    assertThat(betawas, is(notNullValue()));

    assertThat(-3900,is(betawas.calculateAge(-4000)));
    assertThat(-2500, is(betawas.calculateAge(-2600)));
    assertThat(-1100, is(betawas.calculateAge(-1200)));

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

  //@Test
  //public void should







}
