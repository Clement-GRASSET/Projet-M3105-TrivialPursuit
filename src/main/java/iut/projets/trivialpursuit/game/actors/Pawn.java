package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.basetypes.Animation;
import iut.projets.trivialpursuit.engine.basetypes.Keyframe;
import iut.projets.trivialpursuit.engine.core.Actor;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;
import iut.projets.trivialpursuit.game.materials.BaseMaterial;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Pion jouable.
 */
public class Pawn extends Actor {

    private Case currentCase;
    private Map<TrivialPursuitColor, Slice> slices;

    private static final Image
            blue = Resources.getImage("/textures/pawn/pawn_blue.png"),
            green = Resources.getImage("/textures/pawn/pawn_green.png"),
            orange = Resources.getImage("/textures/pawn/pawn_orange.png"),
            pink = Resources.getImage("/textures/pawn/pawn_pink.png"),
            purple = Resources.getImage("/textures/pawn/pawn_purple.png"),
            yellow = Resources.getImage("/textures/pawn/pawn_yellow.png"),
            normals = Resources.getImage("/textures/pawn/pawn_normals.png");

    /**
     * Construit un pion.
     */
    public Pawn() {
        setMaterial(new BaseMaterial(blue, normals));
        setScale(new Vector2D(8,8));
        slices = new HashMap<>();
    }

    @Override
    public void start() {}

    @Override
    public void update(double frameTime) {
        slices.forEach((color, slice) -> {
            slice.setPosition(getPosition());
        });
    }

    /**
     * Définit la couleur du pion.
     * @param pawnColor Couleur du pion.
     */
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

    /**
     * Déplace le pion vers une position en jouant une animation.
     * @param position Position cible.
     */
    public void moveTo(Vector2D position) {
        Vector2D start = getPosition();
        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0,0),
                new Keyframe(1,0.3),
        });
        animation.onUpdate(() -> {
            double x = Animation.interpolate(start.getX(), position.getX(), animation.getValue());
            double y = Animation.interpolate(start.getY(), position.getY(), animation.getValue());
            setPosition(new Vector2D(x,y));
        });
        animation.start(this);
    }

    /**
     * Renvoie la case sur laquelle le pion se trouve.
     * @return Case sur laquelle le pion se trouve.
     */
    public Case getCurrentCase() {
        return currentCase;
    }

    /**
     * Définit la case sur laquelle le pion se trouve.
     * @param currentCase Case sur laquelle le pion se trouve.
     */
    public void setCurrentCase(Case currentCase) {
        this.currentCase = currentCase;
    }

    /**
     * Ajoute une part au pion.
     * @param color Couleur de la part.
     */
    public void addSlice(TrivialPursuitColor color) {
        if (slices.containsKey(color)) return;
        Slice slice = (Slice) getScene().addActor(Slice.class);
        slice.setColor(color);
        slice.setScale(getScale());
        slice.setRenderOrder(getRenderOrder());
        slices.put(color, slice);
    }
}
