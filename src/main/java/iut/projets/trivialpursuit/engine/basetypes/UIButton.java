package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIButton extends UIBoxContainer {

    private Image image, defaultImage, hoverImage, pressedImage;
    private boolean hovered, pressed;
    private Runnable onClick;

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

    protected BufferedImage makeColoredImage(Color color) {
        int w = 1;
        int h = 1;
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, w, h);
        return image;
    }

    public void setDefaultImage(Image image) {
        this.defaultImage = image;
    }

    public void setHoverImage(Image image) {
        this.hoverImage = image;
    }

    public void setPressedImage(Image image) {
        this.pressedImage = image;
    }

    private boolean isHovered() {
        if (!isFocusable() || !isParentFocusable())
            return false;
        double mouseX = getMousePosition().getX();
        double mouseY = getMousePosition().getY();
        double unit = getUnitSizeOnScreen();
        double w = (getSize().getX()*unit);
        double h = (getSize().getY()*unit);
        double x = ( getPosition().getX()*unit + (getAlignmentX()-1)/2 * w + getAnchorX() );
        double y = ( getPosition().getY()*unit + (getAlignmentY()-1)/2 * h + getAnchorY() );
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

    public void onClick(Runnable onClick) {
        this.onClick = onClick;
    }
}
