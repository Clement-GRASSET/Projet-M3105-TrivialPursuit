package iut.projets.trivialpursuit.engine.userinterface;

import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIButton extends UIElement {

    private UIImage btnImage;
    private Image defaultImage, hoverImage, pressedImage;

    public UIButton() {
        super();
        btnImage = (UIImage) addChildElement(UIImage.class);
        btnImage.setSize(new Vector2D(20, 10));
        setDefaultImage( makeColoredImage(new Color(255, 120, 0)) );
        setHoverImage( makeColoredImage(new Color(255, 180, 0)) );
        setPressedImage( makeColoredImage(new Color(255, 220, 0)) );
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

    @Override
    public void tick(double frameTime) {
        super.tick(frameTime);
        if (isHovered())
            btnImage.setImage(hoverImage);
        else
            btnImage.setImage(defaultImage);
    }
}
