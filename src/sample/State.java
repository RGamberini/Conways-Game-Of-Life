package sample;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Rudy Gamberini on 1/20/2016.
 */
public class State {
    private boolean[][] state;
    public int size;
    public Rule rule;
    public HashSet<Point> activeCells;

    public State(int size, Rule rule) {
        this.state = new boolean[size][size];
        activeCells = new HashSet<>();
        this.size = size;
        this.rule = rule;
    }

    public State(int size, Rule rule, boolean[][] initialState) {
        this(size, rule);
        for (int x = 0; x < initialState.length; x++) {
            for (int y = 0; y < initialState[x].length; y++) {
                if (initialState[x][y]) set(new Point(x, y), true);
            }
        }
    }

    public State(Board board, boolean[][] initialState) {
        this(board.size.intValue(), board.rule, initialState);
    }

    public State(Board board) {
        this(board.size.intValue(), board.rule);
    }

    public State(State state, boolean[][] initialState) {
        this(state.size, state.rule, initialState);
    }

    public Point[] getNeighbors(Point center) {
        Point[] result = new Point[rule.DIRECTIONS.length];
        for (int i = 0; i < rule.DIRECTIONS.length; i++) {
            Point direction = rule.DIRECTIONS[i];

            Point neighbor = new Point(center);
            if (rule.borderLoop.get()) neighbor.addWithLoop(direction, this.size);
            else neighbor.addNoLoop(direction);
            result[i] = neighbor;
        }
        return result;
    }

    public boolean get(Point point) {
        return inBounds(point) && state[point.x][point.y];
    }

    public boolean inBounds(Point point) {
        return point.x < state.length && point.y < state[0].length && point.x >= 0 && point.y >= 0;
    }

    public void set(Point point, boolean value) {
        if (inBounds(point)) {
            if (value) {
                activeCells.add(point);
                activeCells.addAll(Arrays.asList(getNeighbors(point)));
            }

            this.state[point.x][point.y] = value;
        }
    }

    public boolean[][] getState() {
        //No exterior meddling with the state
        return state.clone();
    }
}
