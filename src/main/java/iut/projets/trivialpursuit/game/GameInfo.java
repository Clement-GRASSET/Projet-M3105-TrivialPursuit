package iut.projets.trivialpursuit.game;

import java.util.ArrayList;
import java.util.List;

public class GameInfo {

    private final List<Player> players;

    public GameInfo() {
        players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
