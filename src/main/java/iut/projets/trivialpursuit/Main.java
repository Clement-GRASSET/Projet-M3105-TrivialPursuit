package iut.projets.trivialpursuit;

import iut.projets.trivialpursuit.engine.Game;
import iut.projets.trivialpursuit.engine.SceneManager;
import iut.projets.trivialpursuit.engine.Settings;
import iut.projets.trivialpursuit.game.ProfilesManager;
import iut.projets.trivialpursuit.game.questions.QuestionsManager;
import iut.projets.trivialpursuit.game.scenes.StartScene;

public class Main {

    public static void main(String[] args) {

        Game trivialPursuit = new Game("Trivial Pursuit", "trivial pursuit") {
            @Override
            public void start() {

                //test questions XML
                QuestionsManager.load();
                QuestionsManager.reset();

                //test profiles XML
                ProfilesManager.load();
                ProfilesManager.reset();

                Settings.setMaxFPS(0);
                Settings.setRenderScale(1);
                Settings.setShowDebug(false);

                SceneManager.setActiveScene(new StartScene());
            }
        };

    }

}
