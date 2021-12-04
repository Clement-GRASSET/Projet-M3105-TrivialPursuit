package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.UIElement;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;

public class UIText extends UIElement {

    private String text;
    private Font font;
    private Color color;
    private double fontSize;
    private Anchor textAlign;

    public UIText() {
        text = "";
        font = new Font("Arial", Font.PLAIN, 1);
        color = Color.WHITE;
        fontSize = 3;
        textAlign = Anchor.TOP_LEFT;
    }

    public final String getText() {
        return text;
    }

    public final void setText(String text) {
        this.text = text;
    }

    public final Font getFont() {
        return font;
    }

    public final void setFont(Font font) {
        this.font = font;
    }

    public final Color getColor() {
        return color;
    }

    public final void setColor(Color color) {
        this.color = color;
    }

    public final double getFontSize() {
        return fontSize;
    }

    public final void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    public Anchor getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(Anchor textAlign) {
        this.textAlign = textAlign;
    }

    @Override
    public void draw(Graphics2D g) {

        double unit = getUnitSizeOnScreen();
        Font renderFont = font.deriveFont(Font.PLAIN, (float) (fontSize*unit));
        FontMetrics fontMetrics = g.getFontMetrics(renderFont);

        int x = getPositionXOnScreen();
        int y = getPositionYOnScreen();
        int width = getWidthOnScreen();
        int height = getHeightOnScreen();

        Vector2D textAlignPercent = new Vector2D(0,0);

        switch (textAlign) {
            case TOP_LEFT: textAlignPercent = new Vector2D(0,0); break;
            case TOP_CENTER: textAlignPercent = new Vector2D(0.5,0); break;
            case TOP_RIGHT: textAlignPercent = new Vector2D(1,0); break;
            case CENTER_LEFT: textAlignPercent = new Vector2D(0,0.5); break;
            case CENTER_CENTER: textAlignPercent = new Vector2D(0.5,0.5); break;
            case CENTER_RIGHT: textAlignPercent = new Vector2D(1,0.5); break;
            case BOTTOM_LEFT: textAlignPercent = new Vector2D(0,1); break;
            case BOTTOM_CENTER: textAlignPercent = new Vector2D(0.5,1); break;
            case BOTTOM_RIGHT: textAlignPercent = new Vector2D(1,1); break;
        }

        x += textAlignPercent.getX() * (width - fontMetrics.stringWidth(text));
        y += textAlignPercent.getY() * (height - fontMetrics.getHeight()) + fontMetrics.getAscent();

        g.translate(x, y);
        g.setFont(renderFont);
        g.setColor(color);
        g.drawString(text, 0, 0);
        g.translate(-x, -y);
    }

}
