package iut.projets.trivialpursuit.game.assets.materials;

import iut.projets.trivialpursuit.engine.graphics.Material;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextMaterial extends Material {

    String text;

    public TextMaterial(String text) {
        super();
        color = new BufferedImage(200, 50, BufferedImage.TYPE_INT_RGB);
        this.text = text;
        updateBuffer();
    }

    public void SetText(String text) {
        this.text = text;
        updateBuffer();
    }

    private void updateBuffer() {
        Graphics2D g = (Graphics2D) color.getGraphics();
        g.setColor(Color.RED);
        g.fillRect(0,0,200,50);
        g.setColor(Color.GREEN);
        Font font = new Font("Arial", Font.PLAIN, 24);
        g.setFont(font);
        g.drawString(text, 20,30);
    }
}
