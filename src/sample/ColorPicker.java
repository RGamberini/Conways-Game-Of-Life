package sample;

import com.jfoenix.controls.JFXButton;
import com.sun.javafx.util.Utils;
import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.util.Locale;

/**
 * Created by Nick on 1/30/2016.
 */
public class ColorPicker extends Pane {
    private JFXButton mainButton;
    private Rectangle rectangle;
    private Popup popup;
    public ObjectProperty<Color> color;
    private RudeColorPalette colorPalette;
    private ParallelTransition show, hide;


    public ColorPicker(ObjectProperty<Color> defaultColor) {
        color = defaultColor;

        mainButton = new JFXButton(colorDisplayName(color.get()));
        mainButton.setPrefWidth(113);
        mainButton.setPrefHeight(38);
        color.addListener(((observable, oldValue, newValue) -> mainButton.setText(colorDisplayName(color.get()))));


        rectangle = new Rectangle();
        rectangle.setHeight(25);
        rectangle.setWidth(25);
        rectangle.setStrokeWidth(0);
        rectangle.setArcWidth(5);
        rectangle.setArcHeight(5);
        rectangle.fillProperty().bind(color);

        mainButton.setGraphic(rectangle);

        this.getStyleClass().add("card");
        this.setPrefSize(113, 38);
        this.setMaxSize(113, 38);
        this.getChildren().add(mainButton);

        colorPalette = new RudeColorPalette(color);
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
                    p.getX() + this.getScene().getX() + this.getScene().getWindow().getX() + getParent().getBoundsInLocal().getWidth(),
                    p.getY() + this.getScene().getY() + this.getScene().getWindow().getY());
        });

        popup.setOnShowing((event) -> show.play());
    }

    // Lifted straight from JFXColorPickerSkin
    static String colorDisplayName(Color c) {
        if(c != null) {
            String displayName = formatHexString(c);
            return displayName;
        } else {
            return null;
        }
    }

    // Lifted straight from JFXColorPickerSkin
    static String formatHexString(Color c) {
        return c != null?String.format((Locale)null, "#%02x%02x%02x", new Object[]{Long.valueOf(Math.round(c.getRed() * 255.0D)), Long.valueOf(Math.round(c.getGreen() * 255.0D)), Long.valueOf(Math.round(c.getBlue() * 255.0D))}).toUpperCase():null;
    }

}
