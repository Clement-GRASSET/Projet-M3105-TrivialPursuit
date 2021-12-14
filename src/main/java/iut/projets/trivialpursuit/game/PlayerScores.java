package iut.projets.trivialpursuit.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Représente les points gagnés par un joueur.
 */
public class PlayerScores {

    private static class Score {
        boolean valid;
        int failedAttempts;
        Score() {
            valid = false;
            failedAttempts = 0;
        }
    }

    private final Map<TrivialPursuitColor, Score> scores;
    TrivialPursuitColor maxFail;

    /**
     * Construit un PlayerScores.
     */
    public PlayerScores() {
        scores = new HashMap<>();
        scores.put(TrivialPursuitColor.BLUE, new Score());
        scores.put(TrivialPursuitColor.GREEN, new Score());
        scores.put(TrivialPursuitColor.ORANGE, new Score());
        scores.put(TrivialPursuitColor.PINK, new Score());
        scores.put(TrivialPursuitColor.PURPLE, new Score());
        scores.put(TrivialPursuitColor.YELLOW, new Score());
        maxFail = TrivialPursuitColor.BLUE;
    }

    /**
     * Enregistre une tentative de réponse à une question.
     * Si la réponse est juste, le joueur gagne un point.
     * Si la réponse est fausse, le nombre de mauvaises réponses pour cette catégorie augmente.
     * @param color Couleur de la catégorie de la question.
     * @param success Vrai si la réponse a été correcte.
     */
    public void addAttempt(TrivialPursuitColor color, boolean success) {
        if (success)
            scores.get(color).valid = true;
        else {
            scores.get(color).failedAttempts++;
            if (scores.get(color).failedAttempts > scores.get(maxFail).failedAttempts)
                maxFail = color;
        }
    }

    /**
     * Renvoie vrai si le joueur a gagné toutes les couleurs.
     * @return Vrai si le joueur a gagné toutes les couleurs.
     */
    public boolean isComplete() {
        return scores.get(TrivialPursuitColor.BLUE).valid &&
                scores.get(TrivialPursuitColor.GREEN).valid &&
                scores.get(TrivialPursuitColor.ORANGE).valid &&
                scores.get(TrivialPursuitColor.PINK).valid &&
                scores.get(TrivialPursuitColor.PURPLE).valid &&
                scores.get(TrivialPursuitColor.YELLOW).valid;
    }

    /**
     * Renvoie toutes les couleurs gagnées par le joueur.
     * @return Toutes les couleurs gagnées par le joueur.
     */
    public List<TrivialPursuitColor> getAllSuccess() {
        List<TrivialPursuitColor> success = new ArrayList<>();
        scores.forEach((color, score) -> {
            if (score.valid)
                success.add(color);
        });
        return success;
    }

    /**
     * Renvoie la couleur correspondant à la catégorie à laquelle le joueur a échoué le plus de fois.
     * @return Couleur correspondant à la catégorie à laquelle le joueur a échoué le plus de fois.
     */
    public TrivialPursuitColor getMostFailedColor() {
        return maxFail;
    }

}
