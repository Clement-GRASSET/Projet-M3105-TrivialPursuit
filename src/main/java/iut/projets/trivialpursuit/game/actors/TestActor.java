package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.graphics.Actor;
import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.game.assets.materials.WhiteMaterial;

public class TestActor extends Actor {

    public TestActor() {
        super();
        setMaterial(new WhiteMaterial());
        setScale(new Vector2D(3, 3));
    }

    @Override
    public void update(double frameTime) {
        getPosition().setX(getPosition().getX() + 15*frameTime);
        setRotation(Rotation.rad(getRotation().getRad() + frameTime*5));
        if (getPosition().getX() > 50.0)
            getScene().removeActor(this);
    }
}
