package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Position;

public interface AttackStrategy {

    public boolean attack(Game currentGame, Position attacker, Position defender);

}
