package sample;

import java.awt.*;

/**
 * Created by Rudy Gamberini on 1/19/2016.
 */
public abstract class Rule {
    public final Point[] DIRECTIONS = new Point[]{
            new Point(1, 0),
            new Point(-1, 0),
            new Point(0, 1),
            new Point(0, -1),
            new Point(1, 1),
            new Point(-1, -1),
            new Point(1, -1),
            new Point(-1, 1)
    };

    public abstract boolean handleActive(State state, Point activeCell);
}
