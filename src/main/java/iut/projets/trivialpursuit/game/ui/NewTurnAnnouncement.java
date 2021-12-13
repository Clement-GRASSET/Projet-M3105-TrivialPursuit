package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.basetypes.Animation;
import iut.projets.trivialpursuit.engine.basetypes.Keyframe;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.basetypes.*;
import iut.projets.trivialpursuit.game.Player;

public class NewTurnAnnouncement extends UIScreenContainer {

    private Runnable onDestroy;

    public NewTurnAnnouncement(Player player) {
        setOpacity(0);

        UIImage image = new UIImage();
        image.setAnchor(Anchor.CENTER_CENTER);
        image.setAlignment(new Vector2D(0,0));
        image.setPosition(new Vector2D(0,0));
        image.setSize(new Vector2D(300, 0));
        addElement(image);

        UIText text = new UIText();
        text.setText("Au tour de " + player.getProfile().getName() + " !");
        text.setAnchor(Anchor.CENTER_CENTER);
        text.setAlignment(new Vector2D(0,0));
        text.setPosition(new Vector2D(0,0));
        text.setTextAlign(Anchor.CENTER_CENTER);
        text.setFontSize(11);
        text.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
        text.setColor(player.getPawnColor().getRGB());
        addElement(text);

        Animation position_animation = new Animation(new Keyframe[] {
                new Keyframe(-30,0),
                new Keyframe(-2, 0.3),
                new Keyframe(2,2.7),
                new Keyframe(30, 3),
        });
        position_animation.onUpdate(() -> {
            text.setPosition(new Vector2D(position_animation.getValue(), 0));
        });
        position_animation.onFinish(() -> {
            onDestroy.run();
        });
        position_animation.start(this);

        Animation opacity_animation = new Animation(new Keyframe[] {
                new Keyframe(0,0),
                new Keyframe(1, 0.3),
                new Keyframe(1,2.7),
                new Keyframe(0, 3),
        });
        opacity_animation.onUpdate(() -> {
            setOpacity(opacity_animation.getValue());
            image.setSize(new Vector2D(image.getSize().getX(), opacity_animation.getValue()*23));
        });
        opacity_animation.start(this);
    }

    public void onDestroy(Runnable onDestroy) {
        this.onDestroy = onDestroy;
    }

}
