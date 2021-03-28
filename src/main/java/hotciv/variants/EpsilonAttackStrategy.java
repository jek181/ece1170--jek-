package hotciv.variants;

import java.util.List;
import hotciv.framework.*;
import hotciv.standard.GameImpl;


public class EpsilonAttackStrategy implements AttackStrategy{

   DieRoll die;

    public EpsilonAttackStrategy(DieRoll die)
    {
        this.die = die;
    }

    public boolean attack(Game game, Position attacker, Position defender)
    {
        int die1 = die.rollDie();
        int die2 = die.rollDie();
        int attack = modifiedAttack(game, attacker);
        int defend = modifiedDefend(game, defender);
        return die1 * attack > die2 * defend;
    }

    public int modifiedAttack(Game game, Position attacker)
    {
        Unit unit = game.getUnitAt(attacker);
        int terrainBonus = terrainBonus(game, attacker);
        int adjacentBonus = adjacentBonus(game, attacker, unit);
        return terrainBonus*(unit.getAttackingStrength() + adjacentBonus);
    }

    public int modifiedDefend(Game game, Position defender) {
        Unit unit = game.getUnitAt(defender);
        int terrainBonus = terrainBonus(game, defender);
        int adjacentBonus = adjacentBonus(game, defender, unit);
        return terrainBonus*(unit.getDefensiveStrength() + adjacentBonus);
    }

    private int adjacentBonus(Game game, Position p, Unit u) {
        GameImpl currentGame = (GameImpl) game;
        List<Position> adjPositions = currentGame.getPositions(p, 1);
        int bonus = 0;
        for ( Position pos : adjPositions) {
            Unit adjU = game.getUnitAt( pos );
            if ( adjU != null && u.getOwner() == adjU.getOwner() ) {
                bonus++;
            }
        }
        return bonus;
    }

    private int terrainBonus(Game game, Position p) {
        int bonus = 1;
        Tile t = game.getTileAt(p);
        String terrainType = t.getTypeString();
        if ( game.getCityAt(p) != null ) {
            bonus = 3;
        } else if ( terrainType.equals(GameConstants.FOREST) ||
                terrainType.equals(GameConstants.HILLS)) {
            bonus = 2;
        }
        return bonus;
    }

}
