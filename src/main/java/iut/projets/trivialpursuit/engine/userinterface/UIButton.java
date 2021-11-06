package iut.projets.trivialpursuit.engine.userinterface;

import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class UIButton extends UIElement {

    private Image image, defaultImage, hoverImage, pressedImage;
    private Vector2D size;
    private Font font;
    private String text;
    private boolean hovered, pressed;
    private Runnable onClick;

    public UIButton(String text) {
        super();
        setText(text);
        setSize(new Vector2D(30, 10));
        setAlignment(new Vector2D(0, 0));
        setAnchor(Anchor.CENTER_CENTER);
        setFont(new Font("Arial", Font.PLAIN, 1));
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

    public final void setSize(double size) {
        this.size = new Vector2D(size, size);
    }

    public final void setSize(Vector2D size) {
        this.size = size;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setText(String text) {
        this.text = text;
    }

    private boolean isHovered() {
        int mouseX = getMouseX();
        int mouseY = getMouseY();
        double unit = getUnitSizeOnScreen();
        int w = (int)(size.getX()*unit);
        int h = (int)(size.getY()*unit);
        int x = (int)( getPosition().getX()*unit + (getAlignmentX()-1)/2 * w + getAnchorX() );
        int y = (int)( getPosition().getY()*unit + (getAlignmentY()-1)/2 * h + getAnchorY() );
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
    protected void draw(Graphics2D g) {
        double unit = getUnitSizeOnScreen();
        double ratio = (double)image.getWidth(null) / image.getHeight(null);
        int width = (int)(size.getX()*unit*ratio);
        int height = (int)(size.getY()*unit);

        int x = (int)(
                getAnchorX() + getPosition().getX()*getUnitSizeOnScreen()
                + ((getAlignmentX()-1)*width/2.0)
        );
        int y = (int)(
                getAnchorY() + getPosition().getY()*getUnitSizeOnScreen()
                + ((getAlignmentY()-1)*height/2.0)
        );

        int fontSize = (int)(unit*3);
        Font renderFont = font.deriveFont(Font.PLAIN, fontSize);
        FontMetrics fontMetrics = g.getFontMetrics(renderFont);

        g.translate(x, y);
        g.drawImage(image, 0, 0, width, height, null);
        g.setFont(renderFont);
        g.setColor(Color.BLACK);
        g.drawString(text, width/2 -fontMetrics.stringWidth(text)/2, height/2 - fontMetrics.getHeight()/2 + fontMetrics.getAscent());
        g.translate(-x, -y);
    }

    public void onClick(Runnable onClick) {
        this.onClick = onClick;
    }
}
