package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.Resources;
import iut.projets.trivialpursuit.engine.audio.Sound;
import iut.projets.trivialpursuit.engine.game.Animation;
import iut.projets.trivialpursuit.engine.game.Delay;
import iut.projets.trivialpursuit.engine.game.Keyframe;
import iut.projets.trivialpursuit.engine.graphics.*;
import iut.projets.trivialpursuit.engine.types.*;
import iut.projets.trivialpursuit.engine.userinterface.UIButton;
import iut.projets.trivialpursuit.game.actors.gameboard.GameBoard;

import java.io.InputStream;

public class GameScene extends Scene {

    DirectionalLight light;
    double compteur;
    private final Sound music, music_thinking;
    private Sound activeMusic;

    public GameScene() {
        GameBoard gameBoard = (GameBoard) addActor(GameBoard.class);

        compteur = 0;
        light = (DirectionalLight) addLight(DirectionalLight.class);
        light.setDirection(new Vector3D(1,1,-1));
        light.setIntensity(1.5);

        music = new Sound(Resources.getInputStream("/sounds/musics/origamikingBB.wav"));
        music_thinking = new Sound(Resources.getInputStream("/sounds/musics/origamikingBBT.wav"));
        music.setLoop(true, 2.18181818);
        music_thinking.setLoop(true, 2.18181818);
        music.setVolume(1);
        music_thinking.setVolume(0);
        music.play();
        music_thinking.play();

        activeMusic = music;
        UIButton button = new UIButton("Switch music");
        button.onClick(() ->  {
            if (activeMusic == music) {
                switchMusic(music, music_thinking);
                activeMusic = music_thinking;
            } else {
                switchMusic(music_thinking, music);
                activeMusic = music;
            }
        });
        Engine.getUserInterface().addElement(button);
    }

    public void switchMusic(Sound current, Sound target) {
        System.out.println("Music switch");
        Animation animation = new Animation(new Keyframe[]{
                new Keyframe(0, 0),
                new Keyframe(1, 2)
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

        light.setDirection(new Vector3D(
                getMousePositionInScene().getX()*-1,
                getMousePositionInScene().getY()*-1,
                -2
        ));

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
