package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.deltaciv.DeltaWorldLayoutStrategy;
import hotciv.variants.strategies.WorldGeneration;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
public class TestDeltaCivWorldGeneration {
  private WorldGeneration wg;

  /**
   * Fixture for deltaciv testing.
   */
  @Before
  public void setUp() {
    wg = new DeltaWorldLayoutStrategy();
  }

  @Test
  public void shouldBeCorrectTiles() {
    assertThat(wg, is(notNullValue()));

    Tile[][] tiles = wg.Tiles();

    assertThat(GameConstants.OCEANS, is(tiles[0][0].getTypeString()));
    assertThat(GameConstants.OCEANS, is(tiles[6][6].getTypeString()));

    assertThat(GameConstants.PLAINS, is(tiles[5][1].getTypeString()));
    assertThat(GameConstants.PLAINS, is(tiles[15][13].getTypeString()));

    assertThat(GameConstants.MOUNTAINS, is(tiles[7][13].getTypeString()));
    assertThat(GameConstants.MOUNTAINS, is(tiles[11][3].getTypeString()));

    assertThat(GameConstants.FOREST, is(tiles[1][10].getTypeString()));
    assertThat(GameConstants.FOREST, is(tiles[12][9].getTypeString()));

    assertThat(GameConstants.HILLS, is(tiles[14][5].getTypeString()));
    assertThat(GameConstants.HILLS, is(tiles[1][3].getTypeString()));
  }

  @Test
  public void shouldBeCorrectCities() {
    assertThat(wg, is(notNullValue()));

    City[][] cities = wg.Cities();

    assertThat(Player.RED, is(cities[8][12].getOwner()));
    assertThat(Player.BLUE, is(cities[4][5].getOwner()));

  }

  @Test
  public void shouldBeCorrectUnits() {
    assertThat(wg, is(notNullValue()));

    Unit[][] units = wg.Units();

    assertThat(GameConstants.ARCHER, is(units[2][0].getTypeString()));
    assertThat(Player.RED, is(units[2][0].getOwner()));

    assertThat(GameConstants.SETTLER, is(units[4][3].getTypeString()));
    assertThat(Player.RED, is(units[4][3].getOwner()));

    assertThat(GameConstants.LEGION, is(units[3][2].getTypeString()));
    assertThat(Player.BLUE, is(units[3][2].getOwner()));
  }




}
