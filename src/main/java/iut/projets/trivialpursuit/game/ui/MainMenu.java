package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.audio.Sound;
import iut.projets.trivialpursuit.engine.game.Delay;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.*;
import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.game.GameInfo;
import iut.projets.trivialpursuit.game.Player;
import iut.projets.trivialpursuit.game.Profile;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;
import iut.projets.trivialpursuit.game.scenes.GameScene;

public class MainMenu extends UIScreenContainer {

    private class PlayerInfo extends UIBoxContainer {
        PlayerInfo() {
            super();
            setAnchor(Anchor.CENTER_CENTER);

            UIImage image = new UIImage();
            setSize(new Vector2D(25,40));
            image.setSize(getSize());
            image.setAnchor(Anchor.TOP_LEFT);
            image.setAlignment(new Vector2D(1,1));
            addElement(image);

            UIButton button = new UIButton("Test");
            button.setSize(new Vector2D(20, 8));
            addElement(button);
        }
    }

    private class TitleScreen extends UIScreenContainer {
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

    private class OptionsMenu extends UIScreenContainer {
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

    private class ProfileSelectionScreen extends UIScreenContainer {
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

            PlayerInfo playerInfo1, playerInfo2, playerInfo3;
            playerInfo1 = new PlayerInfo();
            playerInfo1.setPosition(new Vector2D(-30, 0));
            playerInfo2 = new PlayerInfo();
            playerInfo3 = new PlayerInfo();
            playerInfo3.setPosition(new Vector2D(30, 0));
            addElement(playerInfo1);
            addElement(playerInfo2);
            addElement(playerInfo3);

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
                    Delay delay = new Delay(2);
                    delay.onFinish(() -> {
                        GameScene gameScene = new GameScene(gameInfo);
                        Engine.setActiveScene(gameScene);
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
    private final long music_start_time;
    private final double music_bpm;

    private GameInfo gameInfo;

    public MainMenu() {
        super();

        initializeGameInfo();

        music_bpm = 132.0;

        menuMusic = new Sound(Resources.getInputStream("/sounds/musics/main_menu.wav"));
        menuMusic.setLoop(true, (60.0/music_bpm)*44);
        menuMusic.setVolume(0.5f);
        menuMusic.play();
        music_start_time = System.nanoTime();

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
        double time = (System.nanoTime() - music_start_time)/1000000000.0;
        double period = 60.0/music_bpm;
        double time_offset = 0;
        double scale = ( Math.cos( (time+time_offset) * 2 * Math.PI/period ) + 1 )/2;
        logo.setSize( Math.pow(scale, 10)*2 + 50);
    }

    private void setActiveMenu(UIContainer menu) {
        if (activeMenu != null)
            removeElement(activeMenu);
        activeMenu = menu;
        addElement(activeMenu);
    }

    private void initializeGameInfo() {
        gameInfo = new GameInfo();
        Profile profile;
        Player player;

        // Player 1
        profile = new Profile();
        profile.setName("Joueur 1");
        profile.setCategory(TrivialPursuitColor.BLUE, "", "");
        profile.setCategory(TrivialPursuitColor.GREEN, "", "");
        profile.setCategory(TrivialPursuitColor.ORANGE, "", "");
        profile.setCategory(TrivialPursuitColor.PINK, "", "");
        profile.setCategory(TrivialPursuitColor.PURPLE, "", "");
        profile.setCategory(TrivialPursuitColor.YELLOW, "", "");
        player = new Player(profile, TrivialPursuitColor.PINK);
        gameInfo.addPlayer(player);

        // Player 2
        profile = new Profile();
        profile.setName("Joueur 2");
        profile.setCategory(TrivialPursuitColor.BLUE, "", "");
        profile.setCategory(TrivialPursuitColor.GREEN, "", "");
        profile.setCategory(TrivialPursuitColor.ORANGE, "", "");
        profile.setCategory(TrivialPursuitColor.PINK, "", "");
        profile.setCategory(TrivialPursuitColor.PURPLE, "", "");
        profile.setCategory(TrivialPursuitColor.YELLOW, "", "");
        player = new Player(profile, TrivialPursuitColor.YELLOW);
        gameInfo.addPlayer(player);
    }

}
