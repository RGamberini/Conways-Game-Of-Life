package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.scene.Node;
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
    public BooleanProperty borderLoop;
    public IntegerProperty size;

    //Timer stuff
    public BooleanProperty playing;
    public Timeline timer;
    public IntegerProperty time;

    public ObjectProperty<Color> aliveColor, deadColor, activeColor;
    public ObjectProperty<State> currentState;

    public GameOfLifeDisplay(Board model) {
        super();
        this.model = model;

        aliveColor = new SimpleObjectProperty<>(Color.BLACK);
        deadColor = new SimpleObjectProperty<>(Color.WHITE);
        activeColor = new SimpleObjectProperty<>(Color.ALICEBLUE);

        model.size.addListener(this::resize);
        this.setGridLinesVisible(true);
        this.resize(model.size, model.size.get(), model.size.get());


        //Timer stuff
        timer = new Timeline();
        timer.setCycleCount(Animation.INDEFINITE);

        time = new SimpleIntegerProperty();
        time.addListener((o, oldVal, newVal) -> {
            timer.getKeyFrames().clear();
            int duration = (newVal.intValue() != 0) ? Math.abs(250 / newVal.intValue()) : 0;
            timer.getKeyFrames().add(new KeyFrame(Duration.millis(duration), step -> {
                if (newVal.intValue() > 0) model.step();
                else model.stepBack();
            }));
        });
        time.setValue(1);

        borderLoop = model.rule.borderLoop;
        size = model.size;
        currentState = model.currentState;

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
    }

    public void resize(Observable o, Number oldVal, Number newVal) {
        this.setGridLinesVisible(false);
        this.getChildren().clear();
        int size = newVal.intValue();
        cellArray = new Rectangle[size][size];
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
        this.setGridLinesVisible(true);
        this.update(model.currentState.get());
    }

    public void update(State state) {
        boolean[][] booleanState = state.getState();
        for (int x = 0; x < booleanState.length; x++) {
            for (int y = 0; y < booleanState[x].length; y++) {
                try {
                    if (booleanState[x][y]) cellArray[x][y].fillProperty().bind(aliveColor);
                    else cellArray[x][y].fillProperty().bind(deadColor);

                    Point p = new Point(x, y);
                    if (state.activeCells.contains(p) && !state.get(p))
                        cellArray[x][y].fillProperty().bind(activeColor);
                } catch (ArrayIndexOutOfBoundsException ignored) {}
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
