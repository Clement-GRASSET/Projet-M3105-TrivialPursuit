package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.basetypes.Animation;
import iut.projets.trivialpursuit.engine.basetypes.Keyframe;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.basetypes.UIScreenContainer;
import iut.projets.trivialpursuit.engine.basetypes.UIText;

public class RandomNumberUI extends UIScreenContainer {

    private int number;
    private Runnable onDestroy;

    public RandomNumberUI() {
        onDestroy = () -> {};

        number = (int) Math.floor(Math.random()*6)+1;
        UIText text = new UIText();
        text.setText(String.valueOf(number));
        text.setAnchor(Anchor.CENTER_CENTER);
        text.setAlignment(new Vector2D(0,0));
        text.setPosition(new Vector2D(0,0));
        text.setTextAlign(Anchor.CENTER_CENTER);
        text.setFontSize(0);
        addElement(text);

        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(30, 0.1),
                new Keyframe(30, 2),
                new Keyframe(200, 2.1),
        });
        animation.onUpdate(() -> {
            text.setFontSize(animation.getValue());
        });
        animation.onFinish(() -> {
            UIManager.removeElement(this);
            onDestroy.run();
        });
        animation.start(this);
    }

    public int getNumber() {
        return number;
    }

    public void onDestroy(Runnable onDestroy) {
        this.onDestroy = onDestroy;
    }
}
