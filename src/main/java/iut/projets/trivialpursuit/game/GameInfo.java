package iut.projets.trivialpursuit.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Informations d'une partie.
 * Contient la liste des joueurs.
 */
public class GameInfo {

    private final List<Player> players;

    public GameInfo() {
        players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
