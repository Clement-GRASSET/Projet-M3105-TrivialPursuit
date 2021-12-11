package iut.projets.trivialpursuit.engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe pour faciliter le chargement et l'obtention des ressources
 */
public class Resources {

    private static final Map<String, BufferedImage> images = new HashMap<>();
    private static final Map<String, Font> fonts = new HashMap<>();

    /**
     * Renvoie une image du dossier resources.
     * Charge l'image depuis le disque si elle n'est pas présente en mémoire.
     * @param name Chemin d'accès de l'image dans le dossier resources.
     * @return Référence à l'image.
     */
    public static Image getImage(String name) {
        if (images.containsKey(name)) {
            return images.get(name);
        } else {
            loadImage(name);
            return images.get(name);
        }
    }

    /**
     * Renvoie une police d'écriture du dossier resources.
     * Charge la police depuis le disque si elle n'est pas présente en mémoire.
     * @param name Chemin d'accès de la police dans le dossier resources.
     * @return Référence à la police.
     */
    public static Font getFont(String name) {
        if (fonts.containsKey(name)) {
            return fonts.get(name);
        } else {
            loadFont(name);
            return fonts.get(name);
        }
    }

    /**
     * Précharge une image depuis le disque
     * @param name Chemin d'accès de l'image dans le dossier resources.
     */
    public static void loadImage(String name) {
        try {
            System.out.println("Loading " + name + "...");
            images.put( name, ImageIO.read( getResource(name) ) );
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Renvoie un son du dossier resources.
     * @param name Chemin d'accès du son dans le dossier resources.
     * @return Le son.
     */
    public static Sound getSound(String name) {
        try {
            return new Sound(getResourceAsStream(name));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    /**
     * Précharge une police d'écriture depuis le disque
     * @param name Chemin d'accès de la police dans le dossier resources.
     */
    public static void loadFont(String name) {
        try {
            System.out.println("Loading " + name + "...");
            InputStream is = getResourceAsStream(name);
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            fonts.put(name, font);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Obtient une ressource
     * @param name Chemin d'accès
     * @return La ressource
     */
    private static URL getResource(String name) {
        return Resources.class.getResource(name);
    }

    /**
     * Obtient une ressource
     * @param name Chemin d'accès
     * @return La ressource
     */
    private static InputStream getResourceAsStream(String name) {
        return Resources.class.getResourceAsStream(name);
    }

}
