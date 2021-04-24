package hotciv.view;

import hotciv.framework.Game;
import hotciv.framework.Position;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class SetFocusTool extends NullTool{
    Game game;

    public SetFocusTool(Game game)
    {
        this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y)
    {
        Position p =GfxConstants.getPositionFromXY(x,y);
        game.setTileFocus(p);
    }
}
