package hotciv.standard;

import hotciv.framework.*;

public class LogDecorator implements Game {

    private Game game;
    private String sentence;

    public LogDecorator(Game game) {
        this.game = game;
        sentence = "\nGame transcription has started.\n";
    }

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() { return game.getWinner(); }

    @Override
    public int getAge() {
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        sentence += "" + getPlayerInTurn() + " moves " + getUnitAt(from).getTypeString() + " from " + from + " to " + to + ".\n";
        return game.moveUnit(from, to);
    }

    @Override
    public void endOfTurn() {
        sentence += "" + getPlayerInTurn() + " ends turn.\n";
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        String workforce;
        if (balance.equals("hammer")) {
            workforce = "production";
        } else {
            workforce = "foodFocus";
        }
        sentence += "" + getPlayerInTurn() + " changes work force focus in city at " + p + " to " + workforce + ".\n";
        game.changeWorkForceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        sentence += "" + getPlayerInTurn() + " changes production in city at " + p + " to " + unitType + ".\n";
        game.changeProductionInCityAt(p, unitType);

    }

    @Override
    public void performUnitActionAt(Position p) {
        sentence += " " + getPlayerInTurn() + " performs unit action on " + getUnitAt(p).getOwner() + " " + getUnitAt(p).getTypeString() + " at " + p + ".\n";
        game.performUnitActionAt(p);

    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }


    public String getLog() {
        return sentence;
    }
}
