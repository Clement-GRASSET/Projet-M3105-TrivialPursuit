package iut.projets.trivialpursuit.engine.userinterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class UIContainer extends UIElement {
    List<UIElement> elements, elementsToAdd, elementsToRemove;

    public UIContainer() {
        elements = new ArrayList<>();
        elementsToAdd = new ArrayList<>();
        elementsToRemove = new ArrayList<>();
    }

    public void removeElement(UIElement element) {
        elementsToRemove.add(element);
    }

    public void addElement(UIElement element) {
        elementsToAdd.add(element);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        for (UIElement element : elements) {
            element.draw(g);
        }
    }

    @Override
    public void tick(double frameTime) {
        super.tick(frameTime);
        for (UIElement element : elements) {
            element.tick(frameTime);
        }
        for (UIElement element : elementsToAdd) {
            elements.add(element);
        }
        for (UIElement element : elementsToRemove) {
            elements.remove(element);
        }
        elementsToAdd.clear();
        elementsToRemove.clear();
        elements.sort(new Comparator<UIElement>() {
            @Override
            public int compare(UIElement o1, UIElement o2) {
                return o1.getRenderOrder() - o2.getRenderOrder();
            }
        });
    }
}
