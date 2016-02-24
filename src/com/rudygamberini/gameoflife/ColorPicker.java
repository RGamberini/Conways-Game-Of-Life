package com.rudygamberini.gameoflife;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

/**
 * Custom color picker, spent a lot of time on this.
 */
class ColorPicker extends Pane {
    private final JFXButton mainButton;
    private final Popup popup;
    private final ObjectProperty<Color> color;
    private final ParallelTransition show;
    private final ParallelTransition hide;


    public ColorPicker(ObjectProperty<Color> defaultColor) {
        color = defaultColor;

        mainButton = new JFXButton(colorDisplayName(color.get()));
        mainButton.setPrefWidth(121);
        mainButton.setPrefHeight(38);
        this.setPrefHeight(38);
        color.addListener(((observable, oldValue, newValue) -> mainButton.setText(colorDisplayName(color.get()))));


        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(25);
        rectangle.setWidth(25);
        rectangle.setStrokeWidth(0);
        rectangle.setArcWidth(5);
        rectangle.setArcHeight(5);
        rectangle.fillProperty().bind(color);

        mainButton.setGraphic(rectangle);

        this.getStyleClass().add("card");
        this.setPrefSize(121, 38);
        this.setMaxSize(121, 38);
        this.getChildren().add(mainButton);

        RudeColorPalette colorPalette = new RudeColorPalette(color);
        show = Animations.newCardAnimation(colorPalette);
        hide = Animations.newCardDestroyAnimation(colorPalette);

        popup = new Popup() {
            @Override
            public void hide() {
                hide.setOnFinished((event) -> super.hide());
                hide.play();
            }
        };
        popup.setAutoHide(true);
        StackPane popupContent = new StackPane();
        popupContent.setPadding(new Insets(20, 20, 20, 20));
        popupContent.getChildren().add(colorPalette);
        popup.getContent().add(popupContent);

        Point2D p = this.localToScene(0.0, 0.0);
        //System.out.println(p);

        mainButton.setOnMouseClicked((event) -> {
            popup.show(this,
                    p.getX() + this.getScene().getX() + this.getScene().getWindow().getX() + (getPrefWidth() * 2),
                    p.getY() + this.getScene().getY() + this.getScene().getWindow().getY());
        });

        popup.setOnShowing((event) -> show.play());
    }

    // Lifted straight from JFXColorPickerSkin
    private static String colorDisplayName(Color c) {
        if(c != null) {
            return formatHexString(c);
        } else {
            return null;
        }
    }

    // Lifted straight from JFXColorPickerSkin
    private static String formatHexString(Color c) {
        return c != null?String.format(null, "#%02x%02x%02x", new Object[]{Long.valueOf(Math.round(c.getRed() * 255.0D)), Long.valueOf(Math.round(c.getGreen() * 255.0D)), Long.valueOf(Math.round(c.getBlue() * 255.0D))}).toUpperCase():null;
    }

}
