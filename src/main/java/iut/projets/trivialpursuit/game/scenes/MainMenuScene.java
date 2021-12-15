package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.basetypes.DirectionalLight;
import iut.projets.trivialpursuit.engine.core.Scene;
import iut.projets.trivialpursuit.engine.types.Vector3D;
import iut.projets.trivialpursuit.game.ui.MainMenu;

import java.awt.*;

/**
 * Scène dans laquelle se trouve le menu principal.
 */
public class MainMenuScene extends Scene {

    @Override
    public void start() {
        setBackgroundColor(Color.WHITE);
        DirectionalLight light = new DirectionalLight();
        light.setDirection(new Vector3D(0,0,-1));
        light.setIntensity(1);
        addLight(light);

        MainMenu mainMenuUI = new MainMenu();
        UIManager.addElement(mainMenuUI);
    }
}
