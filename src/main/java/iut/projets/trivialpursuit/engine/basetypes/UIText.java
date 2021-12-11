package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.UIElement;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;

/**
 * Élément de texte de l'interface utilisateur.
 */
public class UIText extends UIElement {

    private String text;
    private Font font;
    private Color color;
    private double fontSize;
    private Anchor textAlign;

    /**
     * Construit un UIText.
     */
    public UIText() {
        text = "";
        font = new Font("Arial", Font.PLAIN, 1);
        color = Color.WHITE;
        fontSize = 3;
        textAlign = Anchor.TOP_LEFT;
    }

    /**
     * Renvoie le texte affiché.
     * @return Le texte affiché.
     */
    public final String getText() {
        return text;
    }

    /**
     * Définit le texte à afficher.
     * @param text Texte à afficher.
     */
    public final void setText(String text) {
        this.text = text;
    }

    /**
     * Renvoie la police d'écriture du texte.
     * @return La police d'écriture du texte.
     */
    public final Font getFont() {
        return font;
    }

    /**
     * Définit la police d'écriture du texte.
     * @param font Police d'écriture.
     */
    public final void setFont(Font font) {
        this.font = font;
    }

    /**
     * Renvoie la couleur du texte.
     * @return La couleur du texte.
     */
    public final Color getColor() {
        return color;
    }

    /**
     * Définit la couleur du texte.
     * @param color Définit la couleur du texte.
     */
    public final void setColor(Color color) {
        this.color = color;
    }

    /**
     * Renvoie la taille de la police.
     * @return La taille de la police.
     */
    public final double getFontSize() {
        return fontSize;
    }

    /**
     * Définit la taille de la police.
     * @param fontSize Taille de la police.
     */
    public final void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * Renvoie l'alignement du texte.
     * @return L'alignement du texte.
     */
    public Anchor getTextAlign() {
        return textAlign;
    }

    /**
     * Définit l'alignement du texte.
     * @param textAlign Alignement du texte.
     */
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

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (getOpacity()*getParentOpacity())));
        g.translate(x, y);
        g.setFont(renderFont);
        g.setColor(color);
        g.drawString(text, 0, 0);
        g.translate(-x, -y);
    }

}
