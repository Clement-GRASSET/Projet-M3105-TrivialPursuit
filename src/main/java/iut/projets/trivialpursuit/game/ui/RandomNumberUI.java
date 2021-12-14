package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.basetypes.*;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class RandomNumberUI extends UIScreenContainer {

    private int number;
    private Runnable onDestroy;

    private static class RandomButton extends UIButton {
        private final int number;
        RandomButton(int number) {
            this.number = number;
            setAnchor(Anchor.CENTER_CENTER);
            setAlignment(new Vector2D(0,0));
            setSize(new Vector2D(25, 25));
            setDefaultImage(Resources.getImage("/images/random_box.png"));
            setHoverImage(Resources.getImage("/images/random_box_hover.png"));
            setPressedImage(Resources.getImage("/images/random_box_active.png"));
            setHoverSound("/sounds/ui/hover.wav");
            setPressSound("/sounds/ui/click.wav");
        }
    }

    public RandomNumberUI() {
        onDestroy = () -> {};

        setOpacity(0);

        UIImage background = new UIImage();
        background.setSize(new Vector2D(500, 100));
        background.setOpacity(0.7);
        addElement(background);

        UIText text = new UIText();
        text.setAnchor(Anchor.CENTER_CENTER);
        text.setAlignment(new Vector2D(0,0));
        text.setPosition(new Vector2D(0, -20));
        text.setTextAlign(Anchor.CENTER_CENTER);
        text.setText("Choisissez un nombre");
        text.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
        text.setFontSize(7);
        addElement(text);

        List<RandomButton> randomButtons = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            RandomButton button = new RandomButton((int) Math.floor(Math.random()*6)+1);
            randomButtons.add(button);
            button.onClick(() -> {
                Resources.getSound("/sounds/ui/button_number.wav").play();
                number = button.number;
                for (RandomButton b : randomButtons) {
                    b.setFocusable(false);
                    UIText number_text = new UIText();
                    number_text.setAnchor(Anchor.CENTER_CENTER);
                    number_text.setAlignment(new Vector2D(0,0));
                    number_text.setTextAlign(Anchor.CENTER_CENTER);
                    number_text.setText(String.valueOf(b.number));
                    number_text.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
                    b.setDefaultImage(Resources.getImage((button == b) ? "/images/random_box_empty_chosen.png" : "/images/random_box_empty.png"));
                    b.addElement(number_text);

                    Animation animation = new Animation(new Keyframe[] {
                            new Keyframe(0,0),
                            new Keyframe(1,0.1),
                    });
                    animation.onUpdate(() -> {
                        number_text.setFontSize(Animation.interpolate(2, 10, animation.getValue()));
                        if (b == button) {
                            double size = Animation.interpolate(25, 32, animation.getValue());
                            b.setSize(new Vector2D(size, size));
                        }
                    });
                    animation.start(this);
                }
                Animation animation = new Animation(new Keyframe[] {
                        new Keyframe(1, 0),
                        new Keyframe(1, 2),
                        new Keyframe(0, 2.2),
                });
                animation.onUpdate(() -> {
                    setOpacity(animation.getValue());
                });
                animation.onFinish(() -> {
                    onDestroy.run();
                });
                animation.start(this);
            });
            button.setPosition(new Vector2D(i*35, 10));
            addElement(button);
        }

        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(1, 0.2),
        });
        animation.onUpdate(() -> {
            setOpacity(animation.getValue());
        });
        animation.start(this);
    }

    public int getNumber() {
        return number;
    }

    /**
     * Définit la fonction à exécuter après que le joueur ait choisi un nombre.
     * @param onDestroy Fonction à exécuter.
     */
    public void onDestroy(Runnable onDestroy) {
        this.onDestroy = onDestroy;
    }
}
