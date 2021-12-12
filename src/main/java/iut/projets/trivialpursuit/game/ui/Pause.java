package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.basetypes.UIImage;
import iut.projets.trivialpursuit.engine.basetypes.UIScreenContainer;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;

public class Pause extends UIScreenContainer {

    private Runnable onResume, onQuit;

    public Pause() {
        onResume = () -> {};
        onQuit = () -> {};

        UIImage background = new UIImage();
        background.setPosition(new Vector2D(0,0));
        background.setSize(new Vector2D(500,100));
        background.setAnchor(Anchor.CENTER_CENTER);
        background.setAlignment(new Vector2D(0,0));
        background.setImageUniform(Color.BLACK);
        background.setOpacity(0.9);
        addElement(background);

        UIScreenContainer main = new UIScreenContainer();
        addElement(main);

        UITextButton playButton = new UITextButton("Reprendre");
        playButton.setRenderOrder(1);
        playButton.setAnchor(Anchor.CENTER_CENTER);
        playButton.setPosition(new Vector2D(0, -12));
        playButton.setSize(new Vector2D(30, 8));
        playButton.setPressSound("/sounds/ui/back.wav");
        playButton.onClick( () -> {
            onResume.run();
        } );
        main.addElement(playButton);

        UITextButton optionButton = new UITextButton("Options");
        optionButton.setRenderOrder(1);
        optionButton.setAnchor(Anchor.CENTER_CENTER);
        optionButton.setPosition(new Vector2D(0, 0));
        optionButton.setSize(new Vector2D(30, 8));
        optionButton.onClick( () -> {
            Options options = new Options(Color.WHITE);
            options.onBackClicked(() -> {
                removeElement(options);
                addElement(main);
            });
            addElement(options);
            removeElement(main);
        });
        main.addElement(optionButton);

        UITextButton quitButton = new UITextButton("Quitter");
        quitButton.setRenderOrder(1);
        quitButton.setAnchor(Anchor.CENTER_CENTER);
        quitButton.setPosition(new Vector2D(0, 12));
        quitButton.setSize(new Vector2D(30, 8));
        quitButton.setPressSound("/sounds/ui/back.wav");
        quitButton.onClick( () ->  {
            onQuit.run();
        });
        main.addElement(quitButton);
    }

    public void onResume(Runnable onResume) {
        this.onResume = onResume;
    }

    public void onQuit(Runnable onQuit) {
        this.onQuit = onQuit;
    }
}
