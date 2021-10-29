package iut.projets.trivialpursuit.engine.userinterface;

import iut.projets.trivialpursuit.engine.graphics.Actor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.List;
import java.util.ArrayList;

public class UserInterface {

    private BufferedImage UIbuffer;
    private int w, h;
    private int mouseX, mouseY;
    private List<UIElement> elements;

    public UserInterface() {
        w = 0;
        h = 0;
        setScreenSize(8, 8);
        elements = new ArrayList<>();
    }

    public void setScreenSize(int width, int height) {
        if (width == w && height == h)
            return;

        w = width;
        h = height;
        UIbuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public void setMousePosition(int x, int y) {
        mouseX = x;
        mouseY = y;
    }

    public BufferedImage getBuffer() {
        return UIbuffer;
    }

    public UIElement addElement(Class<? extends UIElement> ElementClass) {
        UIElement element;
        try {
            element = ElementClass.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        elements.add(element);
        return element;
    }

    public void clear() {
        elements.clear();
    }

    public void tick(double frametime) {
        UIElement.setScreenWidth(w);
        UIElement.setScreenHeight(h);
        UIElement.setMouseX(mouseX);
        UIElement.setMouseY(mouseY);
        for (UIElement element : elements)
        {
            element.tick(frametime);
        }
    }

    public void render(Graphics2D g) {
        int [] pixels = ( (DataBufferInt) UIbuffer.getRaster().getDataBuffer() ).getData();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (UIElement element : elements)
        {
            element.draw_all(g);
        }
    }

}