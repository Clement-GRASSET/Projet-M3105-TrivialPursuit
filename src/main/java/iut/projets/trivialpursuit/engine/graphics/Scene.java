package iut.projets.trivialpursuit.engine.graphics;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;

public class Scene {

    private final Vector<Actor> actors;
    private Vector<Actor> actorsToAdd;
    private Vector<Actor> actorsToRemove;
    private ArrayList<DirectionalLight> directionalLights;
    private Color backgroundColor;
    private Camera camera;

    Point mousePosition;

    public Scene() {
        actors = new Vector<>();
        actorsToAdd = new Vector<>();
        actorsToRemove = new Vector<>();
        directionalLights = new ArrayList<>();
        backgroundColor = Color.BLACK;
        mousePosition = new Point(0,0);
        camera = new Camera();
    }

    public void tick(double frameTime) {
        update(frameTime);
        for (Actor actor : actors) {
            actor.update(frameTime);
        }
        for (Actor actor: actorsToRemove) {
            actors.removeElement(actor);
        }
        actorsToRemove = new Vector<>();
        for (Actor actor: actorsToAdd) {
            actors.addElement(actor);
        }
        actorsToAdd = new Vector<>();
        actors.sort(new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                return o1.getRenderOrder() - o2.getRenderOrder();
            }
        });
    }

    protected void update(double frameTime) {
        
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

    public void setMousePosition(Point mousePosition) {
        this.mousePosition = mousePosition;
    }

    public Point getMousePosition() {
        return mousePosition;
    }
}
