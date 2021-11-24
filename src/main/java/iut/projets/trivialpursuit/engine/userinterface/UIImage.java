package iut.projets.trivialpursuit.engine.userinterface;

import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImage extends UIElement {

    private Image image;
    private Vector2D size;
    private Rotation rotation;

    public UIImage() {
        super();
        BufferedImage bufferedImage = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        g.dispose();
        setImage(bufferedImage);
        setSize(10);
        rotation = Rotation.rad(0);
        setAnchor(Anchor.CENTER_CENTER);
    }

    @Override
    public void draw(Graphics2D g) {
        double unit = getUnitSizeOnScreen();
        double ratio = (double)image.getWidth(null) / image.getHeight(null);
        double angle = rotation.getRad();
        //double angle_correction = (Math.cos(angle)-1)*-0.5; // 0 si angle = 0°, 1 si angle = 180°
        //System.out.println(angle_correction);
        int width = (int)(size.getX()*unit*ratio);
        int height = (int)(size.getY()*unit);

        int x = (int)(
                getAnchorX() + (getPosition().getX()*getUnitSizeOnScreen())
                + ((getAlignmentX()-1)*width/2.0)
                //+ (int)(angle_correction*width)
        );

        int y = (int)(
                getAnchorY() + (getPosition().getY()*getUnitSizeOnScreen())
                + ((getAlignmentY()-1)*height/2.0)
                //+ (int)(angle_correction*height)
        );

        g.translate(x, y);
        g.rotate(angle, width/2.0, height/2.0);
        g.drawImage(image, 0, 0, width, height, null);
        g.rotate(-angle, width/2.0, height/2.0);
        g.translate(-x, -y);
    }

    public final void setImage(Image image) {
        this.image = image;
    }

    public final void setSize(double size) {
        this.size = new Vector2D(size, size);
    }

    public final void setSize(Vector2D size) {
        this.size = size;
    }

    public Vector2D getSize() {
        return size;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }
}
