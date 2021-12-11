package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.Task;

/**
 * Tâche qui se termine après un délai.
 */
public class Delay extends Task {

    private final double duration;

    /**
     * Construit un délai.
     * @param duration Durée du délai.
     */
    public Delay(double duration) {
        super();
        this.duration = duration;
    }

    @Override
    public void tick(double frameTime) {
        super.tick(frameTime);
        if (getTime() > duration)
            stop();
        sendUpdate();
    }

    /**
     * Renvoie la durée du délai.
     * @return La durée du délai.
     */
    public double getDuration() {
        return duration;
    }

}
