package iut.projets.trivialpursuit.game;

public class Player {

    private final Profile profile;
    private final TrivialPursuitColor pawnColor;

    public Player(Profile profile, TrivialPursuitColor pawnColor) {
        this.profile = profile;
        this.pawnColor = pawnColor;
    }

    public Profile getProfile() {
        return profile;
    }

    public TrivialPursuitColor getPawnColor() {
        return pawnColor;
    }
}
