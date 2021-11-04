package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.engine.userinterface.UIElement;
import iut.projets.trivialpursuit.game.Resources;
import iut.projets.trivialpursuit.game.assets.ui.FPSCounter;
import iut.projets.trivialpursuit.game.assets.ui.LoadingIcon;
import iut.projets.trivialpursuit.game.assets.ui.SplashScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartScene extends Scene {

    private class LoadThread extends Thread {
        private boolean isLoaded;
        private final List<String> images;

        private LoadThread(List<String> images) {
            this.images = images;
            isLoaded = false;
            start();
        }

        @Override
        public void run() {
            super.run();
            try {
                for (String image : images) {
                    Resources.loadImage(image);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            isLoaded = true;
        }

        public boolean isLoadComplete() {
            return isLoaded;
        }
    }

    private final LoadThread loadThread;
    private boolean sceneTransitionStarted;
    private final UIElement splashScreen, loadingIcon;
    private double timeElapsed;

    public StartScene() {
        sceneTransitionStarted = false;
        timeElapsed = 0;

        Engine.getUserInterface().addElement(FPSCounter.class);
        splashScreen = Engine.getUserInterface().addElement(SplashScreen.class);
        loadingIcon = Engine.getUserInterface().addElement(LoadingIcon.class);
        System.out.println("Loading resources...");

        List<String> images = new ArrayList<>();
        images.add("/textures/tiles/tiles_color.jpg");
        images.add("/textures/tiles/tiles_normals.jpg");
        loadThread = new LoadThread(images);
    }

    @Override
    protected void update(double frameTime) {
        timeElapsed += frameTime;
        if (loadThread.isLoadComplete() && !sceneTransitionStarted && timeElapsed > 1) {
            System.out.println("Starting game...");
            sceneTransitionStarted = true;
            Engine.getUserInterface().removeElement(loadingIcon);
            Engine.getUserInterface().removeElement(splashScreen);
            Engine.setActiveScene(new GameScene());
        }
    }
}
