package iut.projets.trivialpursuit.engine.graphics;

import iut.projets.trivialpursuit.engine.Engine;
import iut.projets.trivialpursuit.engine.game.GameObject;
import iut.projets.trivialpursuit.engine.types.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;

public class Scene extends GameObject {

    private final Vector<Actor> actors;
    private final Vector<Actor> actorsToAdd;
    private final Vector<Actor> actorsToRemove;
    private final ArrayList<DirectionalLight> directionalLights;
    private Color backgroundColor;
    private Camera camera;

    private Vector2D mousePosition, mousePositionInScene;

    public Scene() {
        actors = new Vector<>();
        actorsToAdd = new Vector<>();
        actorsToRemove = new Vector<>();
        directionalLights = new ArrayList<>();
        backgroundColor = Color.BLACK;
        mousePosition = new Vector2D(0,0);
        mousePositionInScene = new Vector2D(0,0);
        camera = new Camera();
    }

    @Override
    public void start() {

    }

    @Override
    public void update(double frameTime) {

    }

    @Override
    public void tick(double frameTime) {
        updateMousePositionInScene();
        update(frameTime);
        for (Actor actor : actors) {
            actor.tick(frameTime);
        }
        for (Actor actor: actorsToRemove) {
            actors.removeElement(actor);
        }
        actorsToRemove.clear();
        for (Actor actor: actorsToAdd) {
            actors.addElement(actor);
            actor.start();
        }
        actorsToAdd.clear();
        actors.sort(new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                return o1.getRenderOrder() - o2.getRenderOrder();
            }
        });
    }

    public Vector<Actor> getActors() {
        return actors;
    }

    public Actor addActor(Class<? extends Actor> ActorClass) {
        Actor actor;
        try {
            actor = ActorClass.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
        actor.setScene(this);
        actorsToAdd.addElement(actor);
        actor.start();
        return actor;
    }

    public void removeActor(Actor actor) {
        actorsToRemove.addElement(actor);
    }

    public ArrayList<DirectionalLight> getLights() {
        return directionalLights;
    }

    public Light addLight(Class<? extends DirectionalLight> LightClass) {
        DirectionalLight light;
        try {
            light = LightClass.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        directionalLights.add(light);
        return light;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setMousePosition(Vector2D mousePosition) {
        this.mousePosition = mousePosition;
    }

    public Vector2D getMousePosition() {
        return mousePosition;
    }

    private void updateMousePositionInScene() {
        double zoom = camera.getZoom();
        double unit = Engine.getGameWindow().getCanvas().getHeight()/100.0;
        double screenCenterX = Engine.getGameWindow().getCanvas().getWidth()/2.0;
        double screenCenterY = Engine.getGameWindow().getCanvas().getHeight()/2.0;
        double x = (mousePosition.getX() - screenCenterX)/unit/zoom;
        double y = (mousePosition.getY() - screenCenterY)/unit/zoom;
        this.mousePositionInScene = Vector2D.add( Vector2D.rotate(new Vector2D(x, y), camera.getRotation()), camera.getPosition() );
    }

    public Vector2D getMousePositionInScene() {
        return mousePositionInScene;
    }
}
