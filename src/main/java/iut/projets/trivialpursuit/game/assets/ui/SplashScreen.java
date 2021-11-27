package iut.projets.trivialpursuit.game.assets.ui;

import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.game.Resources;

public class SplashScreen extends UIImage {

    public SplashScreen() {
        super();
        setImage(Resources.getImage("/images/iut-logo.png"));
        setSize(20);
    }

}
