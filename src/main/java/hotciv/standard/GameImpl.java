package hotciv.standard;

import hotciv.framework.*;

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

  int endOfTurnCount = 0;

  public Tile getTileAt( Position p )
  {

    return null;
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
