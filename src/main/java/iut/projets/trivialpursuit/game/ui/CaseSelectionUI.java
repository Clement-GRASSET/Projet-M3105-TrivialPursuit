package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.basetypes.UIButton;
import iut.projets.trivialpursuit.engine.basetypes.UIScreenContainer;
import iut.projets.trivialpursuit.game.actors.Case;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaseSelectionUI extends UIScreenContainer {

    Map<UIButton, Case> buttons;
    private Runnable onDestroy;
    private Case selected;

    public CaseSelectionUI() {
        onDestroy = () -> {};
    }

    public void addButtons(List<Case> cases) {
        buttons = new HashMap<>();
        for (Case c : cases) {
            UIButton button = new UIButton("Case");
            button.setAnchor(Anchor.CENTER_CENTER);
            button.setAlignment(new Vector2D(0, 0));
            button.setSize(new Vector2D(8, 8));
            button.setPosition(c.getPosition());

            button.onClick(() -> {
                selected = c;
                UIManager.removeElement(this);
                onDestroy.run();
            });

            buttons.put(button, c);
            addElement(button);
        }
    }

    public void onDestroy(Runnable onDestroy) {
        this.onDestroy = onDestroy;
    }

    public Case getSelected() {
        return selected;
    }
}
