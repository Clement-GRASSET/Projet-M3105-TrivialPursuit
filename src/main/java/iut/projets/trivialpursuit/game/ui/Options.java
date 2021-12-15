package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.Settings;
import iut.projets.trivialpursuit.engine.basetypes.*;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.util.function.Function;

/**
 * Interface utilisateur permettant de modifier les paramètres du jeu.
 */
public class Options extends UIScreenContainer {

    /**
     * Bloc contenant une option
     */
    private static class Selector extends UIBoxContainer {
        private final Function<String, String> update;
        private final Runnable decrease, increase;
        private String textValue;

        /**
         * Construit un sélecteur.
         * @param name Nom du sélecteur.
         * @param update Fonction permettant de mettre à jour la valeur à afficher.
         * @param decrease Fonction permettant d'augmenter la valeur du paramètre.
         * @param increase Fonction permettant de diminuer la valeur du paramètre.
         * @param fontColor Couleur du texte.
         */
        Selector(String name, Function<String, String> update, Runnable decrease, Runnable increase, Color fontColor) {
            this.update = update;
            this.decrease = decrease;
            this.increase = increase;
            textValue = "Valeur";

            setAnchor(Anchor.CENTER_CENTER);
            setAlignment(new Vector2D(0,0));
            setPosition(new Vector2D(0,0));
            setSize(new Vector2D(80, 7));

            double buttonSize = getSize().getY();

            UIText text = new UIText();
            text.setPosition(new Vector2D(0,0));
            text.setAnchor(Anchor.CENTER_LEFT);
            text.setAlignment(new Vector2D(1, 0));
            text.setTextAlign(Anchor.CENTER_LEFT);
            text.setText(name);
            text.setColor(fontColor);
            text.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
            addElement(text);

            UIText value = new UIText();
            value.setPosition(new Vector2D(-buttonSize,0));
            value.setSize(new Vector2D(30-buttonSize, buttonSize));
            value.setAnchor(Anchor.CENTER_RIGHT);
            value.setAlignment(new Vector2D(-1, 0));
            value.setTextAlign(Anchor.CENTER_CENTER);
            value.setText("Valeur");
            value.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
            value.setColor(fontColor);
            addElement(value);

            UITextButton left = new UITextButton("<");
            left.setAnchor(Anchor.CENTER_RIGHT);
            left.setAlignment(new Vector2D(-1,0));
            left.setPosition(new Vector2D(-30,0));
            left.setSize(new Vector2D(buttonSize, buttonSize));
            left.getTextElement().setFontSize(5);
            left.onClick(() -> {
                decrease.run();
                value.setText(update.apply(textValue));
                Settings.save();
            });
            addElement(left);

            UITextButton right = new UITextButton(">");
            right.setAnchor(Anchor.CENTER_RIGHT);
            right.setAlignment(new Vector2D(-1,0));
            right.setPosition(new Vector2D(0,0));
            right.setSize(new Vector2D(buttonSize, buttonSize));
            right.getTextElement().setFontSize(5);
            right.onClick(() -> {
                increase.run();
                value.setText(update.apply(textValue));
                Settings.save();
            });
            addElement(right);

            value.setText(update.apply(textValue));
        }
    }

    private Runnable onBackClicked;

    /**
     * Construit les options.
     * @param fontColor Couleur du texte.
     */
    public Options(Color fontColor) {
        onBackClicked = () -> {};

        UITextButton backButton = new UITextButton("Retour");
        backButton.setAnchor(Anchor.BOTTOM_LEFT);
        backButton.setAlignment(new Vector2D(1, -1));
        backButton.setPosition(new Vector2D(5, -3));
        backButton.setSize(new Vector2D(17, 7));
        backButton.setPressSound("/sounds/ui/back.wav");
        backButton.onClick( () -> {
            onBackClicked.run();
        });
        addElement(backButton);

        Selector fps, renderScale, fullScreen;

        int [] allowedFPS = {30, 60, 100, 120, 144, 240, 300, 0};

        fps = new Selector("Limite de FPS", (value) -> {
            return (Settings.getMaxFPS() > 0) ? Settings.getMaxFPS() + " FPS" : "Illimité";
        }, () -> {
            for (int i = 1; i < allowedFPS.length; i++) {
                if (allowedFPS[i] == Settings.getMaxFPS()) {
                    Settings.setMaxFPS(allowedFPS[i-1]);
                    return;
                }
            }
            Settings.setMaxFPS(30);
        }, () -> {
            for (int i = 0; i < allowedFPS.length-1; i++) {
                if (allowedFPS[i] == Settings.getMaxFPS()) {
                    Settings.setMaxFPS(allowedFPS[i+1]);
                    return;
                }
            }
            Settings.setMaxFPS(0);
        }, fontColor);
        renderScale = new Selector("Echelle de rendu", (value) -> {
            return String.format("%.0f" ,Settings.getRenderScale()*100) + "%";
        }, () -> {
            Settings.setRenderScale(Math.max(Settings.getRenderScale()-0.1, 0.1));
        }, () -> {
            Settings.setRenderScale(Math.min(Settings.getRenderScale()+0.1, 2));
        }, fontColor);
        fullScreen = new Selector("Plein écran", (value) -> {
            return (Settings.getFullScreen()) ? "Activé" : "Désactivé";
        }, () -> {
            Settings.setFullScreen(false);
        }, () -> {
            Settings.setFullScreen(true);
        }, fontColor);

        fps.setPosition(new Vector2D(0, -15));
        renderScale.setPosition(new Vector2D(0, 0));
        fullScreen.setPosition(new Vector2D(0, 15));
        addElement(fps);
        addElement(renderScale);
        addElement(fullScreen);
    }

    /**
     * Fonction à exécuter quand l'utilisateur appuie sur le bouton retour.
     * @param onBackClicked Fonction à exécuter.
     */
    public void onBackClicked(Runnable onBackClicked) {
        this.onBackClicked = onBackClicked;
    }
}
