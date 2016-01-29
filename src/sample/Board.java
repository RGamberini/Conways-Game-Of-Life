package sample;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventType;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Rudy Gamberini on 1/19/2016.
 */
public class Board {
    public int size;
    public Rule rule;
    //public State currentState;
    public ObjectProperty<State> currentState;

    public Board(int size, Rule rule) {
        this.size = size;
        this.rule = rule;
        this.currentState = new SimpleObjectProperty<>(new State(this));
    }

    public Board(int size, Rule rule, boolean[][] initialState) {
        this(size, rule);
        this.currentState = new SimpleObjectProperty<>(new State(this, initialState));
    }


    public void step() {
        HashSet<Point> activeCells = currentState.get().activeCells;
        State nextState = new State(this);
        for (Point activeCell: activeCells)
            nextState.set(activeCell, rule.handleActive(currentState.get(), activeCell));
        this.currentState.setValue(nextState);
    }

    public boolean get(Point point) {
        return this.currentState.get().get(point);
    }

    public static void main(String[] args) {
//        Point one = new Point(0, 0);
//        Point two = new Point(-1, -1);
//        one.constrainedAdd(two, 25);
//
//        System.out.println(one);
        System.out.println(ImageParser.parseImage("blank.bmp"));
    }
}
