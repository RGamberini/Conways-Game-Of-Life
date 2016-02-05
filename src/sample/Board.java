package sample;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventType;

import java.util.*;

/**
 * Created by Rudy Gamberini on 1/19/2016.
 */
public class Board {
    public int size;
    public Rule rule;
    //public State currentState;
    private Stack<State> pastStates;
    public ObjectProperty<State> currentState;

    public Board(int size, Rule rule) {
        this.size = size;
        this.rule = rule;
        this.currentState = new SimpleObjectProperty<>(new State(this));
        pastStates = new Stack<>();
    }

    public Board(int size, Rule rule, boolean[][] initialState) {
        this(size, rule);
        this.currentState = new SimpleObjectProperty<>(new State(this, initialState));
    }


    public void step() {
        pastStates.add(currentState.get());
        HashSet<Point> activeCells = currentState.get().activeCells;
        State nextState = new State(this);
        for (Point activeCell: activeCells)
            nextState.set(activeCell, rule.handleActive(currentState.get(), activeCell));
        this.currentState.setValue(nextState);
    }

    public void stepBack() {
        try {
            this.currentState.setValue(pastStates.pop());
        } catch (EmptyStackException ignored) {}

    }

    public boolean get(Point point) {
        return this.currentState.get().get(point);
    }
}
