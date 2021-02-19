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

  private Player playerInTurn;
  private int round;
  private int age;
  private Game game;
  private City c;
  private Tile[][] tiles;
  private Unit[][] units;
  private City[][] cities;
  private boolean firstRound;

  private HashMap<Position, Tile> Map;
  Player RED = Player.RED;
  Player BLUE = Player.BLUE;

  public GameImpl()
  {

    playerInTurn = RED;
    round = 1;
    age = -4000;
    firstRound = true;


    this.tiles = new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
    this.units = new UnitImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
    this.cities = new CityImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];


    //This Places The Tiles
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

    //This Places The Units
    units[2][0] = new UnitImpl(GameConstants.ARCHER, RED);
    units[4][3] = new UnitImpl(GameConstants.SETTLER, RED);
    units[3][2] = new UnitImpl(GameConstants.LEGION, BLUE);

    //This Places The Cities
    cities[4][1] = new CityImpl(BLUE);
    cities[1][1] = new CityImpl(RED);




  }

  public Tile getTileAt( Position p )
  {
    return tiles[p.getRow()][p.getColumn()];
  }

  public Unit getUnitAt( Position p )
  {
    return units[p.getRow()][p.getColumn()];
  }

  public City getCityAt( Position p )
  {
    return cities[p.getRow()][p.getColumn()];
  }

  public Player getPlayerInTurn()
  {
      return playerInTurn;
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
    Unit u = getUnitAt(from);

    //To make sure there is an actual unit
    if(u != null)
    {
      Tile t = getTileAt(to);
      String type = t.getTypeString();
      boolean Mountain = type.equals(GameConstants.MOUNTAINS);
      boolean Ocean = type.equals(GameConstants.OCEANS);

      //Making sure the unit can't be placed on a mountain or in an ocean
      if(Mountain == true || Ocean == true)
      {
        return false;
      }

      //Making sure the player is moving their own unit
      if(u.getOwner() != playerInTurn)
      {
        return false;
      }

      //Checking to make sure the unit doesn't move more than their move count
      //Gets the distance between the rows and columns 'from' to 'to'
      //Only can move to an adjacent block
      int row = from.getRow() - to.getRow();
      int col = from.getColumn() - to.getColumn();
      int dist;
      if(row>col || row == col)
      {
        dist = row;
      }
      else
      {
        dist = col;
      }
      if(getUnitAt(to) != null)
      {
        if(getUnitAt(to).getOwner() == u.getOwner())
        {
          return false;
        }
        else
        {
          if(u.getTypeString() == GameConstants.ARCHER && u.getOwner() == RED)
          {
            performUnitActionAt(to);
            units[to.getRow()][to.getColumn()] = u;
          }
        }

      }
      else
      {
        u.setMoveCount(0);
        //Set the unit at its new position
        units[to.getRow()][to.getColumn()] = u;
        //Remove the unit from the old position
        units[from.getRow()][from.getColumn()] = null;
      }

      return true;

    }
    return false;
  }

  public void endOfTurn()
  {
     if(playerInTurn == RED)
     {
       playerInTurn = BLUE;
     }
     else
     {
       playerInTurn = RED;
       age += 100;
       round += 1;
       Position p1 = new Position(4, 1);
       Position p2 = new Position(1, 1);
       City c1 = getCityAt(p1);
       City c2 = getCityAt(p2);

       c1.addTreasury(6);
       c2.addTreasury(6);
     }

  }

  public void changeWorkForceFocusInCityAt( Position p, String balance )
  {
      City c = getCityAt(p);
      c.setWorkforceFocus(balance);
  }

  public void changeProductionInCityAt( Position p, String unitType )
  {
        City c = getCityAt(p);
        c.setProduction(unitType);
  }
  public void performUnitActionAt( Position p )
  {
      if(getUnitAt(p).getOwner() == BLUE)
      {
        //Remove Blue Unit as Attack By Red
        units[p.getRow()][p.getColumn()] = null;

      }
  }

}
