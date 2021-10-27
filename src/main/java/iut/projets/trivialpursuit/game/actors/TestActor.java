package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.graphics.Actor;
import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.game.assets.materials.WhiteMaterial;

public class TestActor extends Actor {

    public TestActor() {
        super();
        setMaterial(new WhiteMaterial());
    }

    @Override
    public void update(double frameTime) {
        getPosition().setX(getPosition().getX() + 5*frameTime);
        if (getPosition().getX() > 50.0)
            getScene().removeActor(this);
    }
}
