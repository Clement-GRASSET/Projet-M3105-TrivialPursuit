package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.core.UIElement;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * Gère l'interface utilisateur à l'écran.
 */
public class UIManager {

    private static int width = 0, height = 0;
    private static Vector2D mousePosition = new Vector2D(0,0);
    private static boolean mousePressed = false;
    private static final List<UIElement> elements = new ArrayList<>(), elementsToRemove = new ArrayList<>(), elementsToAdd = new ArrayList<>();

    /**
     * Met à jour les informations de taille de l'écran
     * @param width La largeur de l'écran
     * @param height La hauteur de l'écran
     */
    public static void setScreenSize(int width, int height) {
        UIManager.width = width;
        UIManager.height = height;
    }

    /**
     * Met à jour les informations de position de la souris à l'écran
     * @param mousePosition La position de la souris
     */
    public static void setMousePosition(Vector2D mousePosition) {
        UIManager.mousePosition = mousePosition;
    }

    /**
     * Met à jour le statut de clic de la souris.
     * @param mousePressed Vrai si le bouton gauche de la souris est pressé.
     */
    public static void setMousePressed(boolean mousePressed) {
        UIManager.mousePressed = mousePressed;
    }

    /**
     * Ajoute un élément à l'interface utilisateur.
     * @param element L'élément à ajouter.
     */
    public static void addElement(UIElement element) {
        elementsToAdd.add(element);
        element.start();
    }

    /**
     * Retire un élément de l'interface utilisateur.
     * @param element L'élément à retirer.
     */
    public static void removeElement(UIElement element) {
        elementsToRemove.add(element);
    }

    /**
     * Met à jour tous les éléments à l'écran.
     * @param frameTime La durée de la dernière frame.
     */
    public static void tick(double frameTime) {
        UIElement.setUnitSizeOnScreen(height /100.0);
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

    /**
     * Affiche les éléments.
     * @param g Contexte graphique dans lequel dessiner.
     */
    public static void render(Graphics2D g) {
        elements.sort(Comparator.comparingInt(UIElement::getRenderOrder));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (UIElement element : elements)
            element.draw(g);
    }

}
