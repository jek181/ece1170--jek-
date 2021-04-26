package hotciv.visual;

import hotciv.factories.SemiCiv;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.variants.alphaciv.AlphaWorldLayoutStrategy;
import hotciv.variants.deltaciv.DeltaWorldLayoutStrategy;
import hotciv.view.CompositionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;


public class SemiCivGui {

    public static void main(String[] args) {
        Game game = new GameImpl(new SemiCiv(), new DeltaWorldLayoutStrategy());

        DrawingEditor editor =
                new MiniDrawApplication( "Start Game",
                        new HotCivFactory4(game) );
        editor.open();

        editor.setTool( new CompositionTool(editor, game) );
    }
}
