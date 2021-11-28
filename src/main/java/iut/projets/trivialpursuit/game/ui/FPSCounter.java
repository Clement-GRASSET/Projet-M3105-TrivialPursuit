package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIElement;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.engine.userinterface.UIText;

import java.awt.*;
import java.util.List;
import java.util.Vector;

public class FPSCounter extends UIText {

    String fps;
    List<Long> frameTimes;

    public FPSCounter() {
        setPosition(new Vector2D(2, 2));
        setAnchor(Anchor.TOP_LEFT);
        setTextAlign(Anchor.TOP_LEFT);
        setAlignment(new Vector2D(1,1));
        setSize(new Vector2D(15,3));
        setFont(Resources.getFont("/fonts/Technology.ttf"));
        setFontSize(3);

        fps = "";
        frameTimes = new Vector<>();
    }

    @Override
    public void update(double frameTime) {
        frameTimes.add(System.nanoTime());
        while (frameTimes.get(0)+1000000000 < System.nanoTime() )
            frameTimes.remove(0);

        fps = "FPS: " + frameTimes.size();
        setText(fps);
    }
}
