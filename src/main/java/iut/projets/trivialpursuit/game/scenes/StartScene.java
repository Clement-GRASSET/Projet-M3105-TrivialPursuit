package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.SceneManager;
import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.core.Scene;
import iut.projets.trivialpursuit.engine.core.UIElement;
import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.game.ui.FPSCounter;
import iut.projets.trivialpursuit.game.ui.SplashScreen;

import java.io.IOException;

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
    private final UIElement splashScreen;
    private double timeElapsed;

    public StartScene() {
        sceneTransitionStarted = false;
        timeElapsed = 0;

        FPSCounter fpsCounter = new FPSCounter();
        fpsCounter.setRenderOrder(100);
        UIManager.addElement(fpsCounter);

        splashScreen = new SplashScreen();
        UIManager.addElement(splashScreen);

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
                "/images/player_info_blue.png",
                "/images/player_info_green.png",
                "/images/player_info_orange.png",
                "/images/player_info_pink.png",
                "/images/player_info_purple.png",
                "/images/player_info_yellow.png",
                "/images/pawn.png",
                "/images/slice_blue.png",
                "/images/slice_green.png",
                "/images/slice_orange.png",
                "/images/slice_pink.png",
                "/images/slice_purple.png",
                "/images/slice_yellow.png",
        };
        String [] inputStreams = {
                "/sounds/musics/main_menu.wav",
                "/sounds/musics/origamiKingBB.wav",
                "/sounds/musics/origamiKingBBT.wav"
        };
        loadThread = new LoadThread(images, inputStreams);
    }

    @Override
    public void update(double frameTime) {
        timeElapsed += frameTime;
        if (loadThread.isLoadComplete() && !sceneTransitionStarted && timeElapsed > 1) {
            System.out.println("Starting game...");
            sceneTransitionStarted = true;
            UIManager.removeElement(splashScreen);
            SceneManager.setActiveScene(new MainMenuScene());
        }
    }
}
