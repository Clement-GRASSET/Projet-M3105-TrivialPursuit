package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.core.Task;

public class Delay extends Task {

    private final double duration;

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

    public double getDuration() {
        return duration;
    }

}
