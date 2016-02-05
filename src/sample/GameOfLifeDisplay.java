package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Created by Rudy Gamberini on 1/25/2016.
 */
public class GameOfLifeDisplay extends GridPane{
    private Rectangle[][] cellArray;
    private Board model;

    //Timer stuff
    public BooleanProperty playing;
    public Timeline timer;
    public ObjectProperty<Duration> time;

    public ObjectProperty<Color> aliveColor, deadColor, activeColor;

    public GameOfLifeDisplay(Board model) {
        super();
        this.model = model;
        int size = model.size;
        cellArray = new Rectangle[size][size];

        aliveColor = new SimpleObjectProperty<>(Color.BLACK);
        deadColor = new SimpleObjectProperty<>(Color.WHITE);
        activeColor = new SimpleObjectProperty<>(Color.ALICEBLUE);

        //Initiate the graphical array of panes
        for (int x = 0; x < size; x++) {
            this.addColumn(0);
            for (int y = 0; y < size; y++) {
                this.addRow(0);
                Rectangle cell = new Rectangle();
                cell.setHeight(22);
                cell.setWidth(22);
                cell.fillProperty().bind(deadColor);
                cellArray[x][y] = cell;
                this.add(cell, x, y);
            }
        }
        //Timer stuff
        time = new SimpleObjectProperty<>(Duration.millis(250));
        timer = new Timeline(new KeyFrame(time.get(), step -> model.step()));
        timer.setCycleCount(Animation.INDEFINITE);

        //Property Stuff
        model.currentState.addListener((observable, oldValue, newValue) -> {
            this.update(newValue);
        });
        update(model.currentState.get());

        playing = new SimpleBooleanProperty(false);
        playing.addListener(this::playingChange);

        this.setOnMouseClicked((event) -> {
            int x = (int) (event.getX() / 22), y = (int) (event.getY() / 22);
            Point p = new Point(x, y);
            model.currentState.get().set(p, !model.get(p));
            this.update(model.currentState.get());
        });

        this.setGridLinesVisible(true);
    }

    public void update(State state) {
        boolean[][] booleanState = state.getState();
        for (int x = 0; x < booleanState.length; x++) {
            for (int y = 0; y < booleanState[x].length; y++) {
                if (booleanState[x][y]) cellArray[x][y].fillProperty().bind(aliveColor);
                else cellArray[x][y].fillProperty().bind(deadColor);

                Point p = new Point(x, y);
                if (state.activeCells.contains(p) && !state.get(p))
                    cellArray[x][y].fillProperty().bind(activeColor);
            }
        }
    }

    // Property Change Listeners
    public void playingChange(Observable observable, boolean oldVal, boolean newVal) {
        if (newVal) timer.play();
        else timer.stop();
    }

    public void step() {
        model.step();
    }

    public void stepBack() {
        model.stepBack();
    }
}
