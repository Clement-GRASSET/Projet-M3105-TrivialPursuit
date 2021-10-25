package fr.trivialpursuit.engine.graphics;

public class PixelCoord {

    private int x, y;

    public PixelCoord(int x, int y) {
        this.x = Math.max(0, x);
        this.y = Math.max(0, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
