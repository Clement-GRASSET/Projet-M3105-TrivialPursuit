package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.UIContainer;
import iut.projets.trivialpursuit.engine.core.UIElement;

/**
 * Conteneur d'éléments de l'interface utilisateur qui fait toujours la taille de l'écran.
 */
public class UIScreenContainer extends UIContainer {

    /**
     * Construit un UIScreenContainer.
     */
    public UIScreenContainer() {
        super();
    }

    @Override
    protected void setElementContainer(UIElement element) {
        element.setContainerX(getContainerX());
        element.setContainerY(getContainerY());
        element.setContainerWidth(getContainerWidth());
        element.setContainerHeight(getContainerHeight());
    }

}
