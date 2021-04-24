package hotciv.view;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EndOfTurnTool extends NullTool {

    Game game;
    DrawingEditor drawingEditor;
    Figure figure;

    public EndOfTurnTool(DrawingEditor drawingEditor, Game game)
    {
        this.game = game;
        this.drawingEditor = drawingEditor;
    }

    public void mouseDown(MouseEvent e, int x, int y)
    {
        figure = drawingEditor.drawing().findFigure(x,y);
    }

    public void mouseUp(MouseEvent e, int x, int y)
    {
        if(figure != null)
        {
            Rectangle display = figure.displayBox();

            boolean X = GfxConstants.TURN_SHIELD_X >= display.x && GfxConstants.TURN_SHIELD_X <= display.x + display.width;
            boolean Y = GfxConstants.TURN_SHIELD_Y >= display.y && GfxConstants.TURN_SHIELD_Y <= display.y + display.height;
            boolean turnShieldCovered = (X && Y);

            if(turnShieldCovered)
            {
                System.out.println("Turn ended");
                game.endOfTurn();
            }
        }
    }
}
