package sample;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * GOL Rules.
 */
public class GameOfLife extends Rule {
    public GameOfLife() {
        borderLoop = new SimpleBooleanProperty(true);
    }
    @Override
    public boolean handleActive(State state, Point activeCell) {
        Point[] neighbors = state.getNeighbors(activeCell);
        int aliveCount = 0;

        for (int i = 0; i < neighbors.length; i++)
            if (state.get(neighbors[i])) aliveCount++;

        //If the cell is alive
        if (state.get(activeCell)) {
            //Death by starvation or overpopulation
            return !(aliveCount < 2 || aliveCount > 3);
        //A dead cell with three neighbors comes alive
        } else if (aliveCount == 3) return true;

        //Otherwise nothing is changed
        return state.get(activeCell);
    }
}
