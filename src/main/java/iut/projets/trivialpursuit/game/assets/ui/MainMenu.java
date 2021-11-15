package iut.projets.trivialpursuit.game.assets.ui;

import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIButton;
import iut.projets.trivialpursuit.engine.userinterface.UIContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.game.Resources;

public class MainMenu extends UIContainer {

    private Runnable onPlayClicked, onQuitClicked;

    public MainMenu() {
        super();
        onPlayClicked = () -> {};
        onQuitClicked = () -> {};

        UIImage background = new UIImage();
        background.setImage(Resources.getImage("/images/main-menu-background.png"));
        background.setSize(100);
        background.setPosition(new Vector2D(0, 0));
        addElement(background);

        UIButton playButton = new UIButton("Jouer");
        playButton.setRenderOrder(1);
        playButton.setAnchor(Anchor.CENTER_CENTER);
        playButton.setPosition(new Vector2D(0, 20));
        playButton.onClick( () -> onPlayClicked.run() );
        addElement(playButton);

        UIButton quitButton = new UIButton("Quitter");
        quitButton.setRenderOrder(1);
        quitButton.setAnchor(Anchor.CENTER_CENTER);
        quitButton.setPosition(new Vector2D(0, 34));
        quitButton.onClick( () -> onQuitClicked.run() );
        addElement(quitButton);

        UIImage logo = new UIImage();
        logo.setRenderOrder(1);
        logo.setImage(Resources.getImage("/images/trivial-pursuit-logo.png"));
        logo.setSize(50);
        logo.setPosition(new Vector2D(0, -10));
        addElement(logo);
    }

    public void onPlayClicked(Runnable onPlayClicked) {
        this.onPlayClicked = onPlayClicked;
    }

    public void onQuitClicked(Runnable onQuitClicked) {
        this.onQuitClicked = onQuitClicked;
    }

}
