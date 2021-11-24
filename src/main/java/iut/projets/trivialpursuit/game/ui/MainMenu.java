package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.audio.Sound;
import iut.projets.trivialpursuit.engine.game.Delay;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIButton;
import iut.projets.trivialpursuit.engine.userinterface.UIContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.game.scenes.GameScene;

public class MainMenu extends UIContainer {

    private class TitleScreen extends UIContainer {
        TitleScreen() {
            UIButton playButton = new UIButton("Jouer");
            playButton.setRenderOrder(1);
            playButton.setAnchor(Anchor.CENTER_CENTER);
            playButton.setPosition(new Vector2D(0, 15));
            playButton.setSize(new Vector2D(30, 8));
            playButton.onClick( () -> {
                setActiveMenu(profileSelectionScreen);
            } );
            addElement(playButton);

            UIButton optionButton = new UIButton("Options");
            optionButton.setRenderOrder(1);
            optionButton.setAnchor(Anchor.CENTER_CENTER);
            optionButton.setPosition(new Vector2D(0, 25));
            optionButton.setSize(new Vector2D(30, 8));
            optionButton.onClick( () -> {
                setActiveMenu(optionsMenu);
            });
            addElement(optionButton);

            UIButton quitButton = new UIButton("Quitter");
            quitButton.setRenderOrder(1);
            quitButton.setAnchor(Anchor.CENTER_CENTER);
            quitButton.setPosition(new Vector2D(0, 35));
            quitButton.setSize(new Vector2D(30, 8));
            quitButton.onClick( () ->  {
                menuMusic.stop();
                System.exit(0);
            });
            addElement(quitButton);

            logo = new UIImage();
            logo.setRenderOrder(1);
            logo.setImage(Resources.getImage("/images/trivial-pursuit-logo.png"));
            logo.setSize(50);
            logo.setPosition(new Vector2D(0, -15));
            addElement(logo);
        }
    }

    private class OptionsMenu extends UIContainer {
        OptionsMenu() {
            UIButton backButton = new UIButton("Retour");
            backButton.setAnchor(Anchor.BOTTOM_LEFT);
            backButton.setAlignment(new Vector2D(1, -1));
            backButton.setPosition(new Vector2D(5, -3));
            backButton.setSize(new Vector2D(17, 7));
            backButton.onClick( () -> {
                setActiveMenu(titleScreen);
            });
            addElement(backButton);
        }
    }

    private class ProfileSelectionScreen extends UIContainer {
        ProfileSelectionScreen() {
            UIButton backButton = new UIButton("Retour");
            backButton.setAnchor(Anchor.BOTTOM_LEFT);
            backButton.setAlignment(new Vector2D(1, -1));
            backButton.setPosition(new Vector2D(5, -3));
            backButton.setSize(new Vector2D(17, 7));
            backButton.onClick( () -> {
                setActiveMenu(titleScreen);
            });
            addElement(backButton);

            UIButton startButton = new UIButton("Démarrer");
            startButton.setAnchor(Anchor.BOTTOM_RIGHT);
            startButton.setAlignment(new Vector2D(-1, -1));
            startButton.setPosition(new Vector2D(-5, -3));
            startButton.setSize(new Vector2D(17, 7));
            startButton.onClick( () -> {
                menuMusic.stop();
                GameLoadingScreen gameLoadingScreen = new GameLoadingScreen();
                gameLoadingScreen.setRenderOrder(10);
                gameLoadingScreen.onConstructAnimationFinished(() -> {
                    Delay delay = new Delay(1);
                    delay.onFinish(() -> {
                        Engine.setActiveScene(new GameScene());
                        gameLoadingScreen.remove();
                    });
                    delay.start(gameLoadingScreen);
                });
                Engine.getUserInterface().addElement(gameLoadingScreen);
                Engine.getUserInterface().removeElement(mainMenu);
            });
            addElement(startButton);
        }
    }

    private final MainMenu mainMenu = this; // reference au menu
    private final UIContainer titleScreen, optionsMenu, profileSelectionScreen;
    private UIImage logo;
    private UIContainer activeMenu;
    private final Sound menuMusic;
    private double time;
    private final double music_bpm;

    public MainMenu() {
        super();

        time = 0;
        music_bpm = 132.0;

        menuMusic = new Sound(Resources.getInputStream("/sounds/musics/main_menu.wav"));
        menuMusic.setLoop(true, (60.0/music_bpm)*44);
        menuMusic.play();

        UIImage background = new UIImage();
        background.setImage(Resources.getImage("/images/main-menu-background.png"));
        background.setSize(100);
        background.setPosition(new Vector2D(0, 0));
        addElement(background);

        titleScreen = new TitleScreen();
        optionsMenu = new OptionsMenu();
        profileSelectionScreen = new ProfileSelectionScreen();

        setActiveMenu(titleScreen);
    }

    @Override
    public void update(double frameTime) {
        super.update(frameTime);
        time += frameTime;
        double period = 60.0/music_bpm;
        double scale = -Math.cos( (time+0.4) * 2 * Math.PI/period );
        logo.setSize( Math.pow(scale+0.5, 5)*0.3 + 50);
    }

    private void setActiveMenu(UIContainer menu) {
        if (activeMenu != null)
            removeElement(activeMenu);
        activeMenu = menu;
        addElement(activeMenu);
    }

}
