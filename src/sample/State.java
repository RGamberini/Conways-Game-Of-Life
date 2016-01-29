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
        this(board.size, board.rule, initialState);
    }

    public State(Board board) {
        this(board.size, board.rule);
    }

    public Point[] getNeighbors(Point center) {
        Point[] result = new Point[rule.DIRECTIONS.length];
        for (int i = 0; i < rule.DIRECTIONS.length; i++) {
            Point direction = rule.DIRECTIONS[i];

            Point neighbor = new Point(center);
            neighbor.constrainedAdd(direction, this.size);
            result[i] = neighbor;
        }
        return result;
    }

    public boolean get(Point point) {
        return state[point.x][point.y];
    }

    public void set(Point point, boolean value) {
        if (value) {
            activeCells.add(point);
            activeCells.addAll(Arrays.asList(getNeighbors(point)));
        }

        this.state[point.x][point.y] = value;
    }

    public boolean[][] getState() {
        //No exterior meddling with the state
        return state.clone();
    }
}
