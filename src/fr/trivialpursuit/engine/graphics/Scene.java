package fr.trivialpursuit.engine.graphics;

import fr.trivialpursuit.engine.graphics.Actor;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

public class Scene {

    private final Vector<Actor> actors;
    private Vector<Actor> actorsToAdd;
    private Vector<Actor> actorsToRemove;
    private Color backgroundColor;

    public Scene() {
        actors = new Vector<Actor>();
        actorsToAdd = new Vector<Actor>();
        actorsToRemove = new Vector<Actor>();
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
        actorsToRemove = new Vector<Actor>();
        for (Actor actor: actorsToAdd) {
            actors.addElement(actor);
        }
        actorsToAdd = new Vector<Actor>();
    }

    protected void update(double frameTime) {
        
    }

    public Vector<Actor> getActors() {
        return actors;
    }

    public Actor addActor(Class<? extends Actor> ActorClass) {
        Actor actor = null;
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

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
