package iut.projets.trivialpursuit.engine.userinterface;

import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImage extends UIElement {

    private Image image;
    private Rotation rotation;

    public UIImage() {
        super();
        image = new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        setSize(10);
        rotation = Rotation.rad(0);
        setAnchor(Anchor.CENTER_CENTER);
    }

    @Override
    public void draw(Graphics2D g) {
        double unit = getUnitSizeOnScreen();
        double angle = rotation.getRad();
        int width = getWidthOnScreen();
        int height = getHeightOnScreen();

        int x = getPositionXOnScreen();

        int y = getPositionYOnScreen();

        g.translate(x, y);
        g.rotate(angle, width/2.0, height/2.0);
        g.drawImage(image, 0, 0, width, height, null);
        g.rotate(-angle, width/2.0, height/2.0);
        g.translate(-x, -y);
    }

    public final void setImage(Image image) {
        this.image = image;
    }

    public void setImageUniform(Color color) {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.fillRect(0,0,1,1);
        g.dispose();
        this.image = image;
    }

    public final void setSize(double size) {
        double ratio = (double)image.getWidth(null) / image.getHeight(null);
        setSize(new Vector2D(size*ratio, size));
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }
}
