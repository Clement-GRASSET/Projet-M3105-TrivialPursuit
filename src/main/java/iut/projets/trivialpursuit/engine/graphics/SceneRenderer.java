package iut.projets.trivialpursuit.engine.graphics;

import iut.projets.trivialpursuit.engine.types.Vector3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Vector;

public class SceneRenderer {

    private class ActorToDraw {

        Actor actor;
        int x, y, w, h;

        ActorToDraw(Actor actor) {
            this.actor = actor;

            double xPosition = width/2.0 + actor.getPosition().getX()*unit;
            double yPosition = height/2.0 + actor.getPosition().getY()*unit;

            double xScale = actor.getScale().getX()*unit;
            double yScale = actor.getScale().getY()*unit;

            x = (int) (xPosition - xScale/2.0);
            y = (int) (yPosition - yScale/2.0);
            w = (int) (xScale);
            h = (int) (yScale);
        }

        public boolean isVisible() {
            return (x < width && y < height && x+w > 0 && y+h > 0);
        }

        public void drawColor() {
            if (isVisible()) {
                Graphics2D g = (Graphics2D) colorBuffer.getGraphics();
                g.drawImage(actor.getMaterial().getColor(), x, y, w, h, null);
            }
        }

        public void drawNormal() {
            if (isVisible()) {
                Graphics2D g = (Graphics2D) normalsBuffer.getGraphics();
                g.drawImage(actor.getMaterial().getNormals(), x, y, w, h, null);
            }
        }

    }

    int width, height;
    private double unit;

    BufferedImage colorBuffer;
    BufferedImage normalsBuffer;
    BufferedImage finalBuffer;

    public SceneRenderer() {
        width = 0;
        height = 0;
        unit = 0;
    }

    public void render(Graphics g, Scene scene) {

        unit = height/100.0;

        ArrayList<ActorToDraw> actorsToDraw = new ArrayList<>();
        for (Actor actor : scene.getActors()) {
            actorsToDraw.add( new ActorToDraw(actor) );
        }

        Thread colorThread = drawColorBuffer(scene, actorsToDraw);
        Thread normalThread = drawNormalBufferImage(scene, actorsToDraw);
        try {
            normalThread.join();
            colorThread.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        drawFinalBuffer(scene);

        g.drawImage(finalBuffer, 0, 0, width, height, null);
    }

    private Thread drawColorBuffer(Scene scene, ArrayList<ActorToDraw> actorsToDraw) {
        Thread thread = new Thread(() -> {
            Graphics2D g = (Graphics2D) colorBuffer.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(scene.getBackgroundColor());
            g.fillRect(0, 0, width, height);

            actorsToDraw.forEach(ActorToDraw::drawColor);

            g.dispose();
        });
        thread.start();
        return thread;
    }

    private Thread drawNormalBufferImage(Scene scene, ArrayList<ActorToDraw> actorsToDraw) {
        Thread thread = new Thread(() -> {
            Graphics2D g = (Graphics2D) normalsBuffer.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(new Color(127, 127, 255));
            g.fillRect(0, 0, width, height);

            actorsToDraw.forEach(ActorToDraw::drawNormal);

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

    public void setResolution(int width, int height) {
        if (this.width == width && this.height == height)
            return;

        this.width = width;
        this.height = height;
        colorBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        normalsBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        finalBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
}
