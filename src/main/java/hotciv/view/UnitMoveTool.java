package hotciv.view;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class UnitMoveTool extends NullTool {
    Game game;
    DrawingEditor drawingEditor;
    Figure figure;
    int lastX;
    int lastY;
    boolean unit;
    Position from;

    public UnitMoveTool(DrawingEditor drawingEditor, Game game)
    {
        this.game = game;
        this.drawingEditor = drawingEditor;
        lastX = 0;
        lastY = 0;
        unit = false;
    }

    public boolean world(Position p)
    {
        boolean rowWorld = (0 <= p.getRow() && GameConstants.WORLDSIZE > p.getRow());
        boolean colWorld = (0 <= p.getColumn() && GameConstants.WORLDSIZE > p.getColumn());

        return (rowWorld && colWorld);
    }

    public void mouseDrag(MouseEvent e, int x, int y)
    {
        if(figure != null)
        {
            figure.moveBy(x-lastX, y-lastY);
            lastX = x;
            lastY = y;
        }
    }

    public void mouseDown(MouseEvent e, int x, int y)
    {
        Position p = GfxConstants.getPositionFromXY(x,y);
        if(world(p))
        {
            from = p;
            Unit u = game.getUnitAt(p);
            if(u != null)
            {
                figure = drawingEditor.drawing().findFigure(x,y);
                lastX = x;
                lastY = y;
            }
        }
    }

    public void mouseUp(MouseEvent e, int x, int y)
    {
        if(figure != null)
        {
            Position to = GfxConstants.getPositionFromXY(x,y);
            int finalX = GfxConstants.getXFromColumn(to.getColumn());
            int finalY = GfxConstants.getYFromRow(to.getRow());

            figure.moveBy(finalX-lastX, finalY-lastY);

            if(!world(to))
            {
                int startX = GfxConstants.getXFromColumn(from.getColumn());
                int startY = GfxConstants.getYFromRow(from.getRow());
                figure.moveBy(startX-finalX, startY-finalY);

            }
            else if(!game.moveUnit(from, to))
            {
                int startX = GfxConstants.getXFromColumn(from.getColumn());
                int startY = GfxConstants.getYFromRow(from.getRow());
                figure.moveBy(startX-finalX, startY-finalY);
            }
        }
        figure = null;
    }


}
