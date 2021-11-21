package iut.projets.trivialpursuit.game.assets.ui;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIButton;
import iut.projets.trivialpursuit.engine.userinterface.UIContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.game.Resources;
import iut.projets.trivialpursuit.game.scenes.GameScene;

public class MainMenu extends UIContainer {

    private class TitleScreen extends UIContainer {
        private Runnable onPlayClicked, onOptionClicked, onQuitClicked;
        private UIImage logo;

        TitleScreen() {
            onPlayClicked = () -> {};
            onOptionClicked = () -> {};
            onQuitClicked = () -> {};

            UIButton playButton = new UIButton("Jouer");
            playButton.setRenderOrder(1);
            playButton.setAnchor(Anchor.CENTER_CENTER);
            playButton.setPosition(new Vector2D(0, 20));
            playButton.onClick( () -> onPlayClicked.run() );
            addElement(playButton);

            UIButton optionButton = new UIButton("Options");
            optionButton.setRenderOrder(1);
            optionButton.setAnchor(Anchor.CENTER_CENTER);
            optionButton.setPosition(new Vector2D(0, 32));
            optionButton.onClick( () -> onOptionClicked.run() );
            addElement(optionButton);

            UIButton quitButton = new UIButton("Quitter");
            quitButton.setRenderOrder(1);
            quitButton.setAnchor(Anchor.CENTER_CENTER);
            quitButton.setPosition(new Vector2D(0, 44));
            quitButton.onClick( () -> onQuitClicked.run() );
            addElement(quitButton);

            logo = new UIImage();
            logo.setRenderOrder(1);
            logo.setImage(Resources.getImage("/images/trivial-pursuit-logo.png"));
            logo.setSize(50);
            logo.setPosition(new Vector2D(0, -10));
            addElement(logo);
        }

        @Override
        protected void update(double frameTime) {
            super.update(frameTime);
            double period = 60/117.0;
            double scale = -Math.cos( System.currentTimeMillis()/1000.0 * 2*Math.PI/period);
            logo.setSize( Math.pow(scale+0.5, 5)*0.6 + 50);
        }

        public void onPlayClicked(Runnable onPlayClicked) {
            this.onPlayClicked = onPlayClicked;
        }

        public void onOptionClicked(Runnable onOptionClicked) {
            this.onOptionClicked = onOptionClicked;
        }

        public void onQuitClicked(Runnable onQuitClicked) {
            this.onQuitClicked = onQuitClicked;
        }
    }

    private class OptionsMenu extends UIContainer {
        OptionsMenu() {
            UIButton backButton = new UIButton("Retour");
            addElement(backButton);
        }
    }

    public MainMenu() {
        super();

        UIImage background = new UIImage();
        background.setImage(Resources.getImage("/images/main-menu-background.png"));
        background.setSize(100);
        background.setPosition(new Vector2D(0, 0));
        addElement(background);

        TitleScreen titleScreen = new TitleScreen();
        OptionsMenu optionsMenu = new OptionsMenu();

        titleScreen.onPlayClicked( () -> {
            removeElement(titleScreen);
            Engine.getUserInterface().removeElement(this);
            Engine.setActiveScene(new GameScene());
        });

        titleScreen.onOptionClicked( () -> {
            addElement(optionsMenu);
            removeElement(titleScreen);
        });

        titleScreen.onQuitClicked( () -> {
           System.exit(0);
        });

        addElement(titleScreen);
    }

}
