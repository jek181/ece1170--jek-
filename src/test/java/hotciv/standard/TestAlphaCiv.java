package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
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

  /**
   * Fixture for alphaciv testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    // TODO: reenable the assert below to get started...
     assertThat(game.getPlayerInTurn(), is(Player.RED));
     assertThat(game.getPlayerInTurn(), is(Player.BLUE));
     assertThat(game.getPlayerInTurn(), is(Player.RED));
  }


  @Test
  public void shouldBe4000BCStartingGame() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getAge(), is(-4000));
  }


  @Test
  public void shouldBeBlueAsNextPlayer() {
    assertThat(game, is(notNullValue()));

    assertThat(game.getPlayerInTurn(), is(Player.RED));
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  @Test
  public void shouldBeAMountain2_2() {
    Position p = new Position(2,2);

    assertThat(GameConstants.MOUNTAINS, is(game.getTileAt(p).getTypeString()));
    assertThat(new Position(2,2), is(game.getTileAt(p).getPosition()));
  }

  @Test
  public void shouldBeAHill0_1() {
    Position p = new Position(0,1);

    assertThat(GameConstants.HILLS, is(game.getTileAt(p).getTypeString()));
    assertThat(new Position(0,1), is(game.getTileAt(p).getPosition()));
  }

  @Test
  public void shouldBeAnOcean1_0() {
    Position p = new Position(1,0);

    assertThat(GameConstants.OCEANS, is(game.getTileAt(p).getTypeString()));
    assertThat(new Position(1,0), is(game.getTileAt(p).getPosition()));
  }

  @Test
  public void shouldBeAPlain4_5() {
    Position p = new Position(4,5);

    assertThat(GameConstants.PLAINS, is(game.getTileAt(p).getTypeString()));
    //assertEquals(new Position(4,5), game.getTileAt(p).getPosition());
  }

  @Test
  public void shouldBeAPlain10_14() {
    Position p = new Position(10,14);
    Tile t = game.getTileAt(p);

    assertEquals(GameConstants.PLAINS, game.getTileAt(p).getTypeString());
    assertThat(t, is(notNullValue()));
  }

  @Test
  public void roundShouldChangeBy100Years() {
    assertThat(game, is(notNullValue()));

    assertThat(game.getPlayerInTurn(), is(Player.RED));
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    assertThat(game.getAge(), is(-3900));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    assertThat(game.getAge(), is(-3800));

  }

  @Test
  public void shouldBeRedAsWinner() {
    assertThat(game, is(notNullValue()));

    for(int i=0; i<10; i++)
    {
      assertThat(game.getPlayerInTurn(), is(Player.RED));
      assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }

    assertThat(game.getWinner(), is(Player.RED));

  }











  /** REMOVE ME. Not a test of HotCiv, just an example of what
   matchers the hamcrest library has... */
  @Test
  public void shouldDefinetelyBeRemoved() {
    // Matching null and not null values
    // 'is' require an exact match
    String s = null;
    assertThat(s, is(nullValue()));
    s = "Ok";
    assertThat(s, is(notNullValue()));
    assertThat(s, is("Ok"));

    // If you only validate substrings, use containsString
    assertThat("This is a dummy test", containsString("dummy"));

    // Match contents of Lists
    List<String> l = new ArrayList<String>();
    l.add("Bimse");
    l.add("Bumse");
    // Note - ordering is ignored when matching using hasItems
    assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));

    // Matchers may be combined, like is-not
    assertThat(l.get(0), is(not("Bumse")));
  }
}
