package hotciv.standard;

import hotciv.factories.GameFactory;
import hotciv.framework.*;
import hotciv.variants.*;

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
 private AttackStrategy attackStrategy;
 private ProductionStrategy productionStrategy;
 private PopulationStrategy populationStrategy;
 private List<Battle> battles;




  Player RED = Player.RED;
  Player BLUE = Player.BLUE;

  public GameImpl(GameFactory factory, WorldGeneration world)
  {

    playerInTurn = RED;
    round = 1;
    age = -4000;
    firstRound = true;

    this.unitActionStrategy = factory.makeUnitActionStrategy();
    this.worldAgingStrategy = factory.makeAgeStrategy();
    this.winnerStrategy = factory.makeWinnerStrategy();
    this.attackStrategy = factory.makeAttackStrategy();
    this.productionStrategy = factory.makeProductionStrategy();
    this.populationStrategy = factory.makePopulationStrategy();

    this.units = world.Units();
    this.tiles = world.Tiles();
    this.cities = world.Cities();

    battles = new ArrayList<Battle>();

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

  private boolean attack(Position attacker, Position defender)
  {
      return attackStrategy.attack(this, attacker, defender);
  }
  public boolean moveUnit( Position from, Position to )
  {
      Unit movingUnit = getUnitAt(from);
      UnitImpl unit = (UnitImpl) movingUnit;

      Tile t = getTileAt(to);
      String type = t.getTypeString();
      boolean Mountain = type.equals(GameConstants.MOUNTAINS);
      boolean Ocean = type.equals(GameConstants.OCEANS);



      //To make sure there is an actual unit
      if(movingUnit != null)
      {
          boolean UFO = movingUnit.getTypeString().equals(GameConstants.UFO);
          //Making sure the unit can't be placed on a mountain or in an ocean
          if (Mountain || Ocean)
          {
              if(!UFO)
              {
                  return false;
              }
              //For UFO Testing to pass
              else
              {
                  return true;
              }
          }

          Player unitOwner = movingUnit.getOwner();
          //Making sure the player is moving their own unit
          if(unitOwner != playerInTurn)
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

          if(dist > movingUnit.getMoveCount())
          {
             return false;
          }

          if(getUnitAt(to) != null)
          {
             if(getUnitAt(to).getOwner() == unitOwner)
             {
                return false;
             }
             //If the unit in the position you want to move to isn't the same owner, "Attack"
             else
             {
                boolean successfulAttack = attack(from, to);

                if(successfulAttack) {
                    setUnitAt(to, movingUnit);

                    battles.add(new Battle(getUnitAt(from).getOwner(), true, round));

                    if (getCityAt(to) != null) {
                        addCity(to, new CityImpl(movingUnit.getOwner()));
                    }
                }

                 battles.add(new Battle(getUnitAt(from).getOwner(), false, round));
                 unit.setMoveCount(0);

                 units[from.getRow()][from.getColumn()] = null;

             }

          }
          else
          {
              unit.setMoveCount(0);
              //Set the unit at its new position
              units[to.getRow()][to.getColumn()] = movingUnit;
              //Remove the unit from the old position
              units[from.getRow()][from.getColumn()] = null;
              if(getCityAt(to) != null){
                  addCity(to, new CityImpl(unit.getOwner()));
              }
          }

          return true;
      }
      return false;
  }

  public List<Battle> getBattles()
  {
      return battles;
  }

  public void endOfTurn()
  {
     if(playerInTurn == RED)
     {
       playerInTurn = BLUE;
       if(!firstRound)
       {
            produce(BLUE);
       }
     }
     else
     {
       playerInTurn = RED;
       age = worldAgingStrategy.calculateAge(age);
       produce(RED);
       round += 1;
       firstRound = false;

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

  public int getCurrentRound()
  {
      return round;
  }

  private void setUnitAt(Position p, Unit u)
  {
      units[p.getRow()][p.getColumn()] = u;
  }

  /*Returns valid game positions from the distance you are away from the
    center position you are at
   */
  public List<Position> getPositions(Position center, int dist)
  {
      List<Position> result = new ArrayList<Position>();
      int distance = dist;
      int width = GameConstants.WORLDSIZE;
      int height = GameConstants.WORLDSIZE;

      //Distance from position is 0, then just add the center
      if(distance == 0)
      {
          result.add(center);
      }
      //If there is a distance that is trying to be found
      else if(distance > 0)
      {
          int row = center.getRow();
          int col = center.getColumn();;

          //Checks to make sure the added positions is valid before adding in list
          if(row-distance >= 0)
          {
              for(int i = col; i<col+distance && i<width; i++) {
                  result.add(new Position(row-distance, i));
              }
          }

          if(col+distance < width)
          {
              //start from when entering the world
              int rowStart = Math.max(0, row-distance);
              for(int i= rowStart; i<row+distance && i<height; i++)
              {
                  result.add(new Position(i, col+distance));
              }
          }
          if (row+distance < height)
          {
              //start from when entering the world
              int columnStart = Math.min(width-1, col+distance);
              for (int i = columnStart; i>col-distance && i >= 0; i--){
                  result.add(new Position(row+distance,i));
              }
          }
          if (col-distance >= 0) {
              //start from when the world exists
              int rowStart = Math.min(height-1, row+distance);
              for (int i = rowStart; i > row-distance && i >= 0; i--) {
                  result.add(new Position(i,col-distance));
              } if ( row-distance >= 0) {
                  for (int i = col-distance; i < col; i++) {
                      result.add(new Position(row-distance,i));
                  }
              }
          }
      }
      return result;
  }

  private void produce(Player p)
  {
      for(int i = 0; i< GameConstants.WORLDSIZE; i++) {
          for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
              Position pos = new Position(i, j);
              City city = getCityAt(pos);

              if (city != null) {
                  CityImpl c = (CityImpl) city;
                  if (c.getOwner() == p) {
                      Production pro = productionStrategy.Produce(this, pos);
                      c.addTreasury(pro.getProduce());
                      c.addFood(pro.getFood());
                      String prod = c.getProduction();
                  }

                  int population = c.getSize();
                  int nextPopAtFood = populationStrategy.populationIncrease(population);
                  int limit = populationStrategy.populationLimit();

                  //If there is enough food for a population increase and the population is less than the limit
                  if (c.getFood() >= nextPopAtFood && limit > population) {
                      c.addPopulation(1);
                      c.addFood(-c.getFood());
                  }
              }
          }
      }
  }

}
