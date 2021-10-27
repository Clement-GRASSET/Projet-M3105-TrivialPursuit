package fr.trivialpursuit.game.actors;

import fr.trivialpursuit.engine.graphics.Actor;
import fr.trivialpursuit.game.assets.materials.TilesMaterial;

public class MaterialTestActor extends Actor {

    public MaterialTestActor() {
        super();
        setMaterial(new TilesMaterial());
    }

    @Override
    public void update(double frameTime) {

    }
}
