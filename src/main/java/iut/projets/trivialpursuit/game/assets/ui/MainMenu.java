package iut.projets.trivialpursuit.game.assets.ui;

import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIButton;
import iut.projets.trivialpursuit.engine.userinterface.UIContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.game.Resources;

public class MainMenu extends UIContainer {

    private Runnable onPlayClicked, onQuitClicked;
    private UIImage logo;
    private double compteur;

    public MainMenu() {
        super();
        compteur = 0;

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

        logo = new UIImage();
        logo.setRenderOrder(1);
        logo.setImage(Resources.getImage("/images/trivial-pursuit-logo.png"));
        logo.setSize(50);
        logo.setPosition(new Vector2D(0, -10));
        addElement(logo);
    }

    @Override
    public void update(double frameTime) {
        super.update(frameTime);
        compteur += frameTime;
        double period = 60/117.0;
        double scale = -Math.cos(compteur*2*Math.PI/period);
        logo.setSize( Math.pow(scale+0.5, 5)*0.6 + 50);
    }

    public void onPlayClicked(Runnable onPlayClicked) {
        this.onPlayClicked = onPlayClicked;
    }

    public void onQuitClicked(Runnable onQuitClicked) {
        this.onQuitClicked = onQuitClicked;
    }

}
