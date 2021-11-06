package iut.projets.trivialpursuit.game.assets.ui;

import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIButton;
import iut.projets.trivialpursuit.engine.userinterface.UIContainer;

public class MainMenu extends UIContainer {

    private Runnable onPlayClicked, onQuitClicked;

    public MainMenu() {
        super();
        onPlayClicked = () -> {};
        onQuitClicked = () -> {};

        UIButton playButton = new UIButton("Jouer");
        playButton.setAnchor(Anchor.CENTER_CENTER);
        playButton.setPosition(new Vector2D(0, 20));
        playButton.onClick( () -> onPlayClicked.run() );
        addElement(playButton);

        UIButton quitButton = new UIButton("Quitter");
        quitButton.setAnchor(Anchor.CENTER_CENTER);
        quitButton.setPosition(new Vector2D(0, 34));
        quitButton.onClick( () -> onQuitClicked.run() );
        addElement(quitButton);
    }

    public void onPlayClicked(Runnable onPlayClicked) {
        this.onPlayClicked = onPlayClicked;
    }

    public void onQuitClicked(Runnable onQuitClicked) {
        this.onQuitClicked = onQuitClicked;
    }

}
