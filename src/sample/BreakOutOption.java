package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Button to make a new window.
 */
class BreakOutOption extends HBox {
    private final GameOfLifeDisplay display;

    public BreakOutOption(GameOfLifeDisplay display) {
        this.display = display;
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(0, 8, 0, 8));

        JFXButton breakout = new JFXButton("New Window");
        breakout.getStyleClass().add("breakout-button");
        this.getChildren().add(breakout);
        breakout.setOnMouseClicked(this::breakout);
    }

    private void breakout(Event event) {
        Main newWindow = new Main();
        newWindow.initialState = display.currentState.get().getState();
        try {
            newWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: Could not create new window");
        }
    }
}
