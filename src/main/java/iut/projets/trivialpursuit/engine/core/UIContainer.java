package iut.projets.trivialpursuit.engine.core;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Un élément de l'interface utilisateur qui peut contenir d'autres éléments.
 */
public abstract class UIContainer extends UIElement {
    List<UIElement> elements, elementsToAdd, elementsToRemove;

    /**
     * Construit un conteneur.
     */
    public UIContainer() {
        super();
        elements = new ArrayList<>();
        elementsToAdd = new ArrayList<>();
        elementsToRemove = new ArrayList<>();
    }

    /**
     * Retire un élément au conteneur.
     * @param element Element à retirer.
     */
    public void removeElement(UIElement element) {
        elementsToRemove.add(element);
    }

    /**
     * Ajoute un élément au conteneur.
     * @param element Element à ajouter.
     */
    public void addElement(UIElement element) {
        elementsToAdd.add(element);
    }

    /**
     * Met à jour les bords d'écran d'un élément en fonction du conteneur dans lequel il se trouve.
     * Utile pour déplacer en bloc tous les éléments en même temps que le conteneur.
     * @param element L'élément à mettre à jour.
     */
    protected abstract void setElementContainer(UIElement element);

    @Override
    public void draw(Graphics2D g) {
        for (UIElement element : elements) {
            element.draw(g);
        }
    }

    @Override
    public void tick(double frameTime) {
        super.tick(frameTime);

        for (UIElement element : elementsToAdd) {
            elements.add(element);
        }
        elementsToAdd.clear();

        for (UIElement element : elements) {
            element.setParentFocusable(isFocusable() && isParentFocusable());
            element.setParentOpacity(getOpacity() * getParentOpacity());
            setElementContainer(element);
            element.tick(frameTime);
        }

        for (UIElement element : elementsToRemove) {
            elements.remove(element);
        }
        elementsToRemove.clear();

        elements.sort(new Comparator<UIElement>() {
            @Override
            public int compare(UIElement o1, UIElement o2) {
                return o1.getRenderOrder() - o2.getRenderOrder();
            }
        });
    }
}
