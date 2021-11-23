package iut.projets.trivialpursuit.engine.game;

public abstract class Task {

    private double time;
    private Runnable onUpdate, onFinish;
    private GameObject objectOwner;

    Task() {
        time = 0;
        onUpdate = () -> {};
        onFinish = () -> {};
    }

    public double getTime() {
        return time;
    }

    public void tick(double frameTime) {
        time += frameTime;
    }

    protected void sendUpdate() {
        onUpdate.run();
    }

    public void onUpdate(Runnable onUpdate) {
        this.onUpdate = onUpdate;
    }

    public void onFinish(Runnable onFinish) {
        this.onFinish = onFinish;
    }

    public void start(GameObject owner) {
        objectOwner = owner;
        owner.startTask(this);
    }

    public void stop() {
        objectOwner.stopTask(this);
        onFinish.run();
    }
}
