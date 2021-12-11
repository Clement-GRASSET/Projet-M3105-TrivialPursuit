package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.basetypes.Animation;
import iut.projets.trivialpursuit.engine.basetypes.Keyframe;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.basetypes.*;
import iut.projets.trivialpursuit.game.Player;

public class NewTurnAnnouncement extends UIScreenContainer {

    private Runnable onDestroy;

    public NewTurnAnnouncement(Player player) {

        UIImage image = new UIImage();
        image.setAnchor(Anchor.CENTER_CENTER);
        image.setAlignment(new Vector2D(0,0));
        image.setPosition(new Vector2D(0,0));
        image.setSize(new Vector2D(300, 18));
        addElement(image);

        UIText text = new UIText();
        text.setText("Au tour de " + player.getProfile().getName() + " !");
        text.setAnchor(Anchor.CENTER_CENTER);
        text.setAlignment(new Vector2D(0,0));
        text.setPosition(new Vector2D(0,0));
        text.setTextAlign(Anchor.CENTER_CENTER);
        text.setFontSize(13);
        text.setColor(player.getPawnColor().getRGB());
        addElement(text);

        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(-30,0),
                new Keyframe(-2, 0.2),
                new Keyframe(2,2.8),
                new Keyframe(30, 3),
        });
        animation.onUpdate(() -> {
            text.setPosition(new Vector2D(animation.getValue(), 0));
        });
        animation.onFinish(() -> {
            onDestroy.run();
        });
        animation.start(this);
    }

    public void onDestroy(Runnable onDestroy) {
        this.onDestroy = onDestroy;
    }

}
