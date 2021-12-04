package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.SceneManager;
import iut.projets.trivialpursuit.engine.UIManager;
import iut.projets.trivialpursuit.engine.basetypes.LoadingTask;
import iut.projets.trivialpursuit.engine.core.Scene;
import iut.projets.trivialpursuit.game.ui.FPSCounter;
import iut.projets.trivialpursuit.game.ui.SplashScreen;

public class StartScene extends Scene {

    @Override
    public void start() {

        FPSCounter fpsCounter = new FPSCounter();
        fpsCounter.setRenderOrder(100);
        UIManager.addElement(fpsCounter);

        SplashScreen splashScreen = new SplashScreen();
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

        LoadingTask loadingTask = new LoadingTask(images, inputStreams);
        loadingTask.onUpdate(() -> {
            splashScreen.setProgress(loadingTask.getProgress());
        });
        loadingTask.onFinish(() -> {
            System.out.println("Starting game...");
            UIManager.removeElement(splashScreen);
            SceneManager.setActiveScene(new MainMenuScene());
        });
        loadingTask.start(this);
    }
}
