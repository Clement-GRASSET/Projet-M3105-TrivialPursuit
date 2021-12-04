package iut.projets.trivialpursuit.engine.basetypes;

import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.core.GameObject;
import iut.projets.trivialpursuit.engine.core.Task;

import java.io.IOException;

public class LoadingTask extends Task {

    private final Thread loadThread;
    private boolean isLoaded;
    private int done;
    private final int total;

    public LoadingTask(String [] images, String [] inputStreams) {
        super();
        isLoaded = false;
        done = 0;
        total = images.length + inputStreams.length;
        loadThread = new Thread(() -> {
            try {
                for (String image : images) {
                    Resources.loadImage(image);
                    done++;
                }
                for (String inputStream : inputStreams) {
                    Resources.loadInputStream(inputStream);
                    done++;
                }
            } catch (IOException e) {
                e.printStackTrace();
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

    public double getProgress() {
        return (double)done / total;
    }
}
