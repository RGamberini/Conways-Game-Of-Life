package com.rudygamberini.gameoflife;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * Part of the ControlDisplay.
 */
class MediaPlayerButtons extends HBox {
    private final JFXButton PLAYPAUSE;

    public MediaPlayerButtons(GameOfLifeDisplay display) {
        JFXButton STEPBACKWARD = newButton("STEP_BACKWARD");
        PLAYPAUSE = newButton("PLAY");
        JFXButton STEPFORWARD = newButton("STEP_FORWARD");

        this.getChildren().addAll(STEPBACKWARD, PLAYPAUSE, STEPFORWARD);
        this.setAlignment(Pos.CENTER);
        PLAYPAUSE.setPadding(new Insets(0, 0, 0, 5));

        display.playing.addListener((o, oldVal, newVal) -> {
            FontAwesomeIconView icon = (FontAwesomeIconView) PLAYPAUSE.getGraphic();
            if (!newVal) {
                icon.setGlyphName("PLAY");
                icon.setSize("32");
                PLAYPAUSE.setPadding(new Insets(0, 0, 0, 5));
            }
            else {
                icon.setGlyphName("PAUSE");
                icon.setSize("28");
                PLAYPAUSE.setPadding(new Insets(0, 0, 0, 0));
            }
        });

        PLAYPAUSE.setOnMouseClicked((event) -> display.playing.set(!display.playing.get()));
        STEPFORWARD.setOnMouseClicked((event) -> {
            display.playing.set(false);
            display.step();
        });

        STEPBACKWARD.setOnMouseClicked((event) -> {
            display.playing.set(false);
            display.stepBack();
        });
    }

    private JFXButton newButton(String buttonName) {
        FontAwesomeIconView icon = new FontAwesomeIconView();
        icon.setGlyphName(buttonName);
        icon.setSize("32");

        JFXButton button = new JFXButton("", icon);
        int buttonSize = 48;
        button.getStyleClass().add("iconButton");
        button.getStyleClass().add("mediaButton");
        button.setMinSize(buttonSize, buttonSize);
        button.setPrefSize(buttonSize, buttonSize);
        button.setMaxSize(buttonSize, buttonSize);
        HBox.setMargin(button, new Insets(0, 8, 0, 8));

        return button;
    }
}
