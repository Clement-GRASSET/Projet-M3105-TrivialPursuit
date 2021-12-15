package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.basetypes.UIImage;
import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.basetypes.UIScreenContainer;
import iut.projets.trivialpursuit.engine.basetypes.UIText;
import iut.projets.trivialpursuit.engine.types.Vector2D;

/**
 * Écran de démarrage du jeu.
 */
public class SplashScreen extends UIScreenContainer {

    private final UIText progressText;

    /**
     * Construit l'écran de démarrage.
     */
    public SplashScreen() {
        super();
        UIImage logo = new UIImage();
        logo.setImage(Resources.getImage("/images/iut-logo.png"));
        logo.setSize(20);
        addElement(logo);

        LoadingIcon loadingIcon = new LoadingIcon();
        addElement(loadingIcon);

        progressText = new UIText();
        progressText.setAnchor(Anchor.BOTTOM_RIGHT);
        progressText.setTextAlign(Anchor.CENTER_RIGHT);
        progressText.setAlignment(new Vector2D(-1, -1));
        progressText.setPosition(new Vector2D(-17,-8));
        progressText.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
        progressText.setFontSize(3.5);
        addElement(progressText);

        setProgress(0);
    }

    /**
     * Met à jour la progression du chargement.
     * @param progress Progression du chargement [0, 1].
     */
    public void setProgress(double progress) {
        progressText.setText(String.format("%,.0f", progress*100) + "%");
    }

}
