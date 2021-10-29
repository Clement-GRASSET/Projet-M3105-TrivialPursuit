package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.graphics.SceneRenderer;
import iut.projets.trivialpursuit.engine.userinterface.UserInterface;
import iut.projets.trivialpursuit.engine.window.RenderCanvas;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameLoop extends Thread {

    private final SceneRenderer sceneRenderer;
    private final UserInterface userInterface;
    private final RenderCanvas canvas;
    private final Game game;
    private long minFrameLength;
    private double frameTime;
    private double sceneTick, UITick, sceneRender, UIRender, total;
    private boolean debug;

    GameLoop(Game game) {
        sceneRenderer = Engine.getSceneRenderer();
        userInterface = Engine.getUserInterface();
        canvas = Engine.getGameWindow().getCanvas();
        this.game = game;
        minFrameLength = 0;
        debug = false;
    }

    @Override
    public void run() {
        super.run();
        long lastFrame = System.nanoTime();
        long newFrame;
        game.start();
        //noinspection InfiniteLoopStatement
        while (true) {
            newFrame = System.nanoTime();
            frameTime = (double)(newFrame-lastFrame)/1000000000;
            lastFrame = newFrame;
            update();
            while (System.nanoTime() < newFrame + minFrameLength) {}
        }
    }

    private void update() {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        long t0 = System.nanoTime();

        Engine.getActiveScene().tick(frameTime);
        long t1 = System.nanoTime();

        userInterface.setScreenSize(canvas.getWidth(), canvas.getHeight());
        userInterface.tick(frameTime);
        long t2 = System.nanoTime();

        sceneRenderer.setResolution(canvas.getWidth(), canvas.getHeight());
        sceneRenderer.render(g, Engine.getActiveScene());

        long t3 = System.nanoTime();

        userInterface.render(g);
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

    public void setMaxFPS(int fps) {
        if (fps > 0)
            minFrameLength = (long) 1000000000.0/fps;
        else
            minFrameLength = 0;
    }

    private void drawDebug(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 150, 230, 170);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("FPS: " + String.format("%,.0f", 1/frameTime), 10, 180);
        g.drawString("Frametime: " + String.format("%,.3f", frameTime*1000) + " ms", 10, 200);
        g.drawString("Scene tick: " + String.format("%,.3f",sceneTick) + " ms", 10, 220);
        g.drawString("UI tick: " + String.format("%,.3f",UITick) + " ms", 10, 240);
        g.drawString("Scene render : " + String.format("%,.3f",sceneRender) + " ms", 10, 260);
        g.drawString("UI render: " + String.format("%,.3f",UIRender) + " ms", 10, 280);
        g.drawString("Lost: " + String.format("%,.3f",(frameTime*1000)-total) + " ms", 10, 300);
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
