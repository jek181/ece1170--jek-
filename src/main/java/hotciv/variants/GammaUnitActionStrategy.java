package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

public class GammaUnitActionStrategy implements UnitActionStrategy {


    public boolean unitAction(Position p, Game currentGame) {
        Unit unit = currentGame.getUnitAt(p);
        GameImpl game = (GameImpl) currentGame;

        //If there is no unit
        if(unit == null)
        {
            return false;
        }

        boolean Settler = unit.getTypeString().equals(GameConstants.SETTLER);
        boolean Archer  = unit.getTypeString().equals(GameConstants.ARCHER);

        //If the unit is a settler
        if(Settler)
        {
            //Erase the unit
            game.eraseUnit(p);
            //Add a city belonging to the units owner
            game.addCity(p,new CityImpl(unit.getOwner()));

            return true;
        }

        //If the unit is an archer
        if(Archer)
        {
            //Acquire the unit implementation
            UnitImpl unitArcher = (UnitImpl) unit;
            //Set the fortification of the archer
            unitArcher.setFortify();
            return true;
        }
        return false;

    }
}
