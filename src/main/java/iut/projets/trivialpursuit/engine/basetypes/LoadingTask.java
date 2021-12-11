package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.core.GameObject;
import iut.projets.trivialpursuit.engine.core.Task;

/**
 * Tâche qui met en mémoire des ressources.
 */
public class LoadingTask extends Task {

    private final Thread loadThread;
    private boolean isLoaded;
    private int done;
    private final int total;

    /**
     * Construit une tâche qui met en mémoire des ressources.
     * @param images Images à mettre en mémoire.
     * @param fonts Polices d'écriture à mettre en mémoire.
     */
    public LoadingTask(String [] images, String [] fonts) {
        super();
        isLoaded = false;
        done = 0;
        total = images.length + fonts.length;
        loadThread = new Thread(() -> {
            try {
                for (String image : images) {
                    Resources.loadImage(image);
                    done++;
                }
                for (String font : fonts) {
                    Resources.loadFont(font);
                    done++;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            isLoaded = true;
        });
    }

    @Override
    public void start(GameObject owner) {
        super.start(owner);
        loadThread.start();
    }

    @Override
    public void tick(double frameTime) {
        super.tick(frameTime);
        sendUpdate();
        if (isLoaded) {
            stop();
        }
    }

    /**
     * Renvoie la procession de la mise en mémoire (en %).
     * @return La procession de la mise en mémoire (en %).
     */
    public double getProgress() {
        return (double)done / total;
    }
}
