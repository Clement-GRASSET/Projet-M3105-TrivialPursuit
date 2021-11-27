package iut.projets.trivialpursuit.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Resources {

    public static Map<String, BufferedImage> images = new HashMap<>();

    public static Image getImage(String name) {
        if (images.containsKey(name)) {
            return images.get(name);
        } else {
            try {
                loadImage(name);
                return images.get(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void loadImage(String name) throws IOException {
        images.put( name, ImageIO.read( getResource(name) ) );
    }

    public static URL getResource(String name) {
        return Resources.class.getResource(name);
    }

}
