package iut.projets.trivialpursuit.engine.core;

/**
 * Représente une tâche qui s'exécute sur la durée.
 */
public abstract class Task {

    private double time;
    private Runnable onUpdate, onFinish;
    private GameObject objectOwner;

    /**
     * Construit une tâche.
     */
    public Task() {
        time = 0;
        onUpdate = () -> {};
        onFinish = () -> {};
    }

    /**
     * Renvoie le temps depuis lequel la tâche a été démarrée.
     * @return Le temps depuis lequel la tâche a été démarrée.
     */
    public double getTime() {
        return time;
    }

    /**
     * Met à jour la tâche.
     * @param frameTime La durée de la dernière frame.
     */
    public void tick(double frameTime) {
        time += frameTime;
    }

    /**
     * Déclenche l'événement de mise à jour de la tâche.
     */
    protected void sendUpdate() {
        onUpdate.run();
    }

    /**
     * Définit la fonction qui devrait s'exécuter lorsque la tâche est mise à jour.
     * @param onUpdate La fonction qui devrait s'exécuter lorsque la tâche est mise à jour.
     */
    public void onUpdate(Runnable onUpdate) {
        this.onUpdate = onUpdate;
    }

    /**
     * Définit la fonction qui devrait s'exécuter lorsque la tâche est terminée.
     * @param onFinish La fonction qui devrait s'exécuter lorsque la tâche est terminée.
     */
    public void onFinish(Runnable onFinish) {
        this.onFinish = onFinish;
    }

    /**
     * Démarre la tâche
     * @param owner GameObject qui se charge d'exécuter la tâche.
     */
    public void start(GameObject owner) {
        objectOwner = owner;
        owner.startTask(this);
    }

    /**
     * Stoppe la tâche
     */
    public void stop() {
        objectOwner.stopTask(this);
        onFinish.run();
    }
}
