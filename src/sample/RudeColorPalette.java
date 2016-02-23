package sample;

import com.jfoenix.controls.JFXSlider;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Surrounds the ColorPicker.
 */
class RudeColorPalette extends Pane {
    private final IntegerProperty[] rgbValues;

    public RudeColorPalette(ObjectProperty<Color> color) {

        this.setPrefHeight(480);
        this.setPrefWidth(300);
        this.setMaxSize(300, 480);
        this.getStyleClass().add("card");

        rgbValues = new SimpleIntegerProperty[3];

        rgbValues[0] = new SimpleIntegerProperty((int) (color.get().getRed() * 255));
        rgbValues[1] = new SimpleIntegerProperty((int) (color.get().getGreen() * 255));
        rgbValues[2] = new SimpleIntegerProperty((int) (color.get().getBlue() * 255));


        VBox innerVBox = new VBox();
        innerVBox.setPrefSize(300, 480);
        innerVBox.setMaxSize(300, 480);
        this.getChildren().add(innerVBox);

        Rectangle colorSwatch = new Rectangle();
        colorSwatch.fillProperty().bind(color);
        colorSwatch.setHeight(207);
        colorSwatch.setWidth(300);
        colorSwatch.setStrokeWidth(4);
        colorSwatch.setArcHeight(0);
        colorSwatch.setArcWidth(0);
        innerVBox.getChildren().add(colorSwatch);

        String[] colors = {"red", "green", "blue"};
        JFXSlider[] sliders = new JFXSlider[3];
        for (int i = 0; i < colors.length; i++) {
            JFXSlider slider = new JFXSlider(0, 255, rgbValues[i].get());
            VBox.setMargin(slider, new Insets(42, 0, 0, 0));
            slider.setPadding(new Insets(0, 4, 0, 4));
            slider.setMaxWidth(300);
            innerVBox.getChildren().add(slider);
            slider.getStyleClass().add(colors[i]+"-slider");

            rgbValues[i].bind(slider.valueProperty());
            sliders[i] = slider;

            rgbValues[i].addListener((o, oldVal, newVal) -> {
                color.setValue(Color.rgb(rgbValues[0].get(), rgbValues[1].get(), rgbValues[2].get()));
            });
        }

        HBox footerHBox = new HBox();
        footerHBox.setAlignment(Pos.CENTER);
        footerHBox.setPrefSize(200, 100);
        innerVBox.getChildren().add(footerHBox);

        Pane footerPane = new Pane();
        footerPane.setPrefSize(86, 43);
        footerPane.setMaxHeight(43);
        footerPane.getStyleClass().add("card");
        footerHBox.getChildren().add(footerPane);

        Label colorName = new Label();
        colorName.textProperty().bind(color.asString());
        colorName.setPrefSize(86, 43);
        colorName.setAlignment(Pos.CENTER);
        colorName.setTextAlignment(TextAlignment.CENTER);
        colorName.setFont(new Font(14));
        colorName.setStyle("-fx-font-weight: bold;");
        footerPane.getChildren().add(colorName);
    }
}
