package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.core.Actor;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;

/**
 * Représente une case.
 * Utilisé uniquement pour les informations telles que les cases adjacentes, la position ou le type de case.
 */
public class Case extends Actor {

    /**
     * Représente le type de la case.
     * SPECIAL signifie que la case permet de gagner un point.
     */
    public enum CaseType {
        BLUE,
        GREEN,
        ORANGE,
        PINK,
        PURPLE,
        YELLOW,
        SPECIAL_BLUE,
        SPECIAL_GREEN,
        SPECIAL_ORANGE,
        SPECIAL_PINK,
        SPECIAL_PURPLE,
        SPECIAL_YELLOW,
        MULTI,
        ROLL_AGAIN,
    }

    private Case [] linkedCases;
    private CaseType type;

    /**
     * Construit une case.
     */
    public Case() {
        linkedCases = new Case[] {};
        type = CaseType.MULTI;

        setAllowRender(false);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(double frameTime) {

    }

    /**
     * Renvoie toutes les cases adjacentes.
     * @return Toutes les cases adjacentes.
     */
    public Case[] getLinkedCases() {
        return linkedCases;
    }

    /**
     * Définit toutes les cases adjacentes.
     * @param linkedCases Cases adjacentes.
     */
    public void setLinkedCases(Case[] linkedCases) {
        this.linkedCases = linkedCases;
    }

    /**
     * Définit le type de la case.
     * @param type Type de case.
     */
    public void setType(CaseType type) {
        this.type = type;
    }

    /**
     * Renvoie le type de la case.
     * @return Type de la case.
     */
    public CaseType getType() {
        return type;
    }

    /**
     * Renvoie la couleur de la case.
     * @return Couleur de la case.
     */
    public TrivialPursuitColor getColor() {
        switch (type) {
            case BLUE:
            case SPECIAL_BLUE: return TrivialPursuitColor.BLUE;
            case GREEN:
            case SPECIAL_GREEN: return TrivialPursuitColor.GREEN;
            case ORANGE:
            case SPECIAL_ORANGE: return TrivialPursuitColor.ORANGE;
            case PINK:
            case SPECIAL_PINK: return TrivialPursuitColor.PINK;
            case PURPLE:
            case SPECIAL_PURPLE: return TrivialPursuitColor.PURPLE;
            case YELLOW:
            case SPECIAL_YELLOW: return TrivialPursuitColor.YELLOW;
            default: return null;
        }
    }

    /**
     * Renvoie vrai si la case permet de gagner un point.
     * @return Vrai si la case permet de gagner un point.
     */
    public boolean isSpecial() {
        return type == CaseType.SPECIAL_BLUE ||
                type == CaseType.SPECIAL_GREEN ||
                type == CaseType.SPECIAL_ORANGE ||
                type == CaseType.SPECIAL_PINK ||
                type == CaseType.SPECIAL_PURPLE ||
                type == CaseType.SPECIAL_YELLOW;
    }
}
