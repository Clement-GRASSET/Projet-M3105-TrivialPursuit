package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.audio.Sound;
import iut.projets.trivialpursuit.engine.game.Animation;
import iut.projets.trivialpursuit.engine.game.Keyframe;
import iut.projets.trivialpursuit.engine.graphics.*;
import iut.projets.trivialpursuit.engine.types.*;
import iut.projets.trivialpursuit.engine.userinterface.UIButton;
import iut.projets.trivialpursuit.engine.userinterface.UIElement;
import iut.projets.trivialpursuit.game.GameInfo;
import iut.projets.trivialpursuit.game.Player;
import iut.projets.trivialpursuit.game.actors.Case;
import iut.projets.trivialpursuit.game.actors.Pawn;
import iut.projets.trivialpursuit.game.actors.GameBoard;
import iut.projets.trivialpursuit.game.questions.QuestionsManager;
import iut.projets.trivialpursuit.game.ui.CaseSelectionUI;
import iut.projets.trivialpursuit.game.ui.NewTurnAnnouncement;
import iut.projets.trivialpursuit.game.ui.QuestionUI;
import iut.projets.trivialpursuit.game.ui.RandomNumberUI;

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
    private final GameBoard gameBoard;
    private int playerIndex;

    public GameScene(GameInfo gameInfo) {
        this.players = gameInfo.getPlayers();
        playerIndex = 0;
        pawns = new HashMap<>();

        for (Player player : players) {
            System.out.println("Player : " + player.getProfile().getName());
        }

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
        /*UIButton button = new UIButton("Switch music");
        button.setAnchor(UIElement.Anchor.TOP_RIGHT);
        button.setAlignment(new Vector2D(-1 ,1));
        button.setPosition(new Vector2D(-2, 2));
        button.onClick(() -> {
            if (activeMusic == music) {
                switchMusic(music, music_thinking);
                activeMusic = music_thinking;
            } else {
                switchMusic(music_thinking, music);
                activeMusic = music;
            }
        });

        Engine.getUserInterface().addElement(button);
        */

        for (int i = 0; i < players.size(); i++) {
            Pawn pawn = (Pawn) addActor(Pawn.class);
            double rotation = Rotation.deg( 180 - i * 360.0/players.size() ).getRad();
            pawn.setPosition(new Vector2D(Math.cos(rotation)*4.3, Math.sin(rotation)*4.3));
            pawn.setColor(players.get(i).getPawnColor());
            pawn.setCurrentCase(gameBoard.getCenter());
            pawns.put(players.get(i), pawn);
        }

        playIntroAnimation(() -> {
            newTurn();
        });
    }

    private void newTurn() {
        Player player = players.get(playerIndex);

        RandomNumberUI randomNumberUI = new RandomNumberUI();
        NewTurnAnnouncement newTurnAnnouncement = new NewTurnAnnouncement(player);
        CaseSelectionUI caseSelectionUI = new CaseSelectionUI();
        QuestionUI questionUI = new QuestionUI();

        newTurnAnnouncement.onDestroy(() -> {
            Engine.getUserInterface().addElement(randomNumberUI);
        });

        randomNumberUI.onDestroy(() -> {
            moveCameraTo(new Vector2D(0,0), 1, 0.3, () -> {
                List<Case> cases = gameBoard.getReachableCases(pawns.get(player).getCurrentCase(), randomNumberUI.getNumber());
                caseSelectionUI.addButtons(cases);
                Engine.getUserInterface().addElement(caseSelectionUI);
            });
        });

        caseSelectionUI.onDestroy(() -> {
            Case c = caseSelectionUI.getSelected();
            pawns.get(player).setCurrentCase(c);
            pawns.get(player).moveTo(c.getPosition());
            moveCameraTo(c.getPosition(), 3, 0.5, () -> {
                switchMusic(music, music_thinking);
                questionUI.addQuestion(QuestionsManager.getRandomQuestion("Y", "Intermédiaire"));
                Engine.getUserInterface().addElement(questionUI);
            });
        });

        questionUI.onDestroy(() -> {
            switchMusic(music_thinking, music);
            playerIndex = Math.floorMod(playerIndex+1, players.size());
            newTurn();
        });


        moveCameraTo(pawns.get(player).getPosition(), 3, 0.8, null);
        Engine.getUserInterface().addElement(newTurnAnnouncement);
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
