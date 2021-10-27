package iut.projets.trivialpursuit.game.assets.materials;

import iut.projets.trivialpursuit.engine.graphics.Material;

import javax.imageio.ImageIO;

public class TilesMaterial extends Material {

    public TilesMaterial() {
        super();
        try {
            color = loadImage("tiles/tiles_color.jpg");
            normals = loadImage("tiles/tiles_normals.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
