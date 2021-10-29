package iut.projets.trivialpursuit.engine.graphics;

import iut.projets.trivialpursuit.engine.types.Rotation;
import iut.projets.trivialpursuit.engine.types.Vector2D;
import iut.projets.trivialpursuit.engine.types.Vector3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Vector;

public class SceneRenderer {

    private class ActorToDraw {

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

        public void drawColor(Graphics2D g) {
            g.translate(translation.getX(), translation.getY());
            g.rotate(r, w/2, h/2);
            g.drawImage(actor.getMaterial().getColor(), 0, 0, (int)w, (int)h, null);
            g.rotate(-r, w/2, h/2);
            g.translate(-translation.getX(), -translation.getY());
        }

        public void drawNormal(Graphics2D g) {
            g.translate(translation.getX(), translation.getY());
            g.rotate(r, w/2, h/2);
            g.drawImage(actor.getMaterial().getNormals(), 0, 0, (int)w, (int)h, null);
            g.rotate(-r, w/2, h/2);
            g.translate(-translation.getX(), -translation.getY());
        }

    }

    int width, height, canvasWidth, canvasHeight;
    double renderScale;

    BufferedImage colorBuffer;
    BufferedImage normalsBuffer;
    BufferedImage finalBuffer;

    public SceneRenderer() {
        canvasWidth = 0;
        canvasHeight = 0;
        width = 0;
        height = 0;
        renderScale = 1;
    }

    public void render(Graphics g, Scene scene) {

        Camera camera = scene.getCamera();

        ArrayList<ActorToDraw> actorsToDraw = new ArrayList<>();
        for (Actor actor : scene.getActors()) {
            actorsToDraw.add( new ActorToDraw(actor) );
        }

        Thread colorThread = drawColorBuffer(camera, scene, actorsToDraw);
        Thread normalThread = drawNormalBufferImage(camera, scene, actorsToDraw);
        try {
            normalThread.join();
            colorThread.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        drawFinalBuffer(scene);

        g.drawImage(finalBuffer, 0, 0, canvasWidth, canvasHeight, null);
    }

    private Thread drawColorBuffer(Camera camera, Scene scene, ArrayList<ActorToDraw> actorsToDraw) {
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

    private Thread drawNormalBufferImage(Camera camera, Scene scene, ArrayList<ActorToDraw> actorsToDraw) {
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

    private void drawFinalBuffer(Scene scene) {
        int nbThreads = Runtime.getRuntime().availableProcessors();
        Vector<Thread> threads = new Vector<>();
        int length = width*height/nbThreads;

        // Récupération des pixels par reference de tous les buffers
        int [] colorBufferPixels = ( (DataBufferInt) colorBuffer.getRaster().getDataBuffer() ).getData();
        int [] normalsBufferPixels = ( (DataBufferInt) normalsBuffer.getRaster().getDataBuffer() ).getData();
        int [] finalBufferPixels = ( (DataBufferInt) finalBuffer.getRaster().getDataBuffer() ).getData();

        // Préparation des lumières
        ArrayList<DirectionalLight> directionalLights = scene.getLights();
        int nbLights = directionalLights.size();

        // Création de threads (1 par CPU) qui vont traiter chacuns une partie de l'image
        for (int i = 0; i < nbThreads; i++) {
            int start = i * length;                                 // Début de la zone de l'image à traiter
            int end = (i+1 == length) ? length-1 : (i+1)*length;    // Fin de la zone de l'image à traiter
            Thread t = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    // Coordonnées x et y du pixel j
                    // int x = j%width;
                    // int y = j/width;

                    // Récupération de la couleur et de l'angle du pixel
                    Color color = new Color(colorBufferPixels[j]);
                    Color normals = new Color(normalsBufferPixels[j]);

                    // Création d'un vecteur de longueur 1 représentant la direction du pixel
                    Vector3D pixel_angle = new Vector3D(
                            (normals.getRed()/255.0 - 0.5) * 2,
                            (normals.getGreen()/255.0 - 0.5) * 2,
                            (normals.getBlue()/255.0 - 0.5) * 2
                    );

                    // Eclairage du pixel
                    Vector3D lightColor = new Vector3D(0,0,0);

                    for (int k = 0; k < nbLights; k++) {

                        DirectionalLight light = directionalLights.get(k);

                        Vector3D direction = new Vector3D(          // Direction inversée de la lumière
                                light.getDirection().getX()*-1,
                                light.getDirection().getY(),
                                light.getDirection().getZ()*-1
                        );
                        Vector3D intensity = light.getIntensity();  // Inensité (en %) de la lumière (x=r, y=g, z=b)

                        // Quantité de lumière sur le pixel en fonction des angles (Produit scalaire de pixel_angle et direction)
                        double lightValue = Math.max(Vector3D.dot(pixel_angle, direction), 0);

                        // Ajout de la lumière calculée (lightValue multiplié par la couleur de la lumière) au total de l'éclairage du pixel
                        lightColor.add(new Vector3D(
                                lightValue * intensity.getX(),
                                lightValue * intensity.getY(),
                                lightValue * intensity.getZ()
                        ));
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

    public void setResolution(int canvasWidth, int canvasHeight) {
        if (this.canvasWidth == canvasWidth && this.canvasHeight == canvasHeight)
            return;

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        recreateBuffers();
    }

    public void setRenderScale(double renderScale) {
        this.renderScale = renderScale;
        if (canvasWidth != 0 && canvasHeight != 0) // Emepeche la création des buffers si la fenetre n'a pas été initialisée
            recreateBuffers();
    }

    private void recreateBuffers() {
        this.width = (int)(canvasWidth * renderScale);
        this.height = (int)(canvasHeight * renderScale);
        colorBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        normalsBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        finalBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
}
