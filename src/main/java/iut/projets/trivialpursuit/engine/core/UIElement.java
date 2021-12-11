package iut.projets.trivialpursuit.engine.core;

import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;

/**
 * Un élément de l'interface utilisateur.
 */
public abstract class UIElement extends GameObject {

    private static double unitSize = 100;
    private int containerX, containerY, containerWidth, containerHeight;
    private static Vector2D mousePosition;
    private static boolean mousePressed;
    private Anchor anchor;
    private Vector2D position, size;
    private Vector2D alignment;
    private int renderOrder;
    private boolean isFocusable, isParentFocusable;
    private double opacity, parentOpacity;

    /**
     * Points d'ancrage de l'élément à l'écran.
     */
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

    /**
     * Construit un élément.
     */
    public UIElement() {
        setAnchor(Anchor.TOP_LEFT);
        position = new Vector2D(0, 0);
        size = new Vector2D(0, 0);
        alignment = new Vector2D(0, 0);
        renderOrder = 0;
        isFocusable = true;
        isParentFocusable = true;
        opacity = 1;
        parentOpacity = 1;
    }

    /**
     * Renvoie la coordonnée X du coin supérieur gauche du conteneur de l'élément.
     * @return La coordonnée X du coin supérieur gauche du conteneur de l'élément.
     */
    public final int getContainerX() {
        return containerX;
    }

    /**
     * Renvoie la coordonnée Y du coin supérieur gauche du conteneur de l'élément.
     * @return La coordonnée Y du coin supérieur gauche du conteneur de l'élément.
     */
    public final int getContainerY() {
        return containerY;
    }

    /**
     * Renvoie la largeur du conteneur de l'élément.
     * @return La largeur du conteneur de l'élément.
     */
    public final int getContainerWidth() {
        return containerWidth;
    }

    /**
     * Renvoie la hauteur du conteneur de l'élément.
     * @return La hauteur du conteneur de l'élément.
     */
    public final int getContainerHeight() {
        return containerHeight;
    }

    /**
     * Définit la coordonnée X du coin supérieur gauche du conteneur de l'élément.
     * @param containerX La coordonnée X du coin supérieur gauche du conteneur de l'élément.
     */
    public final void setContainerX(int containerX) {
        this.containerX = containerX;
    }

    /**
     * Définit la coordonnée Y du coin supérieur gauche du conteneur de l'élément.
     * @param containerY La coordonnée Y du coin supérieur gauche du conteneur de l'élément.
     */
    public final void setContainerY(int containerY) {
        this.containerY = containerY;
    }

    /**
     * Définit la largeur du conteneur de l'élément.
     * @param containerHeight La largeur du conteneur de l'élément.
     */
    public final void setContainerHeight(int containerHeight) {
        this.containerHeight = containerHeight;
    }

    /**
     * Définit la hauteur du conteneur de l'élément.
     * @param containerWidth La hauteur du conteneur de l'élément.
     */
    public final void setContainerWidth(int containerWidth) {
        this.containerWidth = containerWidth;
    }

    /**
     * Renvoie la position de la souris à l'écran.
     * @return La position de la souris à l'écran.
     */
    public static Vector2D getMousePosition() {
        return mousePosition;
    }

    /**
     * Met à jour la position de la souris à l'écran.
     * @param mousePosition La position de la souris à l'écran.
     */
    public static void setMousePosition(Vector2D mousePosition) {
        UIElement.mousePosition = mousePosition;
    }

    /**
     * Renvoie vrai si le bouton gauche de la souris est pressé.
     * @return Vrai si le bouton gauche de la souris est pressé.
     */
    public static boolean getMousePressed() {
        return mousePressed;
    }

    /**
     * Met à jour l'état du bouton gauche de la souris.
     * @param mousePressed Vrai si le clic gauche de la souris est pressé.
     */
    public static void setMousePressed(boolean mousePressed) {
        UIElement.mousePressed = mousePressed;
    }

