package iut.projets.trivialpursuit.engine.userinterface;

import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class UIElement {

    private static double unit = 100;
    private static int screenWidth, screenHeight;
    private static int mouseX, mouseY;
    private Anchor anchor;
    private final Vector2D position;
    private final Vector2D alignment;

    public enum Anchor {
        TOP_LEFT,
        CENTER_LEFT,
        BOTTOM_LEFT,
        TOP_CENTER,
        CENTER_CENTER,
        BOTTOM_CENTER,
        TOP_RIGHT,
        CENTER_RIGHT,
        BOTTOM_RIGHT;
    }

    public UIElement() {
        setAnchor(Anchor.TOP_LEFT);
        position = new Vector2D(0, 0);
        alignment = new Vector2D(0, 0);
        screenWidth = 1;
        screenHeight = 1;
    }

    public static void setUnit(int unit) {
        UIElement.unit = unit;
    }

    public static void setScreenHeight(int screenHeight) {
        UIElement.screenHeight = screenHeight;
    }

    public static void setScreenWidth(int screenWidth) {
        UIElement.screenWidth = screenWidth;
    }

    public static void setMouseX(int mouseX) {
        UIElement.mouseX = mouseX;
    }

    public static void setMouseY(int mouseY) {
        UIElement.mouseY = mouseY;
    }

    public static int getMouseX() {
        return mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public final int getAnchorX() {
        int x = 0;
        switch (anchor) {
            case TOP_LEFT:
            case CENTER_LEFT:
            case BOTTOM_LEFT:
                x = 0;
                break;
            case TOP_CENTER:
            case CENTER_CENTER:
            case BOTTOM_CENTER:
                x = screenWidth/2;
                break;
            case TOP_RIGHT:
            case CENTER_RIGHT:
            case BOTTOM_RIGHT:
                x = screenWidth;
                break;
        }
        return x;
    }

    public final int getAnchorY() {
        int y = 0;
        switch (anchor) {
            case TOP_LEFT:
            case TOP_CENTER:
            case TOP_RIGHT:
                y = 0;
                break;
            case CENTER_LEFT:
            case CENTER_CENTER:
            case CENTER_RIGHT:
                y = screenHeight/2;
                break;
            case BOTTOM_LEFT:
            case BOTTOM_CENTER:
            case BOTTOM_RIGHT:
                y = screenHeight;
                break;
        }
        return y;
    }

    public final void setPosition(Vector2D position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public static double getUnitSizeOnScreen() {
        return screenHeight/unit;
    }

    public final Vector2D getPosition() {
        return position;
    }

    public final int getX() {
        return (int)(position.getX()*getUnitSizeOnScreen());
    }

    public final int getY() {
        return (int)(position.getY()*getUnitSizeOnScreen());
    }

    public final double getAlignmentX() {
        return alignment.getX();
    }

    public final double getAlignmentY() {
        return alignment.getY();
    }

    public final void setAlignment(Vector2D alignment) {
        this.alignment.setX(alignment.getX());
        this.alignment.setY(alignment.getY());
    }

    public void draw_all(Graphics2D g) {
        this.draw(g);
    }

    protected void draw (Graphics2D g) {}

    public void tick(double frameTime) {
        update(frameTime);
    }

    protected void update(double frameTime) {}
}
