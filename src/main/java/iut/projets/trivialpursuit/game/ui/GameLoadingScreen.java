package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.game.Animation;
import iut.projets.trivialpursuit.engine.game.Keyframe;
import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.engine.userinterface.UserInterface;
import iut.projets.trivialpursuit.engine.Resources;

import java.awt.*;

public class GameLoadingScreen extends UIContainer {

    private final UIImage background, logo, loadingSpinner;
    private Runnable onConstructAnimationFinished;

    GameLoadingScreen() {
        onConstructAnimationFinished = () -> {};

        background = new UIImage();
        background.setImage(UserInterface.uniformImage(Color.BLACK));
        background.setSize(100);
        addElement(background);

        loadingSpinner = new LoadingIcon();
        loadingSpinner.setRenderOrder(1);
        addElement(loadingSpinner);

        logo = new UIImage();
        logo.setRenderOrder(2);
        logo.setImage(Resources.getImage("/images/trivial-pursuit-logo.png"));
        logo.setPosition(new Vector2D(0, 0));
        addElement(logo);
    }

    @Override
    public void start() {
        playConstructAnimation(onConstructAnimationFinished);
    }

    @Override
    public void update(double frameTime) {

    }

    public void remove() {
        playDestructAnimation(() -> {
            Engine.getUserInterface().removeElement(this);
        });
    }

    private void playConstructAnimation(Runnable then) {
        Animation logoAnimation = new Animation(new Keyframe[] {
                new Keyframe(200, 0),
                new Keyframe(50, 0.2)
        });
        logoAnimation.onUpdate(() -> {
            logo.setSize(logoAnimation.getValue());
        });
        logoAnimation.onFinish(() -> {
            onConstructAnimationFinished.run();
        });
        logoAnimation.start(this);
    }

    public void onConstructAnimationFinished(Runnable onConstructAnimationFinished) {
        this.onConstructAnimationFinished = onConstructAnimationFinished;
    }

    private void playDestructAnimation(Runnable then) {
        UIImage image_down = new UIImage(), image_up = new UIImage();

        image_down.setImage(UserInterface.uniformImage(new Color(13, 71, 101)));
        image_down.setRotation(Rotation.deg(-45));
        //image_down.setSize(new Vector2D(2, 300));
        image_down.setSize(new Vector2D(0, 0));
        //image_down.setPosition(new Vector2D(2, -2));
        image_down.setAnchor(Anchor.CENTER_CENTER);
        image_down.setRenderOrder(3);

        image_up.setImage(UserInterface.uniformImage(new Color(34, 56, 75)));
        image_up.setRotation(Rotation.deg(-45));
        //image_up.setSize(new Vector2D(2, 300));
        image_up.setSize(new Vector2D(0, 0));
        //image_up.setPosition(new Vector2D(-2, 2));
        image_up.setAnchor(Anchor.CENTER_CENTER);
        image_up.setRenderOrder(3);

        double images_height = 300.0, images_width_base = 2;
        Vector2D orientation = Vector2D.normalize(new Vector2D(1, -1));
        Animation animation1 = new Animation(new Keyframe[] {
                new Keyframe(300, 0),
                new Keyframe(0, 0.4),
                new Keyframe(0, 0.8)
        });
        animation1.onUpdate(() -> {
            double value = animation1.getValue();
            image_up.setPosition(Vector2D.add(
                    Vector2D.multiply(orientation, images_width_base),
                    new Vector2D(value, value)
            ));
            image_down.setPosition(Vector2D.add(
                    Vector2D.multiply(orientation, -images_width_base),
                    new Vector2D(value, value)
            ));
            image_up.setSize(new Vector2D(images_width_base, images_height));
            image_up.setSize(new Vector2D(images_width_base, images_height));
        });
        Animation animation2 = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(1, 0.5)
        });
        animation2.onUpdate(() -> {
            double value = animation2.getValue();
            double positionOffset = value*100;
            double scale = images_width_base + value*200;
            image_up.setPosition(
                    Vector2D.multiply(orientation, positionOffset)
            );
            image_down.setPosition(
                    Vector2D.multiply(orientation, -positionOffset)
            );
            image_up.setSize(new Vector2D(scale, images_height));
            image_down.setSize(new Vector2D(scale, images_height));
        });
        Animation animation3 = new Animation(new Keyframe[] {
                new Keyframe(200, 0),
                new Keyframe(0, 0.3)
        });
        animation3.onUpdate(() -> {
            double value = animation3.getValue();
            image_up.setSize(new Vector2D(value, images_height));
            image_down.setSize(new Vector2D(value, images_height));
        });
        animation1.onFinish(() -> {
            animation2.start(this);
        });
        animation2.onFinish(() -> {
            removeElement(logo);
            removeElement(background);
            removeElement(loadingSpinner);
            animation3.start(this);
        });
        animation3.onFinish(then);

        addElement(image_up);
        addElement(image_down);
        animation1.start(this);
    }

}
