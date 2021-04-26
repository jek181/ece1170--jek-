package hotciv.view;

import hotciv.framework.Game;
import hotciv.framework.Position;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class ActionTool extends NullTool {

    Game game;
    DrawingEditor drawingEditor;
    Position position;

    public  ActionTool(DrawingEditor drawingEditor, Game game)
    {
        this.game = game;
        this.drawingEditor = drawingEditor;
    }

    public void mouseDown(MouseEvent e, int x, int y)
    {
        if(e.isShiftDown())
        {
            position = GfxConstants.getPositionFromXY(x,y);
        }
    }

    public void mouseUp(MouseEvent e, int x, int y)
    {
        if(e.isShiftDown())
        {
            if(game.getUnitAt(position) != null)
            {
                drawingEditor.showStatus("ActionTool: Unit action performed");
                game.performUnitActionAt(position);
            }
        }
        position = null;
    }
}
