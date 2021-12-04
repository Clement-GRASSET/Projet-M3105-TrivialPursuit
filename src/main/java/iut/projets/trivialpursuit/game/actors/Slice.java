package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.core.Actor;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;

public class Slice extends Actor {

    public Slice() {
        setRenderOrder(10);
    }

    void setColor(TrivialPursuitColor color) {
        switch (color) {
            case BLUE: {
                getMaterial().setColor(Resources.getImage("/textures/slices/slice_blue.png"));
                getMaterial().setNormals(Resources.getImage("/textures/slices/slice_blue_normals.png"));
                break;
            }
            case GREEN: {
                getMaterial().setColor(Resources.getImage("/textures/slices/slice_green.png"));
                getMaterial().setNormals(Resources.getImage("/textures/slices/slice_green_normals.png"));
                break;
            }
            case ORANGE: {
                getMaterial().setColor(Resources.getImage("/textures/slices/slice_orange.png"));
                getMaterial().setNormals(Resources.getImage("/textures/slices/slice_orange_normals.png"));
                break;
            }
            case PINK: {
                getMaterial().setColor(Resources.getImage("/textures/slices/slice_pink.png"));
                getMaterial().setNormals(Resources.getImage("/textures/slices/slice_pink_normals.png"));
                break;
            }
            case PURPLE: {
                getMaterial().setColor(Resources.getImage("/textures/slices/slice_purple.png"));
                getMaterial().setNormals(Resources.getImage("/textures/slices/slice_purple_normals.png"));
                break;
            }
            case YELLOW: {
                getMaterial().setColor(Resources.getImage("/textures/slices/slice_yellow.png"));
                getMaterial().setNormals(Resources.getImage("/textures/slices/slice_yellow_normals.png"));
                break;
            }
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void update(double frameTime) {

    }

}