    /**
     * Définit le point d'ancrage de l'élément par rapport à son conteneur.
     * @param anchor Point d'ancrage.
     */
    public final void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    /**
     * Renvoie la coordonnée x du point d'ancrage de l'élément par rapport à son conteneur.
     * @return La coordonnée x du point d'ancrage de l'élément par rapport à son conteneur.
     */
    protected final int getAnchorX() {
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

    /**
     * Renvoie la coordonnée y du point d'ancrage de l'élément par rapport à son conteneur.
     * @return La coordonnée y du point d'ancrage de l'élément par rapport à son conteneur.
     */
    protected final int getAnchorY() {
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

    /**
     * Renvoie la position de l'élément dans son conteneur.
     * @return La position de l'élément dans son conteneur.
     */
    public final Vector2D getPosition() {
        return position;
    }

    /**
     * Définit la position de l'élément dans son conteneur.
     * @param position Position de l'élément.
     */
    public final void setPosition(Vector2D position) {
        this.position = position;
    }

    /**
     * Renvoie la taille de l'élément.
     * @return La taille de l'élément.
     */
    public final Vector2D getSize() {
        return size;
    }

    /**
     * Définit la taille de l'élément.
     * @param size La taille de l'élément.
     */
    public final void setSize(Vector2D size) {
        this.size = size;
    }

    /**
     * Renvoie la taille d'une unité à l'écran.
     * @return La taille d'une unité à l'écran.
     */
    public static double getUnitSizeOnScreen() {
        return unitSize;
    }

    /**
     * Définit la taille d'une unité à l'écran.
     * @param unitSize La taille d'une unité à l'écran.
     */
    public static void setUnitSizeOnScreen(double unitSize) {
        UIElement.unitSize = unitSize;
    }

    /**
     * Renvoie l'alignement de l'élément.
     * @return L'alignement de l'élément.
     */
    public final Vector2D getAlignment() {
        return alignment;
    }

    /**
     * Définit l'alignement de l'élément.
     * X = -1 : origine à droite
     * X = 1 : origine à gauche
     * Y = -1 : origine en bas
     * Y = 1 : origine en haut
     * @param alignment Alignement.
     */
    public final void setAlignment(Vector2D alignment) {
        this.alignment = alignment;
    }

    /**
     * Renvoie la coordonnée x de la position de l'élément à l'écran.
     * @return La coordonnée x de la position de l'élément à l'écran.
     */
    public final int getPositionXOnScreen() {
        return (int)(
                getAnchorX() + (getPosition().getX()*getUnitSizeOnScreen())
                        + ((getAlignment().getX()-1)*getWidthOnScreen()/2.0)
        );
    }

    /**
     * Renvoie la coordonnée y de la position de l'élément à l'écran.
     * @return La coordonnée y de la position de l'élément à l'écran.
     */
    public final int getPositionYOnScreen() {
        return (int)(
                getAnchorY() + (getPosition().getY()*getUnitSizeOnScreen())
                        + ((getAlignment().getY()-1)*getHeightOnScreen()/2.0)
        );
    }

    /**
     * Renvoie la largeur de l'élément à l'écran.
     * @return La largeur de l'élément à l'écran.
     */
    public final int getWidthOnScreen() {
        return (int)(getSize().getX()*getUnitSizeOnScreen());
    }

    /**
     * Renvoie la hauteur de l'élément à l'écran.
     * @return La hauteur de l'élément à l'écran.
     */
    public final int getHeightOnScreen() {
        return (int)(getSize().getY()*getUnitSizeOnScreen());
    }

    /**
     * Dessine l'élément.
     * @param g Contexte graphique dans lequel dessiner.
     */
    public abstract void draw(Graphics2D g);

    @Override
    public void start() {}

    @Override
    public void update(double frameTime) {}

    /**
     * Renvoie l'ordre dans lequel l'élément est rendu dans la scène.
     * @return L'ordre dans lequel l'élément est rendu dans la scène.
     */
    public final int getRenderOrder() {
        return renderOrder;
    }

    /**
     * Définit l'ordre dans lequel l'élément est rendu dans la scène.
     * @param renderOrder L'ordre dans élément l'acteur est rendu dans la scène.
     */
    public final void setRenderOrder(int renderOrder) {
        this.renderOrder = renderOrder;
    }

    /**
     * Renvoie vrai si l'élément peut recevoir les événements de la souris.
     * @return Vrai si l'élément peut recevoir les événements de la souris.
     */
    public final boolean isFocusable() {
        return isFocusable;
    }

    /**
     * Renvoie vrai si le conteneur de l'élément peut recevoir les événements de la souris.
     * @return Vrai si le conteneur de l'élément peut recevoir les événements de la souris.
     */
    protected final boolean isParentFocusable() {
        return isParentFocusable;
    }

    /**
     * Définit si l'élément peut recevoir les événements de la souris.
     * @param focusable Vrai si l'élément peut recevoir les événements de la souris.
     */
    public final void setFocusable(boolean focusable) {
        isFocusable = focusable;
    }

    /**
     * Met à jour si le conteneur de l'élément peut recevoir les événements de la souris ou non.
     * @param parentFocusable Vrai si le conteneur de l'élément peut recevoir les événements de la souris.
     */
    final void setParentFocusable(boolean parentFocusable) {
        isParentFocusable = parentFocusable;
    }

    /**
     * Renvoie l'opacité de l'élément.
     * @return L'opacité de l'élément.
     */
    public double getOpacity() {
        return opacity;
    }

    /**
     * Renvoie l'opacité du conteneur de l'élément.
     * @return L'opacité du conteneur de l'élément.
     */
    protected double getParentOpacity() {
        return parentOpacity;
    }

    /**
     * Définit l'opacité de l'élément.
     * @param opacity Opacité.
     */
    public void setOpacity(double opacity) {
        this.opacity = Math.max(Math.min(opacity, 1), 0);
    }

    /**
     * Met à jour l'opacité du conteneur de l'élément.
     * @param parentOpacity Opacité.
     */
    protected void setParentOpacity(double parentOpacity) {
        this.parentOpacity = parentOpacity;
    }
}
