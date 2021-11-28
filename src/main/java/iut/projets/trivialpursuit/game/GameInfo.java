package iut.projets.trivialpursuit.game;

import java.util.ArrayList;
import java.util.List;

public class GameInfo {

    private final List<Player> players;

    public GameInfo() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        for (Player p : players) {
            if (player.getPawnColor().equals(p.getPawnColor())) {
                players.remove(p);
            }
        }
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
