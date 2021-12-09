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
                "/textures/slices/slice_blue.png",
                "/textures/slices/slice_blue_normals.png",
                "/textures/slices/slice_green.png",
                "/textures/slices/slice_green_normals.png",
                "/textures/slices/slice_orange.png",
                "/textures/slices/slice_orange_normals.png",
                "/textures/slices/slice_pink.png",
                "/textures/slices/slice_pink_normals.png",
                "/textures/slices/slice_purple.png",
                "/textures/slices/slice_purple_normals.png",
                "/textures/slices/slice_yellow.png",
                "/textures/slices/slice_yellow_normals.png",
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
                "/images/case_button.png",
                "/images/case_button_hover.png",
                "/images/case_button_press.png",
        };
        String [] fonts = {
        };

        LoadingTask loadingTask = new LoadingTask(images, fonts);
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
