package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.audio.Sound;
import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.game.assets.ui.MainMenu;

import java.io.InputStream;

public class MainMenuScene extends Scene {

    public MainMenuScene() {
        super();

        InputStream inputStream = MainMenuScene.class.getResourceAsStream("/sounds/musics/main_menu.wav");
        Sound sound = new Sound(inputStream);
        sound.setLoop(true);
        Engine.getAudioManager().playMusic(sound);
        //Engine.getAudioManager().playMusic("origamiKingBB.wav", true, 2.18181818);
        //Engine.getAudioManager().playMusic("origamiKingBBT.wav", true, 2.18181818);

        MainMenu mainMenuUI = new MainMenu();
        Engine.getUserInterface().addElement(mainMenuUI);
    }
}
