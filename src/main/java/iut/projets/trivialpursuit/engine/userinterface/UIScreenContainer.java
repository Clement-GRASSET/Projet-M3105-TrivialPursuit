package iut.projets.trivialpursuit.engine.userinterface;

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
