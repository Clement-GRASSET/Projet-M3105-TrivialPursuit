package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.basetypes.UITextButton;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.basetypes.UIButton;
import iut.projets.trivialpursuit.engine.basetypes.UIScreenContainer;
import iut.projets.trivialpursuit.game.actors.Case;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaseSelectionUI extends UIScreenContainer {

    private static class CaseButton extends UIButton {
        CaseButton() {
            setAnchor(Anchor.CENTER_CENTER);
            setAlignment(new Vector2D(0, 0));
            setSize(new Vector2D(12, 12));
            setDefaultImage(Resources.getImage("/images/case_button.png"));
            setHoverImage(Resources.getImage("/images/case_button_hover.png"));
            setPressedImage(Resources.getImage("/images/case_button_press.png"));
        }
    }

    Map<UIButton, Case> buttons;
    private Runnable onDestroy;
    private Case selected;

    public CaseSelectionUI() {
        onDestroy = () -> {};
    }

    public void addButtons(List<Case> cases) {
        buttons = new HashMap<>();
        for (Case c : cases) {
            UIButton button = new CaseButton();
            button.setPosition(c.getPosition());

            button.onClick(() -> {
                selected = c;
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
