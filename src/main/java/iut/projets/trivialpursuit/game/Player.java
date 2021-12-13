package iut.projets.trivialpursuit.game;

public class Player {

    private final Profile profile;
    private final TrivialPursuitColor pawnColor;
    private String name;

    public Player(Profile profile, TrivialPursuitColor pawnColor) {
        this.profile = profile;
        this.pawnColor = pawnColor;
        name = "";
    }

    public Profile getProfile() {
        return profile;
    }

    public TrivialPursuitColor getPawnColor() {
        return pawnColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
