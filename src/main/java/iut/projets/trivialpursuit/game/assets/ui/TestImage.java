package iut.projets.trivialpursuit.game.assets.ui;

import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TestImage extends UIImage {

    double compteur;

    public TestImage() {
        super();
        String uri = "/images/iut-logo.png";
        try {
            Image image = ImageIO.read(UIImage.class.getResource(uri));
            setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(20);
        setAlignment(new Vector2D(0, 0));
    }

    @Override
    public void update(double frameTime) {
        getRotation().setRad(System.currentTimeMillis()*0.002);
    }
}
