package iut.projets.trivialpursuit.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Resources {

    public static Map<String, Image> images = new HashMap<>();

    public static Image getImage(String name) {
        if (images.containsKey(name)) {
            return images.get(name);
        } else {
            try {
                return loadImage(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Image loadImage(String name) throws IOException {
        return ImageIO.read( getResource(name) );
    }

    public static URL getResource(String name) {
        return Resources.class.getResource(name);
    }

}
