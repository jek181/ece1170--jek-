package hotciv.variants.alphaciv;

import hotciv.framework.*;
import hotciv.framework.Game;
import hotciv.variants.strategies.AttackStrategy;

public class AlphaAttackStrategy implements AttackStrategy {

    public boolean attack(Game currentGame, Position attacker, Position defender) {
        return true;
    }
}
