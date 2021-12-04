package iut.projets.trivialpursuit.engine.core;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {

    private final List<Task> taskList = new ArrayList<>();
    private final List<Task> tasksToAdd = new ArrayList<>();
    private final List<Task> tasksToRemove = new ArrayList<>();

    public abstract void start();

    public abstract void update(double frameTime);

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

    void startTask(Task task) {
        tasksToAdd.add(task);
    }

    void stopTask(Task task) {
        tasksToRemove.add(task);
    }

    public double interpolate(double a, double b, double alpha) {
        return (1-alpha)*a + alpha*b;
    }

}
