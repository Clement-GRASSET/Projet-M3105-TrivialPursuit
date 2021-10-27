package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.graphics.Actor;
import iut.projets.trivialpursuit.engine.graphics.Vector2D;
import iut.projets.trivialpursuit.game.assets.materials.TextMaterial;

import java.util.Vector;

public class FPSCounter extends Actor {

    Vector<Long> frameTimes;

    public FPSCounter() {
        super();
        setMaterial(new TextMaterial(""));
        setScale(new Vector2D(20,5));

        frameTimes = new Vector<>();
    }

    @Override
    public void update(double frameTime) {

        frameTimes.addElement(System.nanoTime());

        while (frameTimes.get(0)+1000000000 < System.nanoTime() )
            frameTimes.remove(0);

        String FPS = frameTimes.size() + " FPS";
        TextMaterial material = (TextMaterial) getMaterial();
        material.SetText(FPS);
    }
}
