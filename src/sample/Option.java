package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/**
 * Base class for options with text on the left and the actual option on the right.
 */
class Option extends HBox {
    final GameOfLifeDisplay display;
    Option(GameOfLifeDisplay display) {
        this.display = display;
    }

    public Option(GameOfLifeDisplay display, String name, Node optionPicker) {
        this(display);
        init(name, optionPicker);
    }

    void init(String name, Node optionPicker) {
        Label label = new Label(name);
        //label.setFont(new Font());
        label.getStyleClass().add("colorPickerLabel");
        label.setAlignment(Pos.CENTER_LEFT);
        label.setMaxHeight(Double.MAX_VALUE);
        label.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);
        //HBox.setMargin(label, new Insets(0, 16, 0, 8));

        StackPane option = new StackPane(optionPicker);
        option.setAlignment(Pos.CENTER_RIGHT);

        this.getChildren().addAll(label, option);
        this.setPadding(new Insets(0, 8, 0, 8));
    }
}
