package iut.projets.trivialpursuit.engine.core;

import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;

public abstract class UIElement extends GameObject {

    private static double unitSize = 100;
    private int containerX, containerY, containerWidth, containerHeight;
    private static Vector2D mousePosition;
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

    public final int getContainerX() {
        return containerX;
    }

    public final int getContainerY() {
        return containerY;
    }

    public final int getContainerWidth() {
        return containerWidth;
    }

    public final int getContainerHeight() {
        return containerHeight;
    }

    public final void setContainerX(int containerX) {
        this.containerX = containerX;
    }

    public final void setContainerY(int containerY) {
        this.containerY = containerY;
    }

    public final void setContainerHeight(int containerHeight) {
        this.containerHeight = containerHeight;
    }

    public final void setContainerWidth(int containerWidth) {
        this.containerWidth = containerWidth;
    }

    public static Vector2D getMousePosition() {
        return mousePosition;
    }

    public static void setMousePosition(Vector2D mousePosition) {
        UIElement.mousePosition = mousePosition;
    }

    public static void setMousePressed(boolean mousePressed) {
        UIElement.mousePressed = mousePressed;
    }

    public static boolean getMousePressed() {
        return mousePressed;
    }

    public final void setAnchor(Anchor anchor) {
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

    public final Vector2D getSize() {
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

    public final int getPositionXOnScreen() {
        return (int)(
                getAnchorX() + (getPosition().getX()*getUnitSizeOnScreen())
                        + ((getAlignmentX()-1)*getWidthOnScreen()/2.0)
        );
    }

    public final int getPositionYOnScreen() {
        return (int)(
                getAnchorY() + (getPosition().getY()*getUnitSizeOnScreen())
                        + ((getAlignmentY()-1)*getHeightOnScreen()/2.0)
        );
    }

    public final int getWidthOnScreen() {
        return (int)(getSize().getX()*getUnitSizeOnScreen());
    }

    public final int getHeightOnScreen() {
        return (int)(getSize().getY()*getUnitSizeOnScreen());
    }

    public abstract void draw(Graphics2D g);

    @Override
    public void start() {}

    @Override
    public void update(double frameTime) {}

    public final int getRenderOrder() {
        return renderOrder;
    }

    public final void setRenderOrder(int renderOrder) {
        this.renderOrder = renderOrder;
    }
}
