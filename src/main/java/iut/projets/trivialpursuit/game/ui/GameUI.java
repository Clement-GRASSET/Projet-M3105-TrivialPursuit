package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.userinterface.UIBoxContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIImage;
import iut.projets.trivialpursuit.engine.userinterface.UIScreenContainer;
import iut.projets.trivialpursuit.engine.userinterface.UIText;
import iut.projets.trivialpursuit.game.Player;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameUI extends UIScreenContainer {

    private class PlayerStatsUI extends UIBoxContainer {

        Map<TrivialPursuitColor, UIImage> slices;

        PlayerStatsUI(Player player) {
            slices = new HashMap<>();

            setAnchor(Anchor.BOTTOM_LEFT);
            setAlignment(new Vector2D(1, -1));
            setSize(new Vector2D(30, 7.5));

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
            text.setFontSize(3);
            addElement(text);

            UIImage pawn = new UIImage();
            pawn.setAnchor(Anchor.CENTER_RIGHT);
            pawn.setAlignment(new Vector2D(-1, 0));
            pawn.setSize(new Vector2D(6,6));
            pawn.setPosition(new Vector2D(-1, 0));
            pawn.setImage(Resources.getImage("/images/pawn.png"));
            addElement(pawn);

            addSlice(TrivialPursuitColor.BLUE);
            addSlice(TrivialPursuitColor.GREEN);
            addSlice(TrivialPursuitColor.ORANGE);
            addSlice(TrivialPursuitColor.PINK);
            addSlice(TrivialPursuitColor.PURPLE);
            addSlice(TrivialPursuitColor.YELLOW);
        }

        public void addSlice(TrivialPursuitColor color) {
            if (slices.containsKey(color)) return;

            UIImage slice = new UIImage();
            slice.setAnchor(Anchor.CENTER_RIGHT);
            slice.setAlignment(new Vector2D(-1, 0));
            slice.setSize(new Vector2D(6,6));
            slice.setPosition(new Vector2D(-1, 0));
            String path = "";
            switch (color) {
                case BLUE: path = "/images/slice_blue.png"; break;
                case GREEN: path = "/images/slice_green.png"; break;
                case ORANGE: path = "/images/slice_orange.png"; break;
                case PINK: path = "/images/slice_pink.png"; break;
                case PURPLE: path = "/images/slice_purple.png"; break;
                case YELLOW: path = "/images/slice_yellow.png"; break;
            }
            slice.setImage(Resources.getImage(path));
            slices.put(color, slice);
            addElement(slice);
        }
    }

    private List<Player> players;

    public GameUI(List<Player> players) {
        this.players = players;

        for (int i = 0; i < players.size(); i++) {
            PlayerStatsUI statsUI = new PlayerStatsUI(players.get(i));
            statsUI.setPosition(new Vector2D(0, -5 - (players.size()-1)*7.5 + i*7.5));
            addElement(statsUI);
        }
    }

}
