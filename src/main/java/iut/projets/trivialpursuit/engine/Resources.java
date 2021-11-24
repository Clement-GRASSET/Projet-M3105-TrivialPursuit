package iut.projets.trivialpursuit.engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Resources {

    private static final Map<String, BufferedImage> images = new HashMap<>();
    private static final Map<String, InputStream> inputStreams = new HashMap<>();
    private static final Map<String, Font> fonts = new HashMap<>();

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

    public static InputStream getInputStream(String name) {
        if (inputStreams.containsKey(name)) {
            return inputStreams.get(name);
        } else {
            try {
                loadInputStream(name);
                return inputStreams.get(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Font getFont(String name) {
        if (fonts.containsKey(name)) {
            return fonts.get(name);
        } else {
            try {
                loadFont(name);
                return fonts.get(name);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void loadImage(String name) throws IOException {
        System.out.println("Loading " + name + "...");
        images.put( name, ImageIO.read( getResource(name) ) );
    }

    public static void loadInputStream(String name) throws IOException {
        System.out.println("Loading " + name + "...");
        inputStreams.put( name, getResourceAsStream(name) );
    }

    public static void loadFont(String name) throws IOException, FontFormatException {
        System.out.println("Loading " + name + "...");
        InputStream is = getResourceAsStream(name);
        Font font = new Font("Arial", Font.PLAIN, 1);
        font = Font.createFont(Font.TRUETYPE_FONT, is);
        fonts.put(name, font);
    }

    private static URL getResource(String name) {
        return Resources.class.getResource(name);
    }

    private static InputStream getResourceAsStream(String name) {
        return Resources.class.getResourceAsStream(name);
    }

}
