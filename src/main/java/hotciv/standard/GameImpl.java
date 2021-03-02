package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.*;

import javax.print.attribute.standard.MediaSize;
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

/**
 * This is the hotfix for release 2.1
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
  private UnitActionStrategy unitActionStrategy;
 private WorldAgingStrategy worldAgingStrategy;
 private WinnerStrategy winnerStrategy;




  Player RED = Player.RED;
  Player BLUE = Player.BLUE;

  public GameImpl(Strategy strategy)
  {

    playerInTurn = RED;
    round = 1;
    age = -4000;
    firstRound = true;
    this.unitActionStrategy = strategy.makeGammaActionStrategy();
    this.worldAgingStrategy = strategy.makeBetaAgingStrategy();
    this.winnerStrategy = strategy.makeAlphaWinnerStrategy();

    this.units = strategy.makeAlphaWorldLayoutStrategy().Units();
    this.tiles = strategy.makeAlphaWorldLayoutStrategy().Tiles();
    this.cities = strategy.makeAlphaWorldLayoutStrategy().Cities();

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
    return winnerStrategy.getWinner(this);
  }

  public int getAge()
  {
    return age;
  }

  public boolean moveUnit( Position from, Position to )
  {
    Unit u = getUnitAt(from);
    UnitImpl unit = (UnitImpl) u;

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
      if(dist > u.getMoveCount())
      {
        return false;
      }


      if(getUnitAt(to) != null)
      {
        if(getUnitAt(to).getOwner() == u.getOwner())
        {
          return false;
        }
        else
        {
          if((u.getTypeString() == GameConstants.ARCHER) || (u.getTypeString() == GameConstants.LEGION))
          {
            units[to.getRow()][to.getColumn()] = null;
            units[to.getRow()][to.getColumn()] = u;
          }
        }

      }
      else
      {
        unit.setMoveCount(0);
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
       age = worldAgingStrategy.calculateAge(age);
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
      unitActionStrategy.unitAction(p, this);
  }

  //Get cities using the util.lists
  public List<City> getCities() {
    List<City> cities = new ArrayList<City>();
    for(int row=0; row < GameConstants.WORLDSIZE; row++)
    {
      for(int col=0; col < GameConstants.WORLDSIZE; col++)
      {
        Position p = new Position(row, col);
        City c = getCityAt(p);
        if(c != null)
        {
          cities.add(c);
        }
      }
    }
    return cities;
  }

  public void eraseUnit(Position p)
  {
    units[p.getRow()][p.getColumn()] = null;
  }

  public void addCity(Position p, City c)
  {
    cities[p.getRow()][p.getColumn()] = c;
  }

  //Conquer city functions for Beta Winner Strategy
  public void conquerRedCity()
  {
    Position red = new Position(1,1);
    cities[red.getRow()][red.getColumn()] = null;
    cities[red.getRow()][red.getColumn()] = new CityImpl(BLUE);

  }

  public void conquerBlueCity()
  {
    Position blue = new Position(4,1);
    cities[blue.getRow()][blue.getColumn()] = null;
    cities[blue.getRow()][blue.getColumn()] = new CityImpl(RED);

  }


}
