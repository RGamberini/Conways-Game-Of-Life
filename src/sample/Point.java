package sample;

/**
 * Created by Rudy Gamberini on 1/19/2016.
 */
public class Point extends java.awt.Point {
    public Point() {
        super();
    }

    public Point(java.awt.Point p) {
        super(p);
    }

    public Point(int x, int y) {
        super(x, y);
    }

    public void addWithLoop(java.awt.Point point, int limit) {
        x = (point.x + this.x + limit) % limit;
        y = (point.y + this.y + limit) % limit;
    }

    public void addNoLoop(java.awt.Point point) {
        x = this.x + point.x;
        y = this.y + point.y;
    }
}
