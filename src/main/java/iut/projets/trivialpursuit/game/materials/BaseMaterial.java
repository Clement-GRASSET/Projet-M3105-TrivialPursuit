package iut.projets.trivialpursuit.game.materials;

import iut.projets.trivialpursuit.engine.graphics.Material;

import java.awt.*;

public class BaseMaterial extends Material {

    public BaseMaterial(Image color, Image normals) {
        super();
        setColor(color);
        setNormals(normals);
    }

}
