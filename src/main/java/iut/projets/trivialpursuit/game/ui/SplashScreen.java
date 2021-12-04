package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.basetypes.UIImage;
import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.basetypes.UIScreenContainer;

public class SplashScreen extends UIScreenContainer {

    public SplashScreen() {
        super();
        UIImage logo = new UIImage();
        logo.setImage(Resources.getImage("/images/iut-logo.png"));
        logo.setSize(20);
        addElement(logo);

        UIImage loadingIcon = new LoadingIcon();
        addElement(loadingIcon);
    }

}
