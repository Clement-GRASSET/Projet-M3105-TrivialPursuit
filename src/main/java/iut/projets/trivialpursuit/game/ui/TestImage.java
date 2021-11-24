package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.engine.Resources;

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
