package sample;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Stack;

/**
 * Data representation of the GOL board.
 */
class Board {
    public final IntegerProperty size;
    public final Rule rule;
    //public State currentState;
    private final Stack<State> pastStates;
    public ObjectProperty<State> currentState;

    private Board(int size, Rule rule) {
        this.size = new SimpleIntegerProperty(size);
        this.rule = rule;
        this.currentState = new SimpleObjectProperty<>(new State(this));
        pastStates = new Stack<>();
        this.size.addListener(this::resize);
    }

    public Board(int size, Rule rule, boolean[][] initialState) {
        this(size, rule);
        this.currentState = new SimpleObjectProperty<>(new State(this, initialState));
    }

    private void resize(Observable o, Number oldVal, Number newVal) {
        int newSize = newVal.intValue(), oldSize = oldVal.intValue();
        boolean[][] newState = new boolean[newSize][newSize];
        for (int x = 0; x < newSize; x++) {
            for (int y = 0; y < newSize; y++) {
                Point p = new Point(x, y);
                if (x < oldSize && y < oldSize)
                    newState[x][y] = this.currentState.get().get(p);
                else newState[x][y] = false;
            }
        }
        currentState.setValue(new State(this, newState));
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
