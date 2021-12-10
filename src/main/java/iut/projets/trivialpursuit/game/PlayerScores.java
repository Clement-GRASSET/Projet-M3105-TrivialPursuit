package iut.projets.trivialpursuit.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void addAttempt(TrivialPursuitColor color, boolean success) {
        if (success)
            scores.get(color).valid = true;
        else {
            scores.get(color).failedAttempts++;
            if (scores.get(color).failedAttempts > scores.get(maxFail).failedAttempts)
                maxFail = color;
        }
    }

    public boolean isComplete() {
        return scores.get(TrivialPursuitColor.BLUE).valid &&
                scores.get(TrivialPursuitColor.GREEN).valid &&
                scores.get(TrivialPursuitColor.ORANGE).valid &&
                scores.get(TrivialPursuitColor.PINK).valid &&
                scores.get(TrivialPursuitColor.PURPLE).valid &&
                scores.get(TrivialPursuitColor.YELLOW).valid;
    }

    public List<TrivialPursuitColor> getAllSuccess() {
        List<TrivialPursuitColor> success = new ArrayList<>();
        scores.forEach((color, score) -> {
            if (score.valid)
                success.add(color);
        });
        return success;
    }

    public TrivialPursuitColor getMostFailedColor() {
        return maxFail;
    }

}
