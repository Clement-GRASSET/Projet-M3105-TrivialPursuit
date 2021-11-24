package iut.projets.trivialpursuit.game.materials;

import iut.projets.trivialpursuit.engine.graphics.Material;
import iut.projets.trivialpursuit.engine.Resources;

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
