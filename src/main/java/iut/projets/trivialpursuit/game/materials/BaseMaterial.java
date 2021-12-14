package iut.projets.trivialpursuit.game.materials;

import iut.projets.trivialpursuit.engine.core.Material;

import java.awt.*;

/**
 * Matériau de base construit avec une image représentant la couleur et une image représentant les normales
 */
public class BaseMaterial extends Material {

    /**
     * Construit un matériau.
     * @param color Couleur du matériau.
     * @param normals Normales du matériau.
     */
    public BaseMaterial(Image color, Image normals) {
        super();
        setColor(color);
        setNormals(normals);
    }

}
