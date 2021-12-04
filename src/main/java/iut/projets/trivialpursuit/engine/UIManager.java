package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.core.UIElement;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class UIManager {

    private static int width = 0, height = 0;
    private static Vector2D mousePosition = new Vector2D(0,0);
    private static boolean mousePressed = false;
    private static final List<UIElement> elements = new ArrayList<>(), elementsToRemove = new ArrayList<>(), elementsToAdd = new ArrayList<>();

    public static void setScreenSize(int width, int height) {
        UIManager.width = width;
        UIManager.height = height;
    }

    public static void setMousePosition(Vector2D mousePosition) {
        UIManager.mousePosition = mousePosition;
    }

    public static void setMousePressed(boolean mousePressed) {
        UIManager.mousePressed = mousePressed;
    }

    public static void addElement(UIElement element) {
        elementsToAdd.add(element);
        element.start();
    }

    public static void removeElement(UIElement element) {
        elementsToRemove.add(element);
    }

    public static void tick(double frameTime) {
        UIElement.setUnitSize(height /100.0);
        UIElement.setMousePosition(mousePosition);
        UIElement.setMousePressed(mousePressed);

        for (UIElement element : elementsToAdd)
            elements.add(element);
        elementsToAdd.clear();

        for (UIElement element : elements) {
            element.setContainerX(0);
            element.setContainerY(0);
            element.setContainerWidth(width);
            element.setContainerHeight(height);
            element.tick(frameTime);
        }

        for (UIElement element : elementsToRemove)
            elements.remove(element);
        elementsToRemove.clear();
    }

    public static void render(Graphics2D g) {
        elements.sort(Comparator.comparingInt(UIElement::getRenderOrder));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (UIElement element : elements)
            element.draw(g);
    }

}
