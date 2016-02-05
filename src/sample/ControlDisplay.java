package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.HashMap;

/**
 * Created by Nick on 1/28/2016.
 */
public class ControlDisplay extends Pane {
    HashMap<String, JFXButton> buttons;
    GameOfLifeDisplay display;
    private int width = 225, height = 200;
    public ControlDisplay(GameOfLifeDisplay display) {
        super();
        this.buttons = new HashMap<>(3);
        this.display = display;

        this.getStyleClass().add("card");
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        this.setMaxSize(width, height);
        StackPane.setMargin(this, new Insets(8, 0, 0, 8));
        StackPane.setAlignment(this, Pos.TOP_LEFT);

        VBox innerVBox = new VBox();
        innerVBox.setPrefHeight(height);
        //this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.getChildren().add(innerVBox);

        HBox buttonBar = new HBox();
        //buttonBar.setMaxWidth(Double.MAX_VALUE);
        buttonBar.setPrefWidth(width);
        buttonBar.getStyleClass().add("buttonBar");

        String[] buttonBarButtons = {"BACKWARD", "PLAY", "FORWARD"};
        for (String buttonName: buttonBarButtons) {
            FontAwesomeIconView icon = new FontAwesomeIconView();
            icon.setGlyphName(buttonName);
            icon.setSize("25");
            JFXButton button = new JFXButton("", icon);
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setPrefWidth(width / buttonBarButtons.length);
            HBox.setHgrow(button, Priority.ALWAYS);

            buttons.put(buttonName, button);
            buttonBar.getChildren().add(button);
        }
        innerVBox.getChildren().add(buttonBar);

        HBox[] colorPickerButtons = {newColorPicker("Alive Color", display.aliveColor),
                newColorPicker("Dead Color", display.deadColor),
                newColorPicker("Active Color", display.activeColor)};

        innerVBox.getChildren().addAll(colorPickerButtons);

        buttons.get("PLAY").setOnMouseClicked((event) -> {
            display.playing.set(!display.playing.get());
        });

    }

    public HBox newColorPicker(String name, ObjectProperty<Color> color) {
        Label label = new Label(name);
        label.setFont(new Font(80 / (name.length() / 2)));
        label.setAlignment(Pos.CENTER);
        label.setMaxHeight(Double.MAX_VALUE);
        label.setPrefWidth(80);
        HBox.setMargin(label, new Insets(0, 16, 0, 8));

        HBox hBox = new HBox(label, new ColorPicker(color));
        hBox.setPrefHeight(55);
        hBox.setAlignment(Pos.CENTER);
        hBox.getStyleClass().add("divider");
        //hBox.setPadding(new Insets(4, 0, 4, 0));
        return hBox;
    }
}
