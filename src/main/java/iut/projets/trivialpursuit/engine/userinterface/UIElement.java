package iut.projets.trivialpursuit.engine.userinterface;

import iut.projets.trivialpursuit.engine.game.GameObject;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;

public class UIElement extends GameObject {

    private static double unit = 100;
    private static int screenWidth, screenHeight;
    private static int mouseX, mouseY;
    private static boolean mousePressed;
    private Anchor anchor;
    private Vector2D position;
    private Vector2D alignment;
    private int renderOrder;

    public enum Anchor {
        TOP_LEFT,
        CENTER_LEFT,
        BOTTOM_LEFT,
        TOP_CENTER,
        CENTER_CENTER,
        BOTTOM_CENTER,
        TOP_RIGHT,
        CENTER_RIGHT,
        BOTTOM_RIGHT
    }

    public UIElement() {
        setAnchor(Anchor.TOP_LEFT);
        position = new Vector2D(0, 0);
        alignment = new Vector2D(0, 0);
        renderOrder = 0;
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

    public static void setMousePressed(boolean mousePressed) {
        UIElement.mousePressed = mousePressed;
    }

    public static boolean getMousePressed() {
        return mousePressed;
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
        this.position = position;
    }

    public static double getUnitSizeOnScreen() {
        return screenHeight/unit;
    }

    public final Vector2D getPosition() {
        return position;
    }

    public final double getAlignmentX() {
        return alignment.getX();
    }

    public final double getAlignmentY() {
        return alignment.getY();
    }

    public final void setAlignment(Vector2D alignment) {
        this.alignment = alignment;
    }

    public void draw(Graphics2D g) {}

    @Override
    public void start() {}

    @Override
    public void update(double frameTime) {}

    public int getRenderOrder() {
        return renderOrder;
    }

    public void setRenderOrder(int renderOrder) {
        this.renderOrder = renderOrder;
    }
}
