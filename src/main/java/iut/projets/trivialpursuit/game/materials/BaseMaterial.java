package iut.projets.trivialpursuit.game.materials;

import iut.projets.trivialpursuit.engine.graphics.Material;

import java.awt.*;

public class BaseMaterial extends Material {

    public BaseMaterial(Image color, Image normals) {
        super();
        this.color = color;
        this.normals = normals;
    }

    public void setColor(Image color) {
        this.color = color;
    }

    public void setNormals(Image normals) {
        this.normals = normals;
    }

}
