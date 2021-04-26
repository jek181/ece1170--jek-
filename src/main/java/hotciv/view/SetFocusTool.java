package hotciv.view;

import hotciv.framework.*;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class SetFocusTool extends NullTool{
    Game game;
    DrawingEditor drawingEditor;

    public SetFocusTool(DrawingEditor drawingEditor, Game game)
    {
        this.game = game;
        this.drawingEditor = drawingEditor;
    }

    public void mouseDown(MouseEvent e, int x, int y)
    {
        Position p =GfxConstants.getPositionFromXY(x,y);
        game.setTileFocus(p);

        Tile t = game.getTileAt(p);
        String type = t.getTypeString();
        Unit u = game.getUnitAt(p);
        City c = game.getCityAt(p);


        if(type.equals(GameConstants.PLAINS))
        {
            drawingEditor.showStatus("This is a plain.");
        }
        if(type.equals(GameConstants.OCEANS))
        {
            drawingEditor.showStatus("This is an ocean.");
        }
        if(type.equals(GameConstants.FOREST))
        {
            drawingEditor.showStatus("This is a forest.");
        }
        if(type.equals(GameConstants.HILLS))
        {
            drawingEditor.showStatus("This is a hill.");
        }

        if(c != null)
        {
            Player owner = c.getOwner();
            int pop = c.getSize();
            int treasure = c.getTreasury();
            String work = c.getWorkforceFocus();

            drawingEditor.showStatus(" There is a " + owner + "city on this tile" + " Population = " + pop + "." +
                    " Treasury = " + treasure + "." +
                    " Work Force Focus is " + work + ".");

        }

        if(u != null)
        {
            String unitType = u.getTypeString();
            Player unitOwner = u.getOwner();
            int strength = u.getAttackingStrength();
            int defense = u.getDefensiveStrength();
            drawingEditor.showStatus(" There is a(n) "+ unitOwner + " " + unitType + " on this tile." +
                    " Attack Strength is " + strength + "." +
                    " Defense Strength is " + defense + ".");
        }
    }
}
