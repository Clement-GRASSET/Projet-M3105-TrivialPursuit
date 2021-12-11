package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Un UIBoxContainer qui reçoit les clics de la souris.
 */
public class UIButton extends UIBoxContainer {

    private Image image, defaultImage, hoverImage, pressedImage;
    private boolean hovered, pressed;
    private Runnable onClick;

    /**
     * Construit un bouton
     */
    public UIButton() {
        super();

        setSize(new Vector2D(30, 10));
        setAlignment(new Vector2D(0, 0));
        setAnchor(Anchor.CENTER_CENTER);
        setDefaultImage( makeColoredImage(new Color(255, 120, 0)) );
        setHoverImage( makeColoredImage(new Color(255, 160, 0)) );
        setPressedImage( makeColoredImage(new Color(255, 220, 50)) );
        this.image = defaultImage;
        onClick = () -> {};
    }

    /**
     * Crée une image de dimensions 1x1.
     * @param color Couleur de l'image.
     * @return Image créée.
     */
    protected BufferedImage makeColoredImage(Color color) {
        int w = 1;
        int h = 1;
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, w, h);
        return image;
    }

    /**
     * Définit le fond affiché par défaut.
     * @param image Fond affiché par défaut.
     */
    public void setDefaultImage(Image image) {
        this.defaultImage = image;
    }

    /**
     * Définit le fond affiché lors du survol de la souris.
     * @param image Fond affiché lors du survol de la souris.
     */
    public void setHoverImage(Image image) {
        this.hoverImage = image;
    }

    /**
     * Définit le fond affiché lors du clic de la souris.
     * @param image Fond affiché lors du clic de la souris.
     */
    public void setPressedImage(Image image) {
        this.pressedImage = image;
    }

    /**
     * Renvoie vrai si la souris survole le bouton.
     * @return Vrai si la souris survole le bouton.
     */
    private boolean isHovered() {
        if (!isFocusable() || !isParentFocusable())
            return false;
        double mouseX = getMousePosition().getX();
        double mouseY = getMousePosition().getY();
        double unit = getUnitSizeOnScreen();
        double w = (getSize().getX()*unit);
        double h = (getSize().getY()*unit);
        double x = ( getPosition().getX()*unit + (getAlignment().getX()-1)/2 * w + getAnchorX() );
        double y = ( getPosition().getY()*unit + (getAlignment().getY()-1)/2 * h + getAnchorY() );
        return (mouseX > x && mouseX < x+w && mouseY > y && mouseY < y+h);
    }

    @Override
    public void tick(double frameTime) {
        super.tick(frameTime);

        if (pressed && !getMousePressed() && isHovered()) {
            onClick.run();
        }

        hovered = isHovered();
        pressed = hovered && getMousePressed();

        if (pressed)
            image = pressedImage;
        else if (hovered)
            image = hoverImage;
        else
            image = defaultImage;
    }

    @Override
    public void draw(Graphics2D g) {
        int width = getWidthOnScreen();
        int height = getHeightOnScreen();

        int x = getPositionXOnScreen();
        int y = getPositionYOnScreen();

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (getOpacity()*getParentOpacity())));
        g.translate(x, y);
        g.drawImage(image, 0, 0, width, height, null);
        g.translate(-x, -y);

        super.draw(g);
    }

    /**
     * Définit la fonction à exécuter lorsqu'on clique sur le bouton.
     * @param onClick Fonction à exécuter.
     */
    public void onClick(Runnable onClick) {
        this.onClick = onClick;
    }
}
