package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.engine.graphics.SceneRenderer;
import iut.projets.trivialpursuit.engine.window.RenderCanvas;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameLoop extends Thread {

    private final SceneRenderer sceneRenderer;
    private final RenderCanvas canvas;
    private long minFrameLength;

    GameLoop() {
        sceneRenderer = Engine.getSceneRenderer();
        canvas = Engine.getGameWindow().getCanvas();
        minFrameLength = 0;
        start();
    }

    @Override
    public void run() {
        super.run();
        long lastFrame = System.nanoTime();
        long newFrame;
        //noinspection InfiniteLoopStatement
        while (true) {
            newFrame = System.nanoTime();
            double frametime = ((double) newFrame - lastFrame)/1000000000;
            lastFrame = newFrame;
            update(frametime);
            while (System.nanoTime() < newFrame + minFrameLength) {}
        }
    }

    private void update(double frameTime) {
        Engine.getActiveScene().tick(frameTime);
        render();
    }

    private void render() {
        sceneRenderer.setResolution(canvas.getWidth(), canvas.getHeight());
        try {
            sceneRenderer.render(Engine.getActiveScene());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }

        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(sceneRenderer.getBuffer(), 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        g.dispose();
        bufferStrategy.show();
    }

    public void setMaxFPS(int fps) {
        if (fps > 0)
            minFrameLength = (long) 1000000000.0/fps;
        else
            minFrameLength = 0;
    }
}
