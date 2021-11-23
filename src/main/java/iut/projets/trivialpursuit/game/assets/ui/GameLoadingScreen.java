package iut.projets.trivialpursuit.game.assets.ui;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.game.Animation;
import iut.projets.trivialpursuit.engine.game.Keyframe;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.engine.userinterface.UserInterface;
import iut.projets.trivialpursuit.game.Resources;

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
        Animation logoAnimation = new Animation(new Keyframe[] {
                new Keyframe(50, 0),
                new Keyframe(200, 0.2)
        });
        logoAnimation.onUpdate(() -> {
            logo.setSize(logoAnimation.getValue());
        });
        logoAnimation.onFinish(() -> {
            then.run();
        });
        logoAnimation.start(this);
    }

}
