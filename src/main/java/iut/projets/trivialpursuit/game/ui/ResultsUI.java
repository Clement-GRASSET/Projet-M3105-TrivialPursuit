package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.SceneManager;
import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.basetypes.*;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.game.Player;
import iut.projets.trivialpursuit.game.scenes.MainMenuScene;

import java.awt.*;

public class ResultsUI extends UIScreenContainer {

    private Runnable onBackToMenuClicked;

    public ResultsUI(Player player) {

        onBackToMenuClicked = () -> {};

        UIImage image = new UIImage();
        image.setImageUniform(Color.black);
        image.setSize(new Vector2D(500, 100));
        image.setAnchor(Anchor.CENTER_CENTER);
        addElement(image);

        UIText playerName = new UIText();
        playerName.setAnchor(Anchor.CENTER_CENTER);
        playerName.setTextAlign(Anchor.CENTER_CENTER);
        playerName.setPosition(new Vector2D(0,-15));
        playerName.setFontSize(15);
        playerName.setText(player.getProfile().getName());
        addElement(playerName);

        UIText text = new UIText();
        text.setAnchor(Anchor.CENTER_CENTER);
        text.setTextAlign(Anchor.CENTER_CENTER);
        text.setPosition(new Vector2D(0,-3));
        text.setFontSize(12);
        text.setText("a gagné !");
        addElement(text);

        UIButton button = new UITextButton("Retour au menu");
        button.setAnchor(Anchor.CENTER_CENTER);
        button.setPosition(new Vector2D(0,30));
        button.setSize(new Vector2D(30,10));
        addElement(button);

        button.onClick(() -> {
            onBackToMenuClicked.run();
        });
    }

    public void onBackToMenuClicked(Runnable onBackToMenuClicked) {
        this.onBackToMenuClicked = onBackToMenuClicked;
    }

}
