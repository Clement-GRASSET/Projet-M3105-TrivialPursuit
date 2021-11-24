package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.engine.Resources;

public class SplashScreen extends UIImage {

    public SplashScreen() {
        super();
        setImage(Resources.getImage("/images/iut-logo.png"));
        setSize(20);
    }

}
