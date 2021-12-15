package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.SceneManager;
import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.basetypes.*;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.game.Player;
import iut.projets.trivialpursuit.game.PlayerScores;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;
import iut.projets.trivialpursuit.game.scenes.MainMenuScene;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interface utilisateur affichée en jeu.
 */
public class GameUI extends UIScreenContainer {

    /**
     * Bloc contenant le nom d'un joueur et ses points gagnés.
     */
    private static class PlayerStatsUI extends UIBoxContainer {

        Map<TrivialPursuitColor, UIImage> slices;

        /**
         * Construit le bloc du joueur.
         * @param player Joueur à représenter.
         */
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
            text.setText(player.getName());
            text.setSize(getSize());
            text.setAnchor(Anchor.CENTER_CENTER);
            text.setAlignment(new Vector2D(0,0));
            text.setPosition(new Vector2D(2, 0));
            text.setTextAlign(Anchor.CENTER_LEFT);
            text.setFont(Resources.getFont("/fonts/theboldfont.ttf"));
            text.setFontSize(3);
            addElement(text);

            UIImage pawn = new UIImage();
            pawn.setAnchor(Anchor.CENTER_RIGHT);
            pawn.setAlignment(new Vector2D(-1, 0));
            pawn.setSize(new Vector2D(6,6));
            pawn.setPosition(new Vector2D(-1, 0));
            pawn.setImage(Resources.getImage("/images/pawn.png"));
            addElement(pawn);
        }

        /**
         * Ajoute une part aux points gagnés.
         * @param color Couleur de la part.
         */
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

    private final List<Player> players;
    private final Map<Player, PlayerScores> scores;
    private final Map<Player, PlayerStatsUI> playerStatsUI;

    /**
     * Construit l'interface utilisateur.
     * @param players Liste des joueurs.
     * @param scores Scores des joueurs.
     */
    public GameUI(List<Player> players, Map<Player, PlayerScores> scores) {
        this.players = players;
        this.scores = scores;
        playerStatsUI = new HashMap<>();

        for (int i = 0; i < players.size(); i++) {
            PlayerStatsUI statsUI = new PlayerStatsUI(players.get(i));
            statsUI.setPosition(new Vector2D(0, -5 - (players.size()-1)*7.5 + i*7.5));
            playerStatsUI.put(players.get(i), statsUI);
            addElement(statsUI);
        }
    }

    /**
     * Met à jour les scores.
     */
    public void updateScores() {
        scores.forEach((player, scores) -> {
            for (TrivialPursuitColor color : scores.getAllSuccess()) {
                playerStatsUI.get(player).addSlice(color);
            }
        });
    }

}
