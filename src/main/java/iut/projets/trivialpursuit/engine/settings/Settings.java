package iut.projets.trivialpursuit.engine.settings;

import iut.projets.trivialpursuit.engine.Engine;

public class Settings {

    public static void setMaxFPS(int fps) {
        Engine.getGameLoop().setMaxFPS(fps);
    }

}
