package fr.trivialpursuit.game.actors;

import fr.trivialpursuit.engine.graphics.Actor;
import fr.trivialpursuit.engine.graphics.Scene;
import fr.trivialpursuit.game.assets.materials.WhiteMaterial;

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
