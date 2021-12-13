package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.*;
import iut.projets.trivialpursuit.engine.basetypes.Delay;
import iut.projets.trivialpursuit.engine.core.UIContainer;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.basetypes.*;
import iut.projets.trivialpursuit.game.*;
import iut.projets.trivialpursuit.game.scenes.GameScene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends UIScreenContainer {

    private class TitleScreen extends UIScreenContainer {
        TitleScreen() {
            UITextButton playButton = new UITextButton("Jouer");
            playButton.setRenderOrder(1);
            playButton.setAnchor(Anchor.CENTER_CENTER);
            playButton.setPosition(new Vector2D(-40, 25));
            playButton.setSize(new Vector2D(30, 30));
            playButton.setDefaultImage(Resources.getImage("/images/play_button.png"));
            playButton.setHoverImage(Resources.getImage("/images/play_button_hover.png"));
            playButton.setPressedImage(Resources.getImage("/images/play_button_press.png"));
            playButton.getTextElement().setFont(Resources.getFont("/fonts/theboldfont.ttf"));
            playButton.getTextElement().setFontSize(5);
            playButton.onClick( () -> {
                setActiveMenu(profileSelectionScreen);
            } );
            addElement(playButton);

            UITextButton optionButton = new UITextButton("Options");
            optionButton.setRenderOrder(1);
            optionButton.setAnchor(Anchor.CENTER_CENTER);
            optionButton.setPosition(new Vector2D(0, 25));
            optionButton.setSize(new Vector2D(30, 30));
            optionButton.setDefaultImage(Resources.getImage("/images/options_button.png"));
            optionButton.setHoverImage(Resources.getImage("/images/options_button_hover.png"));
            optionButton.setPressedImage(Resources.getImage("/images/options_button_press.png"));
            optionButton.getTextElement().setFont(Resources.getFont("/fonts/theboldfont.ttf"));
            optionButton.getTextElement().setFontSize(5);
            optionButton.onClick( () -> {
                setActiveMenu(optionsMenu);
            });
            addElement(optionButton);

            UITextButton quitButton = new UITextButton("Quitter");
            quitButton.setRenderOrder(1);
            quitButton.setAnchor(Anchor.CENTER_CENTER);
            quitButton.setPosition(new Vector2D(40, 25));
            quitButton.setSize(new Vector2D(30, 30));
            quitButton.setDefaultImage(Resources.getImage("/images/quit_button.png"));
            quitButton.setHoverImage(Resources.getImage("/images/quit_button_hover.png"));
            quitButton.setPressedImage(Resources.getImage("/images/quit_button_press.png"));
            quitButton.getTextElement().setFont(Resources.getFont("/fonts/theboldfont.ttf"));
            quitButton.getTextElement().setFontSize(5);
            quitButton.setPressSound("/sounds/ui/back.wav");
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

    private class ProfileSelectionScreen extends UIScreenContainer {
        final List<PlayerInfo> playerInfos;
        ProfileSelectionScreen() {
            playerInfos = new ArrayList<>();

            UIText text = new UIText();
            text.setPosition(new Vector2D(0, -35));
            text.setAlignment(new Vector2D(0,0));
            text.setAnchor(Anchor.CENTER_CENTER);
            text.setTextAlign(Anchor.CENTER_CENTER);
            text.setText("Choix des profils");
            text.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
            text.setFontSize(8);
            text.setColor(Color.BLACK);
            addElement(text);

            UITextButton backButton = new UITextButton("Retour");
            backButton.setAnchor(Anchor.BOTTOM_LEFT);
            backButton.setAlignment(new Vector2D(1, -1));
            backButton.setPosition(new Vector2D(5, -3));
            backButton.setSize(new Vector2D(17, 7));
            backButton.setPressSound("/sounds/ui/back.wav");
            backButton.onClick( () -> {
                setActiveMenu(titleScreen);
            });
            addElement(backButton);

            updatePlayers();

            UITextButton profile_button = new UITextButton("Modifier les profils");
            profile_button.setAnchor(Anchor.BOTTOM_CENTER);
            profile_button.setAlignment(new Vector2D(0, -1));
            profile_button.setPosition(new Vector2D(0, -3));
            profile_button.setSize(new Vector2D(35, 7));
            profile_button.onClick( () -> {
                ProfileMenu pmenu = new ProfileMenu(Game.getWindow(), 640, 360, Game.getWindow().getX(), Game.getWindow().getY(), gameInfo);
                updatePlayers();
            });

            addElement(profile_button);
            UITextButton startButton = new UITextButton("Démarrer");
            startButton.setAnchor(Anchor.BOTTOM_RIGHT);
            startButton.setAlignment(new Vector2D(-1, -1));
            startButton.setPosition(new Vector2D(-5, -3));
            startButton.setSize(new Vector2D(17, 7));
            startButton.onClick( () -> {
                Resources.getSound("/sounds/ui/game_start.wav").play();
                menuMusic.stop();
                GameLoadingScreen gameLoadingScreen = new GameLoadingScreen();
                gameLoadingScreen.onConstructAnimationFinished(() -> {
                    Delay delay = new Delay(0.8);
                    delay.onFinish(() -> {
                        GameScene gameScene = new GameScene(gameInfo);
                        SceneManager.setActiveScene(gameScene);
                        gameLoadingScreen.remove();
                    });
                    delay.start(gameLoadingScreen);
                });
                UIManager.addElement(gameLoadingScreen);
                UIManager.removeElement(mainMenu);
            });
            addElement(startButton);
        }

        void updatePlayers() {
            for (PlayerInfo playerInfo : playerInfos) {
                removeElement(playerInfo);
            }
            playerInfos.clear();
            int nbPlayers = gameInfo.getPlayers().size();
            double spacing = 29;
            for (int i = 0; i < nbPlayers; i++) {
                Player player = gameInfo.getPlayers().get(i);
                PlayerInfo playerInfo = new PlayerInfo(player);
                playerInfos.add(playerInfo);
                playerInfo.setPosition(new Vector2D(i*spacing - (nbPlayers-1)*spacing/2, 3));
                addElement(playerInfo);
            }
        }
    }

    private final MainMenu mainMenu = this; // reference au menu
    private final UIContainer titleScreen, profileSelectionScreen;
    private final Options optionsMenu;
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

        menuMusic = Resources.getSound("/sounds/musics/main_menu.wav");
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
        optionsMenu = new Options(Color.BLACK);
        optionsMenu.onBackClicked(() -> {
            setActiveMenu(titleScreen);
        });
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
        logo.setSize( Math.pow(scale, 10)*0.8 + 20);
    }

    private void setActiveMenu(UIContainer menu) {
        if (activeMenu != null)
            removeElement(activeMenu);
        activeMenu = menu;
        addElement(activeMenu);
    }

    private void initializeGameInfo() {
        gameInfo = new GameInfo();
        Player player;

        // Player 1
        player = new Player(ProfilesManager.getDefaultProfile(), TrivialPursuitColor.BLUE);
        gameInfo.getPlayers().add(player);

        // Player 2
        player = new Player(ProfilesManager.getDefaultProfile(), TrivialPursuitColor.PINK);
        gameInfo.getPlayers().add(player);
    }

}
