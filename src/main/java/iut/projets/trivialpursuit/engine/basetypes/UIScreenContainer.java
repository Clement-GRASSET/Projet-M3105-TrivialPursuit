package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.UIContainer;
import iut.projets.trivialpursuit.engine.core.UIElement;

public class UIScreenContainer extends UIContainer {

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
