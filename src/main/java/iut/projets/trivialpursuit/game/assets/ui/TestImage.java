package iut.projets.trivialpursuit.game.assets.ui;

import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.game.Resources;

public class TestImage extends UIImage {

    public TestImage() {
        super();
        setImage(Resources.getImage("/images/iut-logo.png"));

        setSize(20);
        setAlignment(new Vector2D(0, 0));
    }

    @Override
    public void update(double frameTime) {
        getRotation().setRad(System.currentTimeMillis()*0.002);
    }
}
