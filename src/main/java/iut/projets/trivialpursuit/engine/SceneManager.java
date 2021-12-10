package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.basetypes.DirectionalLight;
import iut.projets.trivialpursuit.engine.basetypes.PointLight;
import iut.projets.trivialpursuit.engine.core.Actor;
import iut.projets.trivialpursuit.engine.core.Camera;
import iut.projets.trivialpursuit.engine.core.Scene;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.types.Vector3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

public class SceneManager {

    private static class ActorToDraw {

        Actor actor;
        double x, y, w, h;
        Vector2D translation;
        double r;

        ActorToDraw(Actor actor) {
            this.actor = actor;

            x = actor.getPosition().getX();
            y = actor.getPosition().getY();
            w = actor.getScale().getX();
            h = actor.getScale().getY();
            r = actor.getRotation().getRad();

            translation = new Vector2D(
                    w/-2 + x,
                    h/-2 + y
            );
        }

        void drawColor(Graphics2D g) {
            g.translate(translation.getX(), translation.getY());
            g.rotate(r, w/2, h/2);
            g.drawImage(actor.getMaterial().getColor(), 0, 0, (int)w, (int)h, null);
            g.rotate(-r, w/2, h/2);
            g.translate(-translation.getX(), -translation.getY());
        }

        void drawNormal(Graphics2D g) {
            g.translate(translation.getX(), translation.getY());
            g.rotate(r, w/2, h/2);
            g.drawImage(actor.getMaterial().getNormals(), 0, 0, (int)w, (int)h, null);
            g.rotate(-r, w/2, h/2);
            g.translate(-translation.getX(), -translation.getY());
        }

    }

    private static class DirectionalLightToRender {
        final Vector3D intensityAtFlatNormal;
        final Vector3D direction, intensity;

        DirectionalLightToRender(DirectionalLight directionalLight) {
            this.direction = directionalLight.getDirection();
            this.intensity = directionalLight.getIntensity();

            double value = Vector3D.dot(directionalLight.getDirection(), new Vector3D(0, 0, -1));
            this.intensityAtFlatNormal = new Vector3D(
                    value * intensity.getX(),
                    value * intensity.getY(),
                    value * intensity.getZ()
            );
        }
    }

    private static class PointLightToRender {
        final Vector3D intensity;
        final int x, y, radius;
        final double lightHeight;
        PointLightToRender(PointLight pointLight) {
            Vector2D screenPosition = activeScene.sceneToScreenCoordinates(pointLight.getPosition());
            x = (int) (screenPosition.getX() * (double) width/canvasWidth);
            y = (int) (screenPosition.getY() * (double) height/canvasHeight);
            radius = (int)(pointLight.getRadius() * height/100 * activeScene.getCamera().getZoom());
            intensity = pointLight.getIntensity();
            lightHeight = pointLight.getHeight();
        }
    }

    private static int width = 0, height = 0, canvasWidth = 0, canvasHeight = 0;
    private static double renderScale = 1;
    private static Vector2D mousePosition = new Vector2D(0,0);

    private static BufferedImage colorBuffer;
    private static BufferedImage normalsBuffer;
    private static BufferedImage finalBuffer;

    private static Scene activeScene = new Scene(), nextScene = null;

    public static void tick(double frameTime) {
        if (nextScene != null) {
            activeScene = nextScene;
            nextScene = null;
            activeScene.start();
        }
        activeScene.setMousePosition(mousePosition);
        activeScene.tick(frameTime);
    }

    public static Scene getActiveScene() {
        return activeScene;
    }

    public static void setActiveScene(Scene scene) {
        nextScene = scene;
    }

