package hotciv.standard;

import hotciv.framework.*;

import java.util.*;

/** Skeleton implementation of HotCiv.
 
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

public class GameImpl implements Game {

  int redCount = 0;
  int blueCount = 0;
  int age = -4000;

  //this.Tile StandardTile;

  private Game game;
  private Tile[][] tiles;
  private Unit[][] units;
  private City[][] cities;

  int endOfTurnCount = 0;

  private HashMap<Position, Tile> Map;

  public GameImpl()
  {
    /*Map.put(new Position(0,1), new StandardTile(GameConstants.HILLS));
    CreateTile.put(new Position(1,0), GameConstants.OCEANS);
    CreateTile.put(new Position(0,1), GameConstants.MOUNTAINS);

    for(int i=0; i < GameConstants.WORLDSIZE; i++) {
      for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
        CreateTile.put(new Position(i, j), GameConstants.PLAINS);
      }
    }*/

    this.tiles = new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];

    for(int i=0; i < GameConstants.WORLDSIZE; i++) {
      for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
        if (i == 0 && j == 1) {
          tiles[i][j] = new TileImpl(GameConstants.HILLS);
        } else if (i == 1 && j == 0) {
          tiles[i][j] = new TileImpl(GameConstants.OCEANS);
        } else if (i == 2 && j == 2) {
          tiles[i][j] = new TileImpl(GameConstants.MOUNTAINS);
        } else {
          tiles[i][j] = new TileImpl(GameConstants.PLAINS);
        }
      }
    }




  }

  public Tile getTileAt( Position p )
  {
    return tiles[p.getRow()][p.getColumn()];
  }

  public Unit getUnitAt( Position p )
  {
    return null;
  }

  public City getCityAt( Position p )
  {
    return null;
  }

  public Player getPlayerInTurn()
  {
    if(redCount == blueCount)
    {
      endOfTurn();
      return Player.RED;
    }
    else
    {
      endOfTurn();
      return Player.BLUE;
    }
  }

  public Player getWinner()
  {
    if(getAge() == -3000)
    {
      return Player.RED;
    }

    return null;
  }

  public int getAge()
  {

    return age;
  }

  public boolean moveUnit( Position from, Position to )
  {
    return false;
  }

  public void endOfTurn()
  {
      if(redCount == blueCount)
      {
        redCount++;
      }
      else
      {
        blueCount++;
      }

      endOfTurnCount++;

    if(endOfTurnCount > 0)
    {
      if(endOfTurnCount % 2 == 0)
      {
        age = age + 100;
      }
    }

  }

  public void changeWorkForceFocusInCityAt( Position p, String balance )
  {

  }

  public void changeProductionInCityAt( Position p, String unitType )
  {

  }
  public void performUnitActionAt( Position p )
  {

  }
}
