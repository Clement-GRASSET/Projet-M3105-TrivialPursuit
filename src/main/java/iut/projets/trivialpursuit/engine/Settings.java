package iut.projets.trivialpursuit.engine;

import java.io.*;
import java.util.Properties;

public class Settings {

    private static Properties properties;

    private static final String path = Game.getDirectory() + "/settings.txt";

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

    private static String getValue(String key, String defaultValue) {
        if (!properties.containsKey(key))
            properties.put(key, defaultValue);
        return(String) properties.get(key);
    }

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

    public static void setMaxFPS(int fps) {
        properties.put("MaxFPS", String.valueOf(fps));
        Game.getGameLoop().setMaxFPS(fps);
    }

    public static void setRenderScale(double scale) {
        properties.put("RenderScale", String.valueOf(scale));
        SceneManager.setRenderScale(scale);
    }

    public static void setShowDebug(boolean showDebug) {
        properties.put("ShowDebug", String.valueOf(showDebug));
        Game.getGameLoop().setDebug(showDebug);
    }

    public static void setFullScreen(boolean fullScreen) {
        properties.put("FullScreen", String.valueOf(fullScreen));
        Game.getWindow().setFullScreen(fullScreen);
    }

}
