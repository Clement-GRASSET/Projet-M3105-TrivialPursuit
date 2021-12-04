package iut.projets.trivialpursuit.engine.core;

import iut.projets.trivialpursuit.engine.core.UIElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class UIContainer extends UIElement {
    List<UIElement> elements, elementsToAdd, elementsToRemove;

    public UIContainer() {
        super();
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
