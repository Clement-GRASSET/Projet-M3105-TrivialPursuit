package iut.projets.trivialpursuit.game.actors;

import iut.projets.trivialpursuit.engine.graphics.Actor;
import iut.projets.trivialpursuit.engine.graphics.Vector2D;
import iut.projets.trivialpursuit.game.assets.materials.TextMaterial;

import java.util.Vector;

public class FPSCounter extends Actor {

    Vector<Double> lastFrameTimes;

    public FPSCounter() {
        super();
        setMaterial(new TextMaterial(""));
        setScale(new Vector2D(20,5));

        lastFrameTimes = new Vector<Double>();
    }

    @Override
    public void update(double frameTime) {

        lastFrameTimes.addElement(frameTime);
        double total = 0;
        for (double time : lastFrameTimes) {
            total += time;
        }
        double average = total/lastFrameTimes.size();
        if (lastFrameTimes.size() > 100)
            lastFrameTimes.remove(0);

        String FPS = String.format("%,.0f", 1.0/average) + " FPS";
        TextMaterial material = (TextMaterial) getMaterial();
        material.SetText(FPS);
    }
}
