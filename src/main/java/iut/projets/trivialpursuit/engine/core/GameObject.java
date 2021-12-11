package iut.projets.trivialpursuit.engine.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe parente des acteurs et des éléments de l'interface utilisateur.
 */
public abstract class GameObject {

    private final List<Task> taskList = new ArrayList<>();
    private final List<Task> tasksToAdd = new ArrayList<>();
    private final List<Task> tasksToRemove = new ArrayList<>();

    /**
     * Fonction appelée lors de l'ajout de l'objet dans le jeu.
     */
    public abstract void start();

    /**
     * Fonction appelée à chaque frame.
     * @param frameTime La durée de la dernière frame.
     */
    public abstract void update(double frameTime);

    /**
     * Fonction appelée à chaque frame qui actualise toutes les tâches en cours et qui se charge d'appeler update.
     * @param frameTime La durée de la dernière frame.
     */
    public void tick(double frameTime) {
        for (Task task : taskList) {
            task.tick(frameTime);
        }
        for (Task task : tasksToAdd) {
            taskList.add(task);
        }
        for (Task task : tasksToRemove) {
            taskList.remove(task);
        }
        tasksToAdd.clear();
        tasksToRemove.clear();
        update(frameTime);
    }

    /**
     * Démarre une tâche.
     * @param task La tâche à démarrer.
     */
    void startTask(Task task) {
        tasksToAdd.add(task);
    }

    /**
     * Arrête une tâche
     * @param task La tâche à arrêter.
     */
    void stopTask(Task task) {
        tasksToRemove.add(task);
    }

    /**
     * Renvoie une moyenne pondérée entre deux valeurs.
     * @param a Valeur a.
     * @param b Valeur b.
     * @param alpha Poids de la valeur b (0 = 0%, 1 = 100%)
     * @return L'interpolation entre les deux valeurs.
     */
    public double interpolate(double a, double b, double alpha) {
        return (1-alpha)*a + alpha*b;
    }

}
