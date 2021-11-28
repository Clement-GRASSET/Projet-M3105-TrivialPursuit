package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.graphics.Actor;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;
import iut.projets.trivialpursuit.game.materials.BaseMaterial;

import java.awt.*;

public class Pawn extends Actor {

    private static final Image
            blue = Resources.getImage("/textures/pawn/pawn_blue.png"),
            green = Resources.getImage("/textures/pawn/pawn_green.png"),
            orange = Resources.getImage("/textures/pawn/pawn_orange.png"),
            pink = Resources.getImage("/textures/pawn/pawn_pink.png"),
            purple = Resources.getImage("/textures/pawn/pawn_purple.png"),
            yellow = Resources.getImage("/textures/pawn/pawn_yellow.png"),
            normals = Resources.getImage("/textures/pawn/pawn_normals.png");

    public Pawn() {
        setMaterial(new BaseMaterial(blue, normals));
        setScale(new Vector2D(8,8));
    }

    @Override
    public void start() {

    }

    @Override
    public void update(double frameTime) {

    }

    public void setColor(TrivialPursuitColor pawnColor) {
        switch (pawnColor) {
            case BLUE:
                getMaterial().setColor(blue);
                break;
            case GREEN:
                getMaterial().setColor(green);
                break;
            case ORANGE:
                getMaterial().setColor(orange);
                break;
            case PINK:
                getMaterial().setColor(pink);
                break;
            case PURPLE:
                getMaterial().setColor(purple);
                break;
            case YELLOW:
                getMaterial().setColor(yellow);
                break;
        }
    }
}
