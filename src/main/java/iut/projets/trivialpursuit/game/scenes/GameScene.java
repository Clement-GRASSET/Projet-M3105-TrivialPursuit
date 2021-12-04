package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.audio.Sound;
import iut.projets.trivialpursuit.engine.game.Animation;
import iut.projets.trivialpursuit.engine.game.Keyframe;
import iut.projets.trivialpursuit.engine.graphics.*;
import iut.projets.trivialpursuit.engine.types.*;
import iut.projets.trivialpursuit.game.GameInfo;
import iut.projets.trivialpursuit.game.Player;
import iut.projets.trivialpursuit.game.TrivialPursuitColor;
import iut.projets.trivialpursuit.game.actors.Case;
import iut.projets.trivialpursuit.game.actors.Pawn;
import iut.projets.trivialpursuit.game.actors.GameBoard;
import iut.projets.trivialpursuit.game.questions.QuestionsManager;
import iut.projets.trivialpursuit.game.ui.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameScene extends Scene {

    DirectionalLight light;
    double compteur;
    private final Sound music, music_thinking;
    private Sound activeMusic;
    private final List<Player> players;
    private final Map<Player, Pawn> pawns;
    private final Map<Player, Map<TrivialPursuitColor, Boolean>> scores;
    private final GameBoard gameBoard;
    private final GameUI gameUI;
    private int playerIndex;

    public GameScene(GameInfo gameInfo) {
        this.players = gameInfo.getPlayers();
        playerIndex = 0;
        pawns = new HashMap<>();
        scores = new HashMap<>();

        gameUI = new GameUI(players, scores);
        Engine.getUserInterface().addElement(gameUI);

        setBackgroundColor(new Color(20,20,20));

        this.gameBoard = (GameBoard) addActor(GameBoard.class);

        music = new Sound(Resources.getInputStream("/sounds/musics/origamikingBB.wav"));
        music_thinking = new Sound(Resources.getInputStream("/sounds/musics/origamikingBBT.wav"));
    }

    @Override
    public void start() {
        compteur = 0;

        light = (DirectionalLight) addLight(DirectionalLight.class);
        light.setDirection(new Vector3D(1,1,-1));
        light.setIntensity(1.5);

        music.setLoop(true, 2.18181818);
        music_thinking.setLoop(true, 2.18181818);
        music.setVolume(1);
        music_thinking.setVolume(0);
        music.play();
        music_thinking.play();

        activeMusic = music;

        for (int i = 0; i < players.size(); i++) {
            Pawn pawn = (Pawn) addActor(Pawn.class);
            double rotation = Rotation.deg( 180 - i * 360.0/players.size() ).getRad();
            pawn.setPosition(new Vector2D(Math.cos(rotation)*4.3, Math.sin(rotation)*4.3));
            pawn.setColor(players.get(i).getPawnColor());
            pawn.setCurrentCase(gameBoard.getCenter());
            pawns.put(players.get(i), pawn);

            Map<TrivialPursuitColor, Boolean> playerScore = new HashMap<>();
            playerScore.put(TrivialPursuitColor.BLUE, false);
            playerScore.put(TrivialPursuitColor.GREEN, false);
            playerScore.put(TrivialPursuitColor.ORANGE, false);
            playerScore.put(TrivialPursuitColor.PINK, false);
            playerScore.put(TrivialPursuitColor.PURPLE, false);
            playerScore.put(TrivialPursuitColor.YELLOW, false);
            scores.put(players.get(i), playerScore);
        }

        playIntroAnimation(() -> {
            newTurn();
        });
    }

    private void newTurn() {
        Player player = players.get(playerIndex);

        NewTurnAnnouncement newTurnAnnouncement = new NewTurnAnnouncement(player);
        newTurnAnnouncement.onDestroy(() -> {
            beginTurn();
        });

        moveCameraTo(pawns.get(player).getPosition(), 3, 0.8, null);
        Engine.getUserInterface().addElement(newTurnAnnouncement);
    }

    private void beginTurn() {
        Player player = players.get(playerIndex);

        RandomNumberUI randomNumberUI = new RandomNumberUI();

        randomNumberUI.onDestroy(() -> {

            moveCameraTo(new Vector2D(0,0), 1, 0.3, () -> {

                List<Case> cases = gameBoard.getReachableCases(pawns.get(player).getCurrentCase(), randomNumberUI.getNumber());
                CaseSelectionUI caseSelectionUI = new CaseSelectionUI();
                caseSelectionUI.addButtons(cases);

                caseSelectionUI.onDestroy(() -> {

                    Case c = caseSelectionUI.getSelected();
                    System.out.println("Case choisie : " + c.getType().name());
                    pawns.get(player).setCurrentCase(c);
                    pawns.get(player).moveTo(c.getPosition());

                    CaseAnnouncement caseAnnouncement = new CaseAnnouncement(c, player.getProfile());

                    caseAnnouncement.onDestroy(() ->  {

                        if (c.getType() == Case.CaseType.ROLL_AGAIN) {
                            beginTurn();
                        } else if (c.getType() == Case.CaseType.MULTI) {
                            playerIndex = Math.floorMod(playerIndex + 1, players.size());
                            newTurn();
                        } else {
                            moveCameraTo(c.getPosition(), 3, 0.5, () -> {

                                switchMusic(music, music_thinking);

                                QuestionUI questionUI = new QuestionUI(QuestionsManager.getRandomQuestion("Y", "Intermédiaire"));
                                questionUI.onDestroy(() -> {
                                    switchMusic(music_thinking, music);
                                    if (questionUI.getSuccess()) {
                                        scores.get(player).put(c.getColor(), true);
                                        gameUI.updateScores();
                                        beginTurn();
                                    } else {
                                        playerIndex = Math.floorMod(playerIndex + 1, players.size());
                                        newTurn();
                                    }
                                });
                                Engine.getUserInterface().addElement(questionUI);

                            });
                        }

                    });
                    Engine.getUserInterface().addElement(caseAnnouncement);

                });
                Engine.getUserInterface().addElement(caseSelectionUI);

            });

        });

        Engine.getUserInterface().addElement(randomNumberUI);
    }

    private void moveCameraTo(Vector2D position, double zoom, double speed, Runnable then) {
        Vector2D cameraPosition = getCamera().getPosition();
        double cameraZoom = getCamera().getZoom();

        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(1, speed)
        });
        animation.onUpdate(() -> {
            getCamera().setPosition(new Vector2D(
                    interpolate(cameraPosition.getX(), position.getX(), animation.getValue()),
                    interpolate(cameraPosition.getY(), position.getY(), animation.getValue())
            ));
            getCamera().setZoom( interpolate(cameraZoom, zoom, animation.getValue()) );
        });
        if (then != null)
            animation.onFinish(then);
        animation.start(this);
    }

    private void playIntroAnimation(Runnable then) {
        Animation animation = new Animation(new Keyframe[] {
                new Keyframe(0, 0),
                new Keyframe(1, 4)
        });
        animation.onUpdate(() -> {
            getCamera().setZoom( interpolate(0.7, 3, animation.getValue()) );
            light.setDirection(new Vector3D(
                    1,
                    1,
                    1/interpolate(-20, -0.8, animation.getValue())
            ));
        });
        animation.onFinish(then);
        animation.start(this);
    }

    public void switchMusic(Sound current, Sound target) {
        Animation animation = new Animation(new Keyframe[]{
                new Keyframe(0, 0),
                new Keyframe(1, 1)
        });
        animation.onUpdate(() -> {
            float volume = (float) animation.getValue();
            target.setVolume(volume);
            current.setVolume(1-volume);
        });
        animation.onFinish(() -> {
            target.setVolume(1);
            current.setVolume(0);
        });
        animation.start(this);
    }

    @Override
    public void update(double frameTime) {
        compteur += frameTime;
        //Fait tourner la lumière autour de la scene
        //light.setDirection(new Vector3D(Math.cos(compteur), Math.sin(compteur), -0.3));

        /*light.setDirection(new Vector3D(
                getMousePositionInScene().getX()*-1,
                getMousePositionInScene().getY()*-1,
                -2
        ));*/

        //light.setDirection(new Vector3D(1,1,-0.5));

        //getCamera().setPosition(new Vector2D(50*Math.cos(System.currentTimeMillis()*0.002), 50*Math.sin(System.currentTimeMillis()*0.002)));
        //getCamera().setPosition(new Vector2D(50, 0));
        //getCamera().setRotation(Rotation.deg(System.currentTimeMillis()*0.02));
        //getCamera().setRotation(Rotation.deg(45));

        //getCamera().setZoom(Math.sin(System.currentTimeMillis()/2000.0)+1);

        /*double zoom = getMousePositionInScene().getY()*-0.001;
        getCamera().setZoom(Math.min(Math.max(getCamera().getZoom() + zoom, 0.5), 10));
        System.out.println(zoom);*/
    }
}
