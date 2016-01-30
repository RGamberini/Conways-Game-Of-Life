package sample;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;

/**
 * Created by Nick on 1/28/2016.
 */
public class ControlDisplay extends Pane {
    public ControlDisplay() {
        super();
        this.getStyleClass().add("card");
        this.setPrefWidth(210);
        this.setPrefHeight(200);
        this.setMaxSize(210, 200);
        StackPane.setMargin(this, new Insets(8, 0, 0, 8));
        StackPane.setAlignment(this, Pos.TOP_LEFT);

        VBox innerVBox = new VBox();
        innerVBox.setPrefHeight(200);
        //this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.getChildren().add(innerVBox);

        HBox buttonBar = new HBox();
        //buttonBar.setMaxWidth(Double.MAX_VALUE);
        buttonBar.setPrefWidth(210);
        buttonBar.getStyleClass().add("buttonBar");

        String[] buttonBarButtons = {"BACKWARD", "PLAY", "FORWARD"};
        for (String buttonName: buttonBarButtons) {
            FontAwesomeIconView icon = new FontAwesomeIconView();
            icon.setGlyphName(buttonName);
            icon.setSize("25");
            JFXButton button = new JFXButton("", icon);
            button.setPrefWidth(70);
            HBox.setHgrow(button, Priority.ALWAYS);

            buttonBar.getChildren().add(button);
        }
        innerVBox.getChildren().add(buttonBar);

        innerVBox.getChildren().add(new RudeColorPicker());

    }
}
