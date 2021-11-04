package iut.projets.trivialpursuit.engine.userinterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIContainer extends UIElement {
    List<UIElement> elements;

    public UIContainer() {
        elements = new ArrayList<>();
    }

    public void addElement(UIElement element) {
        elements.add(element);
    }

    @Override
    public void draw_all(Graphics2D g) {
        super.draw_all(g);
        for (UIElement element : elements) {
            element.draw_all(g);
        }
    }
}
