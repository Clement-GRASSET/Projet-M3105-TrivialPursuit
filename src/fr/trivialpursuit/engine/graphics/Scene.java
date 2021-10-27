package fr.trivialpursuit.engine.graphics;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Scene {

    private final Vector<Actor> actors;
    private Vector<Actor> actorsToAdd;
    private Vector<Actor> actorsToRemove;
    private ArrayList<DirectionalLight> directionalLights;
    private Color backgroundColor;

    public Scene() {
        actors = new Vector<>();
        actorsToAdd = new Vector<>();
        actorsToRemove = new Vector<>();
        directionalLights = new ArrayList<>();
        backgroundColor = Color.BLACK;
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
            return null;
        }
        actor.setScene(this);
        actorsToAdd.addElement(actor);
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
}
