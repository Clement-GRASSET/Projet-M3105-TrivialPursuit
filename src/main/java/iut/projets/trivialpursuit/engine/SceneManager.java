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
        final DirectionalLight directionalLight;
        final double intensityAtFlatNormal;

        DirectionalLightToRender(DirectionalLight directionalLight) {
            this.directionalLight = directionalLight;
            this.intensityAtFlatNormal = Vector3D.dot(directionalLight.getDirection(), new Vector3D(0, 0, -1));
        }
    }

    private static class PointLightToRender {
        final PointLight pointLight;
        final int x, y, radius;
        PointLightToRender(PointLight pointLight) {
            this.pointLight = pointLight;
            Vector2D screenPosition = activeScene.sceneToScreenCoordinates(pointLight.getPosition());
            x = (int) screenPosition.getX();
            y = (int) screenPosition.getY();
            radius = (int)(pointLight.getRadius() * height/100 * activeScene.getCamera().getZoom());
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
            actorsToDraw.add( new ActorToDraw(actor) );
        }

        Thread colorThread = drawColorBuffer(camera, activeScene, actorsToDraw);
        Thread normalThread = drawNormalBufferImage(camera, activeScene, actorsToDraw);
        try {
            normalThread.join();
            colorThread.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        drawFinalBuffer(activeScene);

        g.drawImage(finalBuffer, 0, 0, canvasWidth, canvasHeight, null);
    }

    private static Thread drawColorBuffer(Camera camera, Scene scene, ArrayList<ActorToDraw> actorsToDraw) {
        Thread thread = new Thread(() -> {
            double unit = camera.getZoom()*height/100.0;
            Graphics2D g = (Graphics2D) colorBuffer.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(scene.getBackgroundColor());
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

    private static Thread drawNormalBufferImage(Camera camera, Scene scene, ArrayList<ActorToDraw> actorsToDraw) {
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

    private static void drawFinalBuffer(Scene scene) {
        int nbThreads = Runtime.getRuntime().availableProcessors();
        Vector<Thread> threads = new Vector<>();
        int length = width*height/nbThreads;

        // Récupération des pixels par reference de tous les buffers
        int [] colorBufferPixels = ( (DataBufferInt) colorBuffer.getRaster().getDataBuffer() ).getData();
        int [] normalsBufferPixels = ( (DataBufferInt) normalsBuffer.getRaster().getDataBuffer() ).getData();
        int [] finalBufferPixels = ( (DataBufferInt) finalBuffer.getRaster().getDataBuffer() ).getData();

        // Préparation des lumières
        List<DirectionalLightToRender> directionalLights = new ArrayList<>();
        for (DirectionalLight directionalLight : scene.getDirectionalLights()) {
            directionalLights.add(new DirectionalLightToRender(directionalLight));
        }
        int nbDirectionalLights = directionalLights.size();
        List<PointLightToRender> pointLights = new ArrayList<>();
        for (PointLight pointLight : scene.getPointLights()) {
            pointLights.add(new PointLightToRender(pointLight));
        }
        int nbPointLights = pointLights.size();

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

                    // Création d'un vecteur de longueur 1 représentant la direction du pixel
                    Vector3D pixel_angle;
                    if (isPixelFlat) {
                        pixel_angle = new Vector3D(0,0,1);
                    } else {
                        pixel_angle = new Vector3D(
                                (normals.getRed()/255.0 - 0.5) * 2,
                                (normals.getGreen()/255.0 - 0.5) * -2,
                                (normals.getBlue()/255.0 - 0.5) * 2
                        );
                    }


                    // Eclairage du pixel
                    Vector3D lightColor = new Vector3D(0,0,0);

                    for (int k = 0; k < nbDirectionalLights; k++) {

                        DirectionalLightToRender light = directionalLights.get(k);

                        double lightValue = 0;

                        if (isPixelFlat)
                            lightValue = light.intensityAtFlatNormal;  // Si le pixel est "plat", on utilise la valeur déja calculée
                        else {
                            // Direction inversée de la lumière
                            Vector3D direction = new Vector3D(
                                    light.directionalLight.getDirection().getX()*-1,
                                    light.directionalLight.getDirection().getY()*-1,
                                    light.directionalLight.getDirection().getZ()*-1
                            );

                            // Quantité de lumière sur le pixel en fonction des angles (Produit scalaire de pixel_angle et direction)
                            lightValue = Math.max(Vector3D.dot(pixel_angle, direction), 0);
                        }

                        Vector3D intensity = light.directionalLight.getIntensity();  // Inensité (en %) de la lumière (x=r, y=g, z=b)
                        // Ajout de la lumière calculée (lightValue multiplié par la couleur de la lumière) au total de l'éclairage du pixel
                        lightColor.add(new Vector3D(
                                lightValue * intensity.getX(),
                                lightValue * intensity.getY(),
                                lightValue * intensity.getZ()
                        ));
                    }

                    for (int k = 0; k < nbPointLights; k++) {
                        PointLightToRender light = pointLights.get(k);
                        int screenPositionX = light.x;
                        int screenPositionY = light.y;
                        double distance = Math.sqrt((x-screenPositionX)*(x-screenPositionX) + (y-screenPositionY)*(y-screenPositionY))/light.radius;
                        if (distance < 1) {
                            double lightValue = 1;

                            Vector3D direction = Vector3D.normalize( new Vector3D(
                                    screenPositionX-x, screenPositionY-y, height/100.0
                            ));
                            lightValue = Math.max(Vector3D.dot(pixel_angle, direction), 0) * (1-distance);

                            Vector3D intensity = light.pointLight.getIntensity();  // Inensité (en %) de la lumière (x=r, y=g, z=b)
                            // Ajout de la lumière calculée (lightValue multiplié par la couleur de la lumière) au total de l'éclairage du pixel
                            lightColor.add(new Vector3D(
                                    lightValue * intensity.getX(),
                                    lightValue * intensity.getY(),
                                    lightValue * intensity.getZ()
                            ));
                        }

                    }

                    // Multiplication de la couleur par l'éclairage
                    int r, g, b;
                    r = (int) (color.getRed() * lightColor.getX());
                    g = (int) (color.getGreen() * lightColor.getY());
                    b = (int) (color.getBlue() * lightColor.getZ());

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
