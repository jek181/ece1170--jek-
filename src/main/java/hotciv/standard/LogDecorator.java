package hotciv.standard;

import hotciv.framework.*;

public class LogDecorator implements Game {

    private Game game;
    private String sentence;

    public LogDecorator(Game game) {
        this.game = game;
        sentence = "The game has begun.\n";
    }

    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    public Player getWinner() {
        if (getWinner() != null) {
            sentence += "" + getWinner() + " has won the game.";
        }
        return game.getWinner();
    }

    public int getAge() {
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        sentence += "" + getPlayerInTurn() + " moves " + getUnitAt(from).getTypeString() + " from " + from + " to " + to + ".\n";
        return game.moveUnit(from, to);
    }

    public void endOfTurn() {
        sentence += "" + getPlayerInTurn() + " ends turn.\n";
        game.endOfTurn();
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        String workforce;
        if (balance == "hammer") {
            workforce = "production";
        } else {
            workforce = "foodFocus";
        }
        sentence += "" + getPlayerInTurn() + " changes work force focus in city at " + p + " to " + workforce + ".\n";
        game.changeWorkForceFocusInCityAt(p, balance);
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        sentence += "" + getPlayerInTurn() + " changes production in city at " + p + " to " + unitType + ".\n";
        game.changeProductionInCityAt(p, unitType);

    }

    public void performUnitActionAt(Position p) {
        sentence += "" + getPlayerInTurn() + " performs unit action on " + getUnitAt(p).getTypeString() + " at " + p + ".\n";
        game.performUnitActionAt(p);

    }


    public String getLog() {
        return sentence;
    }
}
