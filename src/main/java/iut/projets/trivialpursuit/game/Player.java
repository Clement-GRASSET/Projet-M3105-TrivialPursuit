package iut.projets.trivialpursuit.game;

/**
 * Représente un joueur définit par un profil, une couleur et un nom.
 */
public class Player {

    private final Profile profile;
    private final TrivialPursuitColor pawnColor;
    private String name;

    /**
     * Construit un joueur.
     * @param profile Profil du joueur.
     * @param pawnColor Couleur du joueur.
     */
    public Player(Profile profile, TrivialPursuitColor pawnColor) {
        this.profile = profile;
        this.pawnColor = pawnColor;
        name = "";
    }

    /**
     * Renvoie le profil du joueur.
     * @return Profil du joueur.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Renvoie la couleur du joueur.
     * @return Couleur du joueur.
     */
    public TrivialPursuitColor getPawnColor() {
        return pawnColor;
    }

    /**
     * Renvoie le nom du joueur.
     * @return Nom du joueur.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du joueur.
     * @param name Nom du joueur.
     */
    public void setName(String name) {
        this.name = name;
    }
}
