package iut.projets.trivialpursuit.engine;

import java.io.*;
import java.util.Properties;

/**
 * Gère les paramètres du jeu et leur sauvegarde sur le disque.
 */
public class Settings {

    private static Properties properties;

    private static final String path = Game.getDirectory() + "/settings.txt";

    /**
     * Charge et applique les paramètres depuis le disque.
     */
    public static void load() {
        try {
            File file = new File(path);
            if (!file.exists()) {
                properties = new Properties();
            } else {
                InputStream inputStream = new FileInputStream(file);
                properties = new Properties();
                properties.load(inputStream);
            }

            try {
                int MaxFPS = Integer.parseInt(getValue("MaxFPS", "30"));
                setMaxFPS(MaxFPS);
            } catch (Exception e) {
                setMaxFPS(0);
            }

            try {
                double RenderScale = Double.parseDouble(getValue("RenderScale", "1"));
                setRenderScale(RenderScale);
            } catch (Exception e) {
                setRenderScale(1);
            }

            try {
                boolean ShowDebug = Boolean.parseBoolean(getValue("ShowDebug", String.valueOf(false)));
                setShowDebug(ShowDebug);
            } catch (Exception e) {
                setShowDebug(false);
            }

            try {
                boolean FullScreen = Boolean.parseBoolean(getValue("FullScreen", String.valueOf(false)));
                setFullScreen(FullScreen);
            } catch (Exception e) {
                setFullScreen(false);
            }

            save();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Obtient la valeur d'un paramètre stocké sur le disque.
     * Définit le paramètre s'il n'existe pas.
     * @param key La clé du paramètre.
     * @param defaultValue La valeur par défaut à appliquer s'il n'existe pas.
     * @return La valeur du paramètre.
     */
    private static String getValue(String key, String defaultValue) {
        if (!properties.containsKey(key))
            properties.put(key, defaultValue);
        return(String) properties.get(key);
    }

    /**
     * Sauvegarde les paramètres sur le disque
     */
    public static void save() {
        try {
            File file = new File(path);
            OutputStream outputStream = new FileOutputStream(file);
            properties.store(outputStream, "");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Renvoie le nombre d'images par seconde maximum du jeu.
     * @return Le nombre d'images par seconde maximum du jeu.
     */
    public static int getMaxFPS() {
        return Game.getGameLoop().getMaxFPS();
    }

    /**
     * Définit le nombre d'images par seconde maximum du jeu.
     * @param fps Le nombre d'images par seconde maximum du jeu.
     */
    public static void setMaxFPS(int fps) {
        properties.put("MaxFPS", String.valueOf(fps));
        Game.getGameLoop().setMaxFPS(fps);
    }

    /**
     * Renvoie l'échelle de rendu de la scène.
     * @return L'échelle de rendu de la scène.
     */
    public static double getRenderScale() {
        return SceneManager.getRenderScale();
    }

    /**
     * Définit l'échelle de rendu de la scène.
     * @param scale L'échelle de rendu de la scène.
     */
    public static void setRenderScale(double scale) {
        properties.put("RenderScale", String.valueOf(scale));
        SceneManager.setRenderScale(scale);
    }

    /**
     * Définit si les temps d'exécution devraient être affichés à l'écran.
     * @param showDebug Définit si les temps d'exécution devraient être affichés à l'écran.
     */
    public static void setShowDebug(boolean showDebug) {
        properties.put("ShowDebug", String.valueOf(showDebug));
        Game.getGameLoop().setDebug(showDebug);
    }

    /**
     * Renvoie vrai si le jeu est en plein écran.
     * @return Vrai si le jeu est en plein écran.
     */
    public static boolean getFullScreen() {
        return Game.getWindow().isFullScreen();
    }

    /**
     * Définit si le jeu devrait être en plein écran ou non.
     * @param fullScreen Vrai si le jeu est en plein écran.
     */
    public static void setFullScreen(boolean fullScreen) {
        properties.put("FullScreen", String.valueOf(fullScreen));
        Game.getWindow().setFullScreen(fullScreen);
    }

}
