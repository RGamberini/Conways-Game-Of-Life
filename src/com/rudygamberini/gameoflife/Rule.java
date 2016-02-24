package com.rudygamberini.gameoflife;

import javafx.beans.property.BooleanProperty;

/**
 * Obviously a base Rule class.
 */
public abstract class Rule {
    public BooleanProperty borderLoop;
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
