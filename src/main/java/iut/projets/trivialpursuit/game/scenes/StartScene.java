package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.graphics.Scene;
import iut.projets.trivialpursuit.engine.userinterface.UIElement;
import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.game.ui.FPSCounter;
import iut.projets.trivialpursuit.game.ui.LoadingIcon;
import iut.projets.trivialpursuit.game.ui.SplashScreen;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartScene extends Scene {

    private class LoadThread extends Thread {
        private boolean isLoaded;
        private final String [] images, inputStreams;

        private LoadThread(String [] images, String [] inputStreams) {
            this.images = images;
            this.inputStreams = inputStreams;
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
                for (String inputStream : inputStreams) {
                    Resources.loadInputStream(inputStream);
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

        FPSCounter fpsCounter = new FPSCounter();
        fpsCounter.setRenderOrder(100);
        splashScreen = new SplashScreen();
        loadingIcon = new LoadingIcon();
        Engine.getUserInterface().addElement(fpsCounter);
        Engine.getUserInterface().addElement(splashScreen);
        Engine.getUserInterface().addElement(loadingIcon);

        String [] images = {
                "/textures/game_board/game_board.png",
                "/textures/game_board/game_board_n.png",
                "/textures/pawn/pawn_blue.png",
                "/textures/pawn/pawn_green.png",
                "/textures/pawn/pawn_orange.png",
                "/textures/pawn/pawn_pink.png",
                "/textures/pawn/pawn_purple.png",
                "/textures/pawn/pawn_yellow.png",
                "/textures/pawn/pawn_normals.png",
                "/images/main-menu-background.png",
                "/images/trivial-pursuit-logo.png",
        };
        String [] inputStreams = {
                "/sounds/musics/main_menu.wav",
                "/sounds/musics/origamikingBB.wav",
                "/sounds/musics/origamikingBBT.wav"
        };
        loadThread = new LoadThread(images, inputStreams);
    }

    @Override
    public void update(double frameTime) {
        timeElapsed += frameTime;
        if (loadThread.isLoadComplete() && !sceneTransitionStarted && timeElapsed > 1) {
            System.out.println("Starting game...");
            sceneTransitionStarted = true;
            Engine.getUserInterface().removeElement(loadingIcon);
            Engine.getUserInterface().removeElement(splashScreen);
            Engine.setActiveScene(new MainMenuScene());
        }
    }
}
