package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.window.RenderCanvas;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Classe qui contient la boucle infinie du jeu.
 * Fonctionne sur un nouveau thread.
 */
public class GameLoop extends Thread {

    private final RenderCanvas canvas;
    private final Game game;
    private long minFrameLength;
    private double frameTime;
    private double sceneTick, UITick, sceneRender, UIRender, total;
    private boolean debug;

    /**
     * Construit un GameLoop
     * @param game Le jeu à boucler
     */
    GameLoop(Game game) {
        canvas = Game.getWindow().getCanvas();
        this.game = game;
        minFrameLength = 0;
        debug = false;
    }

    @Override
    public void run() {
        super.run();
        game.start();
        long lastFrame = System.nanoTime();
        long newFrame;
        //noinspection InfiniteLoopStatement
        while (true) {
            newFrame = System.nanoTime();
            frameTime = (double)(newFrame-lastFrame)/1000000000;
            lastFrame = newFrame;
            update();
            //noinspection StatementWithEmptyBody
            while (System.nanoTime() < newFrame + minFrameLength) {}
        }
    }

    /**
     * Gère toutes les opérations à faire en une pendant une image :
     * Actualisation de la scène et de l'interface utilisateur
     * Rendu de la scène et de l'interface utilisateur
     */
    private void update() {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        long t0 = System.nanoTime();

        SceneManager.tick(frameTime);
        long t1 = System.nanoTime();

        UIManager.setScreenSize(canvas.getWidth(), canvas.getHeight());
        UIManager.tick(frameTime);
        long t2 = System.nanoTime();

        SceneManager.setResolution(canvas.getWidth(), canvas.getHeight());
        SceneManager.render(g);

        long t3 = System.nanoTime();

        UIManager.render(g);
        long t4 = System.nanoTime();

        sceneTick = (t1-t0)*0.000001;
        UITick = (t2-t1)*0.000001;
        sceneRender = (t3-t2)*0.000001;
        UIRender = (t4-t3)*0.000001;
        total = (t4-t0)*0.000001;

        if (debug)
            drawDebug(g);

        g.dispose();
        bufferStrategy.show();

    }

    /**
     * @return Le nombre maximum d'images par seconde du jeu. 0 si infini.
     */
    public int getMaxFPS() {
        return (minFrameLength == 0) ? 0 : (int) (1000000000.0/minFrameLength);
    }

    /**
     * @param fps Définit le nombre maximum d'images par seconde du jeu, supérieur à 0. 0 si infini.
     */
    public void setMaxFPS(int fps) {
        if (fps > 0)
            minFrameLength = (long) 1000000000.0/fps;
        else
            minFrameLength = 0;
    }

    /**
     * Affiche le temps d'exécution de l'actualisation et du rendu de la scène et de l'interface utilisateur.
     * @param g Contexte graphique où dessiner.
     */
    private void drawDebug(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 150, 230, 150);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("FPS: " + String.format("%,.0f", 1/frameTime), 10, 180);
        g.drawString("Frametime: " + String.format("%,.3f", frameTime*1000) + " ms", 10, 200);
        g.drawString("Scene tick: " + String.format("%,.3f",sceneTick) + " ms", 10, 220);
        g.drawString("UI tick: " + String.format("%,.3f",UITick) + " ms", 10, 240);
        g.drawString("Scene render : " + String.format("%,.3f",sceneRender) + " ms", 10, 260);
        g.drawString("UI render: " + String.format("%,.3f",UIRender) + " ms", 10, 280);
    }

    /**
     * @param debug Active ou non l'affichage des temps d'exécution.
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
