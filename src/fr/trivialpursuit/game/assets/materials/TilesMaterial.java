package fr.trivialpursuit.game.assets.materials;

import fr.trivialpursuit.engine.graphics.Material;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TilesMaterial extends Material {

    public TilesMaterial() {
        super();
        try {
            color = ImageIO.read(new File("src/fr/trivialpursuit/game/assets/textures/tiles/tiles_color.jpg"));
            normals = ImageIO.read(new File("src/fr/trivialpursuit/game/assets/textures/tiles/tiles_normals.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
