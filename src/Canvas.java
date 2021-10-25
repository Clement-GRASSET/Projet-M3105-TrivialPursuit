import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    long lastFrame, newFrame;
    String frameRate;
    double compteur;

    Canvas() {
        frameRate = "";
        compteur = 0.0;
        lastFrame = System.nanoTime();
    }

    private void gameLoop(double frameTime) {
        frameRate = String.format("%,.0f", 1.0/frameTime) + " FPS";
        compteur+=frameTime;
    }

    private void draw(Graphics2D g) {
        long systemTime = (long) (System.nanoTime()/1000000000.0);
        g.drawString(frameRate, 10, 20);
        g.drawString("Temps passé depuis l'ouverture du jeu : " + String.format("%.2f", compteur) + "s.", 10, 50);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        newFrame = System.nanoTime();
        long deltaTime = newFrame-lastFrame;
        gameLoop(deltaTime/1000000000.0);
        draw((Graphics2D) g);
        lastFrame = newFrame;
        repaint();
    }
}
