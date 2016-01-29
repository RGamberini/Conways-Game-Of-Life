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

    public void constrainedAdd(java.awt.Point point, int limit) {
        x = (point.x + x + limit) % limit;
        y = (point.y + y + limit) % limit;
    }
}
