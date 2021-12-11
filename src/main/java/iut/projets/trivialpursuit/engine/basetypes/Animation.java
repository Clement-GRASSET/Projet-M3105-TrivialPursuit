package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.Task;

/**
 * Tâche qui anime une valeur
 */
public class Animation extends Task {

    Keyframe[] keyframes;
    private double value, speed;
    private int index;

    /**
     * Construit une animation.
     * @param keyframes Liste des valeurs de prises dans l'animation associées à un temps.
     */
    public Animation(Keyframe[] keyframes) {
        super();
        this.keyframes = keyframes;
        value = (keyframes.length > 0) ? keyframes[0].value : 0;
        index = 0;
        speed = 0;
    }

    @Override
    public void tick(double frameTime) {
        super.tick(frameTime);
        if (getTime() > keyframes[index].time) {
            value = keyframes[index].value;
            if (index+1 < keyframes.length) {
                index += 1;
                double timeLength = Math.abs(keyframes[index].time - getTime());
                double valueLength = keyframes[index].value - value;
                speed = valueLength/timeLength;
            }
            else
                stop();
        } else {
            value += frameTime*speed;
        }
        sendUpdate();
    }

    /**
     * Renvoie la valeur animée.
     * @return La valeur animée.
     */
    public double getValue() {
        return value;
    }
}
