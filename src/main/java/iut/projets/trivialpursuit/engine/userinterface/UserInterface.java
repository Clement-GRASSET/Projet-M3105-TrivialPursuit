package iut.projets.trivialpursuit.engine.userinterface;

import iut.projets.trivialpursuit.engine.graphics.Actor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class UserInterface {

    private BufferedImage UIbuffer;
    private int w, h;
    private int mouseX, mouseY;
    private boolean mousePressed;
    private final List<UIElement> elements, elementsToRemove, elementsToAdd;

    public UserInterface() {
        w = 0;
        h = 0;
        mousePressed = false;
        setScreenSize(8, 8);
        elements = new ArrayList<>();
        elementsToRemove = new ArrayList<>();
        elementsToAdd = new ArrayList<>();
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

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public BufferedImage getBuffer() {
        return UIbuffer;
    }

    public void addElement(UIElement element) {
        elementsToAdd.add(element);
    }

    public void removeElement(UIElement element) {
        elementsToRemove.add(element);
    }

    public void clear() {
        elements.clear();
    }

    public void tick(double frametime) {
        UIElement.setScreenWidth(w);
        UIElement.setScreenHeight(h);
        UIElement.setMouseX(mouseX);
        UIElement.setMouseY(mouseY);
        UIElement.setMousePressed(mousePressed);
        for (UIElement element : elements)
        {
            element.tick(frametime);
        }
        for (UIElement element : elementsToAdd)
        {
            elements.add(element);
        }
        for (UIElement element : elementsToRemove)
        {
            elements.remove(element);
        }
        elementsToAdd.clear();
        elementsToRemove.clear();
        elements.sort(new Comparator<UIElement>() {
            @Override
            public int compare(UIElement o1, UIElement o2) {
                return o1.getRenderOrder() - o2.getRenderOrder();
            }
        });
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