    public static void render(Graphics g) {

        Camera camera = activeScene.getCamera();

        ArrayList<ActorToDraw> actorsToDraw = new ArrayList<>();
        for (Actor actor : activeScene.getActors()) {
            if (actor.getAllowRender())
                actorsToDraw.add( new ActorToDraw(actor) );
        }

        Thread colorThread = drawColorBuffer(camera, actorsToDraw);
        Thread normalThread = drawNormalBufferImage(camera, actorsToDraw);
        try {
            normalThread.join();
            colorThread.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        drawFinalBuffer();

        g.drawImage(finalBuffer, 0, 0, canvasWidth, canvasHeight, null);
    }

    private static Thread drawColorBuffer(Camera camera, ArrayList<ActorToDraw> actorsToDraw) {
        Thread thread = new Thread(() -> {
            double unit = camera.getZoom()*height/100.0;
            Graphics2D g = (Graphics2D) colorBuffer.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(activeScene.getBackgroundColor());
            g.fillRect(0, 0, width, height);
            g.translate(width/2.0, height/2.0);
            g.rotate(-camera.getRotation().getRad());
            g.translate(-camera.getPosition().getX()*unit, -camera.getPosition().getY()*unit);
            g.scale(unit, unit);

            for (ActorToDraw actorToDraw : actorsToDraw) {
                actorToDraw.drawColor(g);
            }

            g.dispose();
        });
        thread.start();
        return thread;
    }

    private static Thread drawNormalBufferImage(Camera camera, ArrayList<ActorToDraw> actorsToDraw) {
        Thread thread = new Thread(() -> {
            double unit = camera.getZoom()*height/100.0;
            Graphics2D g = (Graphics2D) normalsBuffer.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(new Color(127, 127, 255));
            g.fillRect(0, 0, width, height);
            g.translate(width/2.0, height/2.0);
            g.rotate(-camera.getRotation().getRad());
            g.translate(-camera.getPosition().getX()*unit, -camera.getPosition().getY()*unit);
            g.scale(unit, unit);

            for (ActorToDraw actorToDraw : actorsToDraw) {
                actorToDraw.drawNormal(g);
            }

            g.dispose();
        });
        thread.start();
        return thread;
    }

    private static void drawFinalBuffer() {
        int nbThreads = Runtime.getRuntime().availableProcessors();
        Vector<Thread> threads = new Vector<>();
        int length = width*height/nbThreads;

        // Récupération des pixels par reference de tous les buffers
        int [] colorBufferPixels = ( (DataBufferInt) colorBuffer.getRaster().getDataBuffer() ).getData();
        int [] normalsBufferPixels = ( (DataBufferInt) normalsBuffer.getRaster().getDataBuffer() ).getData();
        int [] finalBufferPixels = ( (DataBufferInt) finalBuffer.getRaster().getDataBuffer() ).getData();

        // Préparation des lumières
        int nbDirectionalLights = activeScene.getDirectionalLights().size();
        DirectionalLightToRender [] directionalLights = new DirectionalLightToRender[nbDirectionalLights];
        for (int i = 0; i < nbDirectionalLights; i++) {
            directionalLights[i] = new DirectionalLightToRender(activeScene.getDirectionalLights().get(i));
        }

        int nbPointLights = activeScene.getPointLights().size();
        PointLightToRender [] pointLights = new PointLightToRender[nbPointLights];
        for (int i = 0; i < nbPointLights; i++) {
            pointLights[i] = new PointLightToRender(activeScene.getPointLights().get(i));
        }

        // Création de threads (1 par CPU) qui vont traiter chacuns une partie de l'image
        for (int i = 0; i < nbThreads; i++) {
            int start = i * length;                                 // Début de la zone de l'image à traiter
            int end = (i+1 == length) ? length-1 : (i+1)*length;    // Fin de la zone de l'image à traiter
            Thread t = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    // Coordonnées x et y du pixel j
                    int x = j%width;
                    int y = j/width;

                    // Récupération de la couleur et de l'angle du pixel
                    Color color = new Color(colorBufferPixels[j]);
                    Color normals = new Color(normalsBufferPixels[j]);

                    boolean isPixelFlat = normals.getRed()==127 && normals.getGreen()==127 && normals.getBlue()==255;

                    // Eclairage du pixel
                    double light_r = 0, light_g = 0, light_b = 0;

                    int k = 0;
                    while (k < nbDirectionalLights) {

                        DirectionalLightToRender light = directionalLights[k];

                        if (isPixelFlat) {
                            light_r += light.intensityAtFlatNormal.getX();
                            light_g += light.intensityAtFlatNormal.getY();
                            light_b += light.intensityAtFlatNormal.getZ();
                        }
                        else {
                            // Quantité de lumière sur le pixel en fonction des angles (Produit scalaire de pixel_angle et direction)
                            double lightValue = Math.max(Vector3D.dot(new Vector3D(
                                    (normals.getRed()/255.0 - 0.5) * 2,
                                    (normals.getGreen()/255.0 - 0.5) * -2,
                                    (normals.getBlue()/255.0 - 0.5) * 2
                            ), new Vector3D(
                                    light.direction.getX()*-1,
                                    light.direction.getY()*-1,
                                    light.direction.getZ()*-1
                            )), 0);

                            // Ajout de la lumière calculée (lightValue multiplié par la couleur de la lumière) au total de l'éclairage du pixel
                            Vector3D intensity = light.intensity;
                            light_r += lightValue * intensity.getX();
                            light_g += lightValue * intensity.getY();
                            light_b += lightValue * intensity.getZ();
                        }
                        k++;
                    }

                    k = 0;
                    while (k < nbPointLights) {
                        PointLightToRender light = pointLights[k];
                        int screenPositionX = light.x;
                        int screenPositionY = light.y;
                        double distance = Math.sqrt((x-screenPositionX)*(x-screenPositionX) + (y-screenPositionY)*(y-screenPositionY))/light.radius;
                        if (distance < 1) {
                            double lightValue = Math.max(Vector3D.dot(new Vector3D(
                                    (normals.getRed()/255.0 - 0.5) * 2,
                                    (normals.getGreen()/255.0 - 0.5) * -2,
                                    (normals.getBlue()/255.0 - 0.5) * 2
                            ), Vector3D.normalize( new Vector3D(
                                    screenPositionX-x, screenPositionY-y, light.lightHeight*height/100.0
                            ))), 0) * Math.pow(1-distance, 2);

                            Vector3D intensity = light.intensity;  // Inensité (en %) de la lumière (x=r, y=g, z=b)
                            // Ajout de la lumière calculée (lightValue multiplié par la couleur de la lumière) au total de l'éclairage du pixel
                            light_r += lightValue * intensity.getX();
                            light_g += lightValue * intensity.getY();
                            light_b += lightValue * intensity.getZ();
                        }
                        k++;
                    }

                    // Multiplication de la couleur par l'éclairage
                    int r, g, b;
                    r = (int) (color.getRed() * light_r);
                    g = (int) (color.getGreen() * light_g);
                    b = (int) (color.getBlue() * light_b);

                    // Ecriture du pixel dans le buffer
                    finalBufferPixels[j] = new Color(
                            Math.min(255, Math.max(0, r)),
                            Math.min(255, Math.max(0, g)),
                            Math.min(255, Math.max(0, b))
                    ).hashCode();
                }
            });
            threads.addElement(t);
            t.start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void setResolution(int canvasWidth, int canvasHeight) {
        if (SceneManager.canvasWidth == canvasWidth && SceneManager.canvasHeight == canvasHeight)
            return;

        SceneManager.canvasWidth = canvasWidth;
        SceneManager.canvasHeight = canvasHeight;
        recreateBuffers();
    }

    public static double getRenderScale() {
        return renderScale;
    }

    public static void setRenderScale(double renderScale) {
        SceneManager.renderScale = renderScale;
        if (canvasWidth != 0 && canvasHeight != 0) // Emepeche la création des buffers si la fenetre n'a pas été initialisée
            recreateBuffers();
    }

    private static void recreateBuffers() {
        SceneManager.width = (int)(canvasWidth * renderScale);
        SceneManager.height = (int)(canvasHeight * renderScale);
        colorBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        normalsBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        finalBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public static void setMousePosition(Vector2D mousePosition) {
        SceneManager.mousePosition = mousePosition;
    }
}
