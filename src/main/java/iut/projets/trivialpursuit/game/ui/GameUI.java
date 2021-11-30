package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIBoxContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.engine.userinterface.UIScreenContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIText;
import iut.projets.trivialpursuit.game.Player;

import java.util.List;

public class GameUI extends UIScreenContainer {

    private class PlayerStatsUI extends UIBoxContainer {
        PlayerStatsUI(String playerName) {
            setAnchor(Anchor.BOTTOM_LEFT);
            setAlignment(new Vector2D(1, -1));
            setSize(new Vector2D(40, 15));

            UIImage background = new UIImage();
            background.setAnchor(Anchor.CENTER_CENTER);
            background.setSize(getSize());
            addElement(background);

            UIText text = new UIText();
            text.setText(playerName);
            addElement(text);
        }
    }

    private List<Player> players;

    public GameUI(List<Player> players) {
        this.players = players;

        for (int i = 0; i < players.size(); i++) {
            PlayerStatsUI statsUI = new PlayerStatsUI(players.get(i).getProfile().getName());
            statsUI.setPosition(new Vector2D(0, -(players.size()-1)*15 + i*15));
            addElement(statsUI);
        }
    }

}
