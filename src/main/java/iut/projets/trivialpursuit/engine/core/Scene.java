package iut.projets.trivialpursuit.engine.core;

import iut.projets.trivialpursuit.engine.Game;
import iut.projets.trivialpursuit.engine.basetypes.DirectionalLight;
import iut.projets.trivialpursuit.engine.basetypes.PointLight;
import iut.projets.trivialpursuit.engine.types.Rotation;
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
    private final ArrayList<PointLight> pointLights;
    private Color backgroundColor;
    private Camera camera;

    private Vector2D mousePosition, mousePositionInScene;

    public Scene() {
        actors = new Vector<>();
        actorsToAdd = new Vector<>();
        actorsToRemove = new Vector<>();
        directionalLights = new ArrayList<>();
        pointLights = new ArrayList<>();
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
        super.tick(frameTime);
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

    public ArrayList<DirectionalLight> getDirectionalLights() {
        return directionalLights;
    }

    public ArrayList<PointLight> getPointLights() {
        return pointLights;
    }

    public void addLight(DirectionalLight light) {
        directionalLights.add(light);
    }

    public void addLight(PointLight light) {
        pointLights.add(light);
    }

    public void removeLight(DirectionalLight light) {
        directionalLights.remove(light);
    }

    public void removeLight(PointLight light) {
        pointLights.remove(light);
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
        this.mousePositionInScene = screenToSceneCoordinates(mousePosition);
    }

    public Vector2D screenToSceneCoordinates(Vector2D coordinates) {
        double zoom = camera.getZoom();
        double unit = Game.getWindow().getCanvas().getHeight()/100.0;
        double screenCenterX = Game.getWindow().getCanvas().getWidth()/2.0;
        double screenCenterY = Game.getWindow().getCanvas().getHeight()/2.0;
        double x = (coordinates.getX() - screenCenterX)/unit/zoom;
        double y = (coordinates.getY() - screenCenterY)/unit/zoom;
        return Vector2D.add( Vector2D.rotate(new Vector2D(x, y), camera.getRotation()), camera.getPosition() );
    }

    public Vector2D sceneToScreenCoordinates(Vector2D coordinates) {
        double zoom = camera.getZoom();
        double unit = Game.getWindow().getCanvas().getHeight()/100.0;
        double screenCenterX = Game.getWindow().getCanvas().getWidth()/2.0;
        double screenCenterY = Game.getWindow().getCanvas().getHeight()/2.0;
        Vector2D rotated_position = Vector2D.rotate(Vector2D.subtract(coordinates, camera.getPosition()), Rotation.rad(camera.getRotation().getRad()*-1));
        double x = ((rotated_position.getX())*zoom*unit + screenCenterX);
        double y = ((rotated_position.getY())*zoom*unit + screenCenterY);
        return new Vector2D(x, y);
    }

    public Vector2D getMousePositionInScene() {
        return mousePositionInScene;
    }
}
