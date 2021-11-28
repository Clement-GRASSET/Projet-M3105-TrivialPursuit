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
import iut.projets.trivialpursuit.game.TrivialPursuitColor;
import iut.projets.trivialpursuit.game.actors.Pawn;
import iut.projets.trivialpursuit.game.actors.GameBoard;
import iut.projets.trivialpursuit.game.ui.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class GameScene extends Scene {

    DirectionalLight light;
    double compteur;
    private final Sound music, music_thinking;
    private Sound activeMusic;
    private final List<Player> players;
    private final List<Pawn> pawns;

    public GameScene(GameInfo gameInfo) {
        this.players = gameInfo.getPlayers();
        pawns = new ArrayList<>();

        for (Player player : players) {
            System.out.println("Player : " + player.getProfile().getName());
        }

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
        UIButton button = new UIButton("Switch music");
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

        GameBoard gameBoard = (GameBoard) addActor(GameBoard.class);

        for (int i = 0; i < players.size(); i++) {
            Pawn pawn = (Pawn) addActor(Pawn.class);
            double rotation = Rotation.deg( i * 360.0/players.size() ).getRad();
            pawn.setPosition(new Vector2D(Math.cos(rotation)*4.3, Math.sin(rotation)*4.3));
            pawn.setColor(players.get(i).getPawnColor());
            pawns.add(pawn);
        }

        Engine.getUserInterface().addElement(button);
        playIntroAnimation(() -> {});
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

    public double interpolate(double a, double b, double alpha) {
        return (1-alpha)*a + alpha*b;
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
