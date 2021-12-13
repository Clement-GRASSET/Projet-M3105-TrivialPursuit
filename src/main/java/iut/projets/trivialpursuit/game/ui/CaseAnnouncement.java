package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.basetypes.*;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;

public class CaseAnnouncement extends UIScreenContainer {

    private Runnable onDestroy;

    public CaseAnnouncement(String text, Color color) {
        onDestroy = () -> {};

        setOpacity(0);

        UIImage background = new UIImage();
        background.setSize(new Vector2D(500, 100));
        background.setOpacity(0.7);
        addElement(background);

        UIText textElement = new UIText();
        textElement.setAnchor(Anchor.CENTER_CENTER);
        textElement.setAlignment(new Vector2D(0, 0));
        textElement.setTextAlign(Anchor.CENTER_CENTER);
        textElement.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
        textElement.setText(text);
        textElement.setColor(color);
        addElement(textElement);

        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(15, 0.2),
                new Keyframe(15, 2),
                new Keyframe(200, 2.2),
        });
        animation.onUpdate(() -> {
            textElement.setFontSize(animation.getValue());
        });
        animation.onFinish(() -> {
            onDestroy.run();
        });
        animation.start(this);

        Animation opacity_animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(1, 0.2),
                new Keyframe(1, 2),
                new Keyframe(0, 2.2),
        });
        opacity_animation.onUpdate(() -> {
            setOpacity(opacity_animation.getValue());
        });
        opacity_animation.start(this);
    }

    public void onDestroy(Runnable onDestroy) {
        this.onDestroy = onDestroy;
    }

}
