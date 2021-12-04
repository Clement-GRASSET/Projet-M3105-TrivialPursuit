package iut.projets.trivialpursuit.engine;

import java.io.*;
import java.util.Properties;

public class Settings {

    private static String path = "settings.txt";

    public static void load() {
        try {
            File file = new File(path);

            if (!file.exists()) {
                OutputStream outputStream = new FileOutputStream(file);
                Properties properties = new Properties();
                properties.store(outputStream, "");
            }

            InputStream inputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(inputStream);

            //int MaxFPS = (int) properties.get("MaxFPS");
            //int RenderScale = (int) properties.get("RenderScale");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void setMaxFPS(int fps) {
        Game.getGameLoop().setMaxFPS(fps);
    }

    public static void setRenderScale(double scale) {
        SceneManager.setRenderScale(scale);
    }

    public static void setShowDebug(boolean showDebug) {
        Game.getGameLoop().setDebug(showDebug);
    }

}
