package hotciv.view;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class CompositionTool extends NullTool {

    private UnitMoveTool unitMoveTool;
    private ActionTool actionTool;
    private EndOfTurnTool endOfTurnTool;

    public CompositionTool(DrawingEditor drawingEditor, Game game)
    {
        unitMoveTool = new UnitMoveTool(drawingEditor, game);
        actionTool = new ActionTool(drawingEditor, game);
        endOfTurnTool = new EndOfTurnTool(drawingEditor, game);

    }

    public void mouseDown(MouseEvent e, int x, int y)
    {
        unitMoveTool.mouseDown(e,x,y);
        actionTool.mouseDown(e,x,y);
        endOfTurnTool.mouseDown(e,x,y);
    }

    public void mouseDrag(MouseEvent e, int x, int y)
    {
        unitMoveTool.mouseDrag(e,x,y);
        actionTool.mouseDrag(e,x,y);
        endOfTurnTool.mouseDrag(e,x,y);
    }

    public void mouseUp(MouseEvent e, int x, int y)
    {
        unitMoveTool.mouseUp(e,x,y);
        actionTool.mouseUp(e,x,y);
        endOfTurnTool.mouseUp(e,x,y);
    }

    public void keyDown(KeyEvent e, int k)
    {
        unitMoveTool.keyDown(e, k);
        actionTool.keyDown(e, k);
        endOfTurnTool.keyDown(e, k);
    }

    public void mouseMove(MouseEvent e, int x, int y)
    {
        unitMoveTool.mouseMove(e,x,y);
        actionTool.mouseMove(e,x,y);
        endOfTurnTool.mouseMove(e,x,y);
    }
}
