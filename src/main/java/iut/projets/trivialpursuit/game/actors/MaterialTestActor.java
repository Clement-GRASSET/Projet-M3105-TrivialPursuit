package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.graphics.Actor;
import iut.projets.trivialpursuit.game.assets.materials.TilesMaterial;

public class MaterialTestActor extends Actor {

    public MaterialTestActor() {
        super();
        setMaterial(new TilesMaterial());
    }

    @Override
    public void update(double frameTime) {

    }
}
