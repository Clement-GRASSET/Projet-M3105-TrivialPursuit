package iut.projets.trivialpursuit.game.assets.ui;

import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIElement;

import java.awt.*;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

public class FPSCounter extends UIElement {

    String fps;
    double textSize;
    List<Long> frameTimes;
    Font font;

    public FPSCounter() {
        setPosition(new Vector2D(2, 2));
        fps = "";
        textSize = 3;
        frameTimes = new Vector<>();
        InputStream is = getClass().getResourceAsStream("/fonts/Technology.ttf");
        font = new Font("Arial", Font.PLAIN, 1);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(double frameTime) {
        frameTimes.add(System.nanoTime());
        while (frameTimes.get(0)+1000000000 < System.nanoTime() )
            frameTimes.remove(0);

        fps = "FPS: " + frameTimes.size();
    }

    @Override
    public void draw(Graphics2D g) {
        int size = (int)(getUnitSizeOnScreen() * textSize);
        int x = getAnchorX() + getX();
        int y = getAnchorY() + getY() + size;

        g.setFont(font.deriveFont(Font.PLAIN, size));
        g.setColor(Color.WHITE);
        g.drawString(fps, x, y);
    }
}
