package fr.trivialpursuit.engine.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.PixelGrabber;
import java.util.ArrayList;
import java.util.Vector;
import java.util.function.BiFunction;

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

    private class Buffer {

        int w, h;
        Vector3D [] values;

        Buffer(int width, int height) {
            w = width;
            h = height;
            values = new Vector3D[w*h];
        }

        public Vector3D getPixel(int x, int y) {
            return values[y*width+x];
        }

        public void setPixel(int x, int y, Vector3D value) {
            values[y*width+x] = value;
        }

        public void forEach(BiFunction<Integer, Integer, Void> f) {
            int nbThreads = Runtime.getRuntime().availableProcessors();
            Vector<Thread> threads = new Vector<>();
            int length = values.length/nbThreads;

            for (int i = 0; i < nbThreads; i++) {

                int start = i * length;
                int end = (i+1 == length) ? length-1 : (i+1)*length;
                Thread t = new Thread(() -> {
                    for (int j = start; j < end; j++) {
                        int x = j%width;
                        int y = j/width;
                        f.apply(x, y);
                    }
                });
                threads.addElement(t);
                t.start();

                //System.out.println("length: " + values.length + ", i: " + i + ", start: " + start + ", end: " + end);
            }
            try {
                for (Thread thread : threads) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
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
        colorBuffer = null;
        normalsBuffer = null;
    }

    public void render(Scene scene) throws InterruptedException {

        unit = height/100.0;

        Vector<ActorToDraw> actorsToDraw = new Vector<>();
        for (Actor actor : scene.getActors()) {
            actorsToDraw.addElement( new ActorToDraw(actor) );
        }

        Thread colorThread = drawColorBuffer(scene, actorsToDraw);
        Thread normalThread = drawNormalBufferImage(scene, actorsToDraw);
        normalThread.join();
        colorThread.join();

        drawFinalBuffer(scene);
    }

    public BufferedImage getBuffer() {
        return finalBuffer;
    }

    private Thread drawColorBuffer(Scene scene, Vector<ActorToDraw> actorsToDraw) {
        Thread thread = new Thread(() -> {
            Graphics2D g = (Graphics2D) colorBuffer.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(scene.getBackgroundColor());
            g.fillRect(0, 0, width, height);

            actorsToDraw.forEach(ActorToDraw::drawColor);
        });
        thread.start();
        return thread;
    }

    private Thread drawNormalBufferImage(Scene scene, Vector<ActorToDraw> actorsToDraw) {
        Thread thread = new Thread(() -> {
            Graphics2D g = (Graphics2D) normalsBuffer.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(new Color(127, 127, 255));
            g.fillRect(0, 0, width, height);

            actorsToDraw.forEach(ActorToDraw::drawNormal);
        });
        thread.start();
        return thread;
    }

    private void drawFinalBuffer(Scene scene) {
        int nbThreads = Runtime.getRuntime().availableProcessors();
        Vector<Thread> threads = new Vector<>();
        int length = width*height/nbThreads;

        int [] colorBufferPixels = ( (DataBufferInt) colorBuffer.getRaster().getDataBuffer() ).getData();
        int [] normalsBufferPixels = ( (DataBufferInt) normalsBuffer.getRaster().getDataBuffer() ).getData();
        int [] finalBufferPixels = ( (DataBufferInt) finalBuffer.getRaster().getDataBuffer() ).getData();
        ArrayList<DirectionalLight> directionalLights = scene.getLights();
        int nbLights = directionalLights.size();

        for (int i = 0; i < nbThreads; i++) {
            int start = i * length;
            int end = (i+1 == length) ? length-1 : (i+1)*length;
            Thread t = new Thread(() -> {
                for (int j = start; j < end; j++) {
//                    int x = j%width;
//                    int y = j/width;

                    // Récupération de la couleur et de l'angle du pixel
                    Color color = new Color(colorBufferPixels[j]);
                    Color normals = new Color(normalsBufferPixels[j]);
                    Vector3D pixel_angle = new Vector3D(
                            (normals.getRed()/255.0 - 0.5) * 2,
                            (normals.getGreen()/255.0 - 0.5) * 2,
                            (normals.getBlue()/255.0 - 0.5) * 2
                    );

                    // Eclairage du pixel
                    Vector3D lightColor = new Vector3D(0,0,0);
                    //Vector3D lightColor = new Vector3D(1,1,1);
                    for (int k = 0; k < nbLights; k++) {
                        DirectionalLight light = directionalLights.get(k);
                        Vector3D direction = light.getDirection().multiply(-1);
                        Vector3D intensity = light.getIntensity();
                        double lightValue = pixel_angle.dot(direction);
                        lightColor = lightColor.add(new Vector3D(
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
//                    r = (int) (light.getX() * 255);
//                    g = (int) (light.getY() * 255);
//                    b = (int) (light.getZ() * 255);

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
