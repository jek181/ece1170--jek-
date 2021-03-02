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

        if(currentGame.getUnitAt(p) == null)
        {
            return false;
        }

        if(unit.getTypeString() == GameConstants.SETTLER)
        {
            if(currentGame.getCityAt(p) != null)
            {
                return false;
            }

            game.eraseUnit(p);
            game.addCity(p,new CityImpl(unit.getOwner()));

            return true;
        }

        if(unit.getTypeString() == GameConstants.ARCHER)
        {
            UnitImpl u = (UnitImpl) unit;
            u.setFortify();
            return true;
        }
        return false;

    }
}
