package iut.projets.trivialpursuit.engine.userinterface;

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
