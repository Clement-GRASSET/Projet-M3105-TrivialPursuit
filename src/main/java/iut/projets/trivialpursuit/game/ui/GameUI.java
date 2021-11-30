package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIBoxContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.engine.userinterface.UIScreenContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIText;
import iut.projets.trivialpursuit.game.Player;

import java.util.List;

public class GameUI extends UIScreenContainer {

    private class PlayerStatsUI extends UIBoxContainer {
        PlayerStatsUI(Player player) {
            setAnchor(Anchor.BOTTOM_LEFT);
            setAlignment(new Vector2D(1, -1));
            setSize(new Vector2D(40, 10));

            UIImage background = new UIImage();
            background.setAnchor(Anchor.CENTER_CENTER);
            background.setSize(getSize());
            String backgroundPath = "/images/player_info_blue.png";
            switch (player.getPawnColor()) {
                case BLUE: backgroundPath = "/images/player_info_blue.png"; break;
                case GREEN: backgroundPath = "/images/player_info_green.png"; break;
                case ORANGE: backgroundPath = "/images/player_info_orange.png"; break;
                case PINK: backgroundPath = "/images/player_info_pink.png"; break;
                case PURPLE: backgroundPath = "/images/player_info_purple.png"; break;
                case YELLOW: backgroundPath = "/images/player_info_yellow.png"; break;
            }
            background.setImage(Resources.getImage(backgroundPath));
            addElement(background);

            UIText text = new UIText();
            text.setText(player.getProfile().getName());
            text.setSize(getSize());
            text.setAnchor(Anchor.CENTER_CENTER);
            text.setAlignment(new Vector2D(0,0));
            text.setPosition(new Vector2D(2, 0));
            text.setTextAlign(Anchor.CENTER_LEFT);
            text.setFontSize(4);
            addElement(text);
        }
    }

    private List<Player> players;

    public GameUI(List<Player> players) {
        this.players = players;

        for (int i = 0; i < players.size(); i++) {
            PlayerStatsUI statsUI = new PlayerStatsUI(players.get(i));
            statsUI.setPosition(new Vector2D(0, -5 - (players.size()-1)*10 + i*10));
            addElement(statsUI);
        }
    }

}
