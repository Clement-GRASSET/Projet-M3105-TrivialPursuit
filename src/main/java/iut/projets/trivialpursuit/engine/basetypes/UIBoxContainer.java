package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.UIContainer;
import iut.projets.trivialpursuit.engine.core.UIElement;

public class UIBoxContainer extends UIContainer {

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
