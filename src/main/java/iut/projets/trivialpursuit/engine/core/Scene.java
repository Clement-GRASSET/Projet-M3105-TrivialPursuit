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

    /**
     * Renvoie la liste des acteurs présents dans la scène.
     * @return La liste des acteurs présents dans la scène.
     */
    public Vector<Actor> getActors() {
        return actors;
    }

    /**
     * Construit un acteur dans la scène.
     * @param ActorClass La classe de l'acteur à construire.
     * @return L'acteur construit.
     */
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

    /**
     * Retire un acteur de la scène.
     * @param actor L'acteur à retirer de la scène.
     */
    public void removeActor(Actor actor) {
        actorsToRemove.addElement(actor);
    }

    /**
     * Renvoie la liste des lumières directionnelles dans la scène.
     * @return La liste des lumières directionnelles dans la scène.
     */
    public ArrayList<DirectionalLight> getDirectionalLights() {
        return directionalLights;
    }

    /**
     * Renvoie la liste des lumières locales dans la scène.
     * @return La liste des lumières locales dans la scène.
     */
    public ArrayList<PointLight> getPointLights() {
        return pointLights;
    }

    /**
     * Ajoute une lumière dans la scène.
     * @param light Lumière directionnelle à ajouter.
     */
    public void addLight(DirectionalLight light) {
        directionalLights.add(light);
    }

    /**
     * Ajoute une lumière dans la scène.
     * @param light Lumière locale à ajouter.
     */
    public void addLight(PointLight light) {
        pointLights.add(light);
    }

    /**
     * Retire une lumière de la scène.
     * @param light Lumière directionnelle à retirer.
     */
    public void removeLight(DirectionalLight light) {
        directionalLights.remove(light);
    }

    /**
     * Retire une lumière de la scène.
     * @param light Lumière directionnelle à retirer.
     */
    public void removeLight(PointLight light) {
        pointLights.remove(light);
    }

    /**
     * Renvoie la couleur de fond de la scène.
     * @return La couleur de fond de la scène.
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Définit la couleur de fond de la scène.
     * @param backgroundColor La couleur de fond de la scène.
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Renvoie la caméra utilisée par la scène.
     * @return La caméra utilisée par la scène.
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Définit la caméra utilisée par la scène.
     * @param camera La caméra utilisée par la scène.
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Met à jour la position de la souris.
     * @param mousePosition La position de la souris.
     */
    public void setMousePosition(Vector2D mousePosition) {
        this.mousePosition = mousePosition;
    }

    /**
     * Renvoie la position de la souris.
     * @return La position de la souris.
     */
    public Vector2D getMousePosition() {
        return mousePosition;
    }

    /**
     * Met à jour la position de la souris dans la scène.
     */
    private void updateMousePositionInScene() {
        this.mousePositionInScene = screenToSceneCoordinates(mousePosition);
    }

    /**
     * Convertit des coordonnées à l'écran en coordonnées dans la scène.
     * @param coordinates Les coordonnées à l'écran.
     * @return Les coordonnées dans la scène.
     */
    public Vector2D screenToSceneCoordinates(Vector2D coordinates) {
        double zoom = camera.getZoom();
        double unit = Game.getWindow().getCanvas().getHeight()/100.0;
        double screenCenterX = Game.getWindow().getCanvas().getWidth()/2.0;
        double screenCenterY = Game.getWindow().getCanvas().getHeight()/2.0;
        double x = (coordinates.getX() - screenCenterX)/unit/zoom;
        double y = (coordinates.getY() - screenCenterY)/unit/zoom;
        return Vector2D.add( Vector2D.rotate(new Vector2D(x, y), camera.getRotation()), camera.getPosition() );
    }

    /**
     * Convertit des coordonnées dans la scène en coordonnées à l'écran.
     * @param coordinates Les coordonnées dans la scène.
     * @return Les coordonnées à l'écran.
     */
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

    /**
     * Renvoie la position de la souris dans la scène.
     * @return La position de la souris dans la scène.
     */
    public Vector2D getMousePositionInScene() {
        return mousePositionInScene;
    }
}
