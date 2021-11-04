package iut.projets.trivialpursuit.game.assets.ui;

import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIButton;
import iut.projets.trivialpursuit.engine.userinterface.UIContainer;

public class MainMenu extends UIContainer {

    public MainMenu() {
        super();

        UIButton playButton = new UIButton("Jouer");
        playButton.setAnchor(Anchor.CENTER_CENTER);
        playButton.setPosition(new Vector2D(0, -10));
        addElement(playButton);

        UIButton quitButton = new UIButton("Quitter");
        quitButton.setAnchor(Anchor.CENTER_CENTER);
        quitButton.setPosition(new Vector2D(0, 10));
        addElement(quitButton);
    }

}
