package iut.projets.trivialpursuit;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.Game;
import iut.projets.trivialpursuit.engine.Settings;
import iut.projets.trivialpursuit.game.questions.QuestionsManager;
import iut.projets.trivialpursuit.game.scenes.StartScene;

public class TrivialPursuit extends Game {

    @Override
    public void start() {
        setName("Trivial Pursuit");
        Settings.setMaxFPS(0);
        Settings.setRenderScale(1);
        Settings.setShowDebug(false);

        Engine.setActiveScene(new StartScene());
    }

    public static void main(String[] args) {
        //test questions XML
        QuestionsManager.load();
        QuestionsManager.reset();

        Engine.start(new TrivialPursuit());
    }

}
