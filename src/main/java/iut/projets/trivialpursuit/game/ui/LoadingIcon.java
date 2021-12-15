package iut.projets.trivialpursuit.game.ui;

import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.basetypes.UIImage;
import iut.projets.trivialpursuit.engine.Resources;

/**
 * Icône de chargement en bas à droite de l'écran.
 */
public class LoadingIcon extends UIImage {

    /**
     * Construit une icône de chargement.
     */
    public LoadingIcon() {
        super();
        setImage(Resources.getImage("/images/loading-icon.png"));
        setAnchor(Anchor.BOTTOM_RIGHT);
        setAlignment(new Vector2D(-1, 0));
        setPosition(new Vector2D(-2, -8));
        setSize(17);
    }

    @Override
    public void update(double frameTime) {
        setRotation(Rotation.rad(getRotation().getRad() + frameTime*3));
    }
}
