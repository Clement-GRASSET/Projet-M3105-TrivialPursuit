package iut.projets.trivialpursuit.game;

import java.awt.*;

public enum TrivialPursuitColor {
    BLUE,
    GREEN,
    ORANGE,
    PINK,
    PURPLE,
    YELLOW;

    public Color getRGB() {
        switch (this) {
            case BLUE: return new Color(43, 197, 255);
            case GREEN: return new Color(71, 189, 8);
            case ORANGE: return new Color(255, 136, 0);
            case PINK: return new Color(249, 53, 255);
            case PURPLE: return new Color(132, 0, 255);
            case YELLOW: return new Color(255, 255, 0);
        }
        return new Color(255, 255, 255);
    }
}
