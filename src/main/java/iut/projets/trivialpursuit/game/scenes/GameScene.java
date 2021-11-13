package iut.projets.trivialpursuit.game.scenes;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.graphics.*;
import iut.projets.trivialpursuit.engine.types.*;
import iut.projets.trivialpursuit.game.actors.gameboard.GameBoard;

import java.awt.*;

public class GameScene extends Scene {

    DirectionalLight light;
    double compteur;

    public GameScene() {
        GameBoard gameBoard = (GameBoard) addActor(GameBoard.class);

        compteur = 0;
        light = (DirectionalLight) addLight(DirectionalLight.class);
        light.setDirection(new Vector3D(1,1,-1));
        light.setIntensity(5);
    }

    @Override
    protected void update(double frameTime) {
        compteur += frameTime;
        // Fais tourner la lumière autour de la scene
        //light.setDirection(new Vector3D(Math.cos(compteur), Math.sin(compteur), -0.08));
        Point mousePosition = getMousePosition();
        double x = mousePosition.getX()/(double)Engine.getGameWindow().getCanvas().getWidth() - 0.5;
        double y = mousePosition.getY()/(double)Engine.getGameWindow().getCanvas().getHeight() - 0.5;

        light.setDirection(new Vector3D(
                x*-1,
                y*-1,
                -0.02
        ));

        //getCamera().setPosition(new Vector2D(50*Math.cos(System.currentTimeMillis()*0.002), 50*Math.sin(System.currentTimeMillis()*0.002)));
        //getCamera().setPosition(new Vector2D(50, 0));
        //getCamera().setRotation(Rotation.deg(System.currentTimeMillis()*0.05));
        //getCamera().setRotation(Rotation.deg(45));
    }
}
