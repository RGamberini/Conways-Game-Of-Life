package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.skins.JFXColorPickerSkin;
import com.sun.javafx.scene.control.behavior.ComboBoxBaseBehavior;
import com.sun.javafx.scene.control.skin.ComboBoxPopupControl;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;

/**
 * Created by Nick on 1/30/2016.
 */
public class IndependantColorPickerSkin extends ComboBoxPopupControl<Color> {

    private Pane displayNode;
    private RudeColorPalette popupContent;
    private Rectangle rectangle;

    public IndependantColorPickerSkin(final ColorPicker colorPicker) {
        super(colorPicker, new RudeColorPickerBehavior(colorPicker));
        JFXButton mainButton = new JFXButton("#FA8C32");
        mainButton.setPrefWidth(113);
        mainButton.setPrefHeight(38);

        mainButton.addEventHandler(MouseEvent.ANY, (event)->{
            if(!event.isConsumed()){
                event.consume();
                getSkinnable().fireEvent(event);
            }
        });
        rectangle = new Rectangle();
        rectangle.setHeight(25);
        rectangle.setWidth(25);
        rectangle.setStrokeWidth(0);
        rectangle.setArcWidth(5);
        rectangle.setArcHeight(5);
        rectangle.setFill(Paint.valueOf("FA8C32"));

        mainButton.setGraphic(rectangle);

        Pane mainNode = new Pane();
        mainNode.getStyleClass().add("card");
        mainNode.setPrefSize(113, 38);
        mainNode.getChildren().add(mainButton);

        displayNode = new Pane();
        mainNode.setPrefSize(113, 38);
        displayNode.getChildren().add(mainNode);

        getChildren().add(displayNode);
        getChildren().remove(arrowButton);

        this.getSkinnable().getStyleClass().clear();
        System.out.println();
        getPopupContent();
        //System.out.println(mainNode.getParent());
        //System.out.println(this.getSkinnable().getStyleClass());



    }

    @Override protected Node getPopupContent() {
        if (popupContent == null) {
            System.out.println("NULLPOPUP");

            popupContent = new RudeColorPalette((ColorPicker)getSkinnable());
            popupContent.setPopupControl(getPopup());
            rectangle.fillProperty().bind(popupContent.color);
        }
        System.out.println("RETURNPOPUP");
        return popupContent;
    }

    @Override public void show() {
        super.show();
        ColorPicker colorPicker = (ColorPicker)this.getSkinnable();
        System.out.println("SHOW");
        this.popupContent.updateSelection((Color)colorPicker.getValue());
    }

    @Override protected void handleControlPropertyChanged(String p) {
        super.handleControlPropertyChanged(p);
        if ("SHOWING".equals(p)) {
            if (getSkinnable().isShowing()) show();
            else if (!popupContent.isCustomColorDialogShowing()) hide();
        } else if ("VALUE".equals(p)) {
            // change the selected color
            //updateColor();
        }
    }

    public void syncWithAutoUpdate() {
        if (!getPopup().isShowing() && getSkinnable().isShowing()) {
            // Popup was dismissed. Maybe user clicked outside or typed ESCAPE.
            // Make sure JFXColorPickerUI button is in sync.
            getSkinnable().hide();
        }
    }

    @Override
    protected TextField getEditor() {
        return null;
    }

    @Override
    protected StringConverter<Color> getConverter() {
        return null;
    }

    @Override
    public Node getDisplayNode() {
        return displayNode;
    }
}
