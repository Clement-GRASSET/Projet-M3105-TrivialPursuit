package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.UIContainer;
import iut.projets.trivialpursuit.engine.core.UIElement;

/**
 * Conteneur d'éléments de l'interface utilisateur.
 */
public class UIBoxContainer extends UIContainer {

    /**
     * Construit un UIBoxContainer.
     */
    public UIBoxContainer() {
        super();
    }

    @Override
    protected void setElementContainer(UIElement element) {
        element.setContainerX(getPositionXOnScreen());
        element.setContainerY(getPositionYOnScreen());
        element.setContainerWidth(getWidthOnScreen());
        element.setContainerHeight(getHeightOnScreen());
    }

}
