package iut.projets.trivialpursuit.engine.userinterface;

import iut.projets.trivialpursuit.engine.game.GameObject;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;

public class UIElement extends GameObject {

    private static double unitSize = 100;
    private int containerX, containerY, containerWidth, containerHeight;
    private static int mouseX, mouseY;
    private static boolean mousePressed;
    private Anchor anchor;
    private Vector2D position, size;
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
        size = new Vector2D(0, 0);
        alignment = new Vector2D(0, 0);
        renderOrder = 0;
    }

    public static void setUnitSize(int unitSize) {
        UIElement.unitSize = unitSize;
    }

    public int getContainerX() {
        return containerX;
    }

    public int getContainerY() {
        return containerY;
    }

    public int getContainerWidth() {
        return containerWidth;
    }

    public int getContainerHeight() {
        return containerHeight;
    }

    public void setContainerX(int containerX) {
        this.containerX = containerX;
    }

    public void setContainerY(int containerY) {
        this.containerY = containerY;
    }

    public void setContainerHeight(int containerHeight) {
        this.containerHeight = containerHeight;
    }

    public void setContainerWidth(int containerWidth) {
        this.containerWidth = containerWidth;
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
        int x = containerX;
        switch (anchor) {
            case TOP_LEFT:
            case CENTER_LEFT:
            case BOTTOM_LEFT:
                x += 0;
                break;
            case TOP_CENTER:
            case CENTER_CENTER:
            case BOTTOM_CENTER:
                x += containerWidth /2;
                break;
            case TOP_RIGHT:
            case CENTER_RIGHT:
            case BOTTOM_RIGHT:
                x += containerWidth;
                break;
        }
        return x;
    }

    public final int getAnchorY() {
        int y = containerY;
        switch (anchor) {
            case TOP_LEFT:
            case TOP_CENTER:
            case TOP_RIGHT:
                y += 0;
                break;
            case CENTER_LEFT:
            case CENTER_CENTER:
            case CENTER_RIGHT:
                y += containerHeight /2;
                break;
            case BOTTOM_LEFT:
            case BOTTOM_CENTER:
            case BOTTOM_RIGHT:
                y += containerHeight;
                break;
        }
        return y;
    }

    public final void setPosition(Vector2D position) {
        this.position = position;
    }

    public final void setSize(Vector2D size) {
        this.size = size;
    }

    public Vector2D getSize() {
        return size;
    }

    public static double getUnitSizeOnScreen() {
        return unitSize;
    }

    public static void setUnitSize(double unitSize) {
        UIElement.unitSize = unitSize;
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

    public int getPositionXOnScreen() {
        return (int)(
                getAnchorX() + (getPosition().getX()*getUnitSizeOnScreen())
                        + ((getAlignmentX()-1)*getWidthOnScreen()/2.0)
        );
    }

    public int getPositionYOnScreen() {
        return (int)(
                getAnchorY() + (getPosition().getY()*getUnitSizeOnScreen())
                        + ((getAlignmentY()-1)*getHeightOnScreen()/2.0)
        );
    }

    public int getWidthOnScreen() {
        return (int)(getSize().getX()*getUnitSizeOnScreen());
    }

    public int getHeightOnScreen() {
        return (int)(getSize().getY()*getUnitSizeOnScreen());
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
