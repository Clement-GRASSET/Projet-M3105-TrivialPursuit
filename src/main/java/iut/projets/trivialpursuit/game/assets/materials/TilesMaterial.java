package iut.projets.trivialpursuit.game.assets.materials;

import iut.projets.trivialpursuit.engine.graphics.Material;
import iut.projets.trivialpursuit.game.Resources;

public class TilesMaterial extends Material {

    public TilesMaterial() {
        super();
        try {
            color = Resources.getImage("/textures/tiles/tiles_color.jpg");
            normals = Resources.getImage("/textures/tiles/tiles_normals.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
