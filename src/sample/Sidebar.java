package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXToolbar;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

/**
 * Created by Nick on 2/6/2016.
 */
public class Sidebar extends VBox {
    private StackPane header;

    public Sidebar(GameOfLifeDisplay display, JFXDrawer drawer) {
        JFXToolbar headerBG = new JFXToolbar();
        ImageView imageView = null;
        try {
            imageView = new ImageView(new Image(new FileInputStream(new File("resources\\material_icon_medium.png"))));
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Material icon missing");
        }
        StackPane image = new StackPane(imageView);
        image.setAlignment(Pos.TOP_CENTER);
        StackPane.setMargin(image, new Insets(8, 0, 0, 0));

        Label title = new Label("Life");
        title.getStyleClass().add("sidebarTitle");
        title.setPadding(new Insets(50, 0, 0, 0));
        title.setTextFill(Paint.valueOf("FFFFFF"));
        title.setContentDisplay(ContentDisplay.CENTER);
        title.setAlignment(Pos.CENTER);
        title.setTextAlignment(TextAlignment.CENTER);

        Label subTitle = new Label("By Rudy Gamberini");
        subTitle.getStyleClass().add("sidebarSubTitle");
        subTitle.setPadding(new Insets(90, 0, 0, 0));
        subTitle.setTextFill(Paint.valueOf("FFFFFF"));
        subTitle.setContentDisplay(ContentDisplay.CENTER);
        subTitle.setAlignment(Pos.CENTER);
        subTitle.setTextAlignment(TextAlignment.CENTER);

        header = new StackPane(headerBG, image, title, subTitle);
        this.getChildren().add(header);
        header.setPrefHeight(150);

        MaterialIconView icon = new MaterialIconView(MaterialIcon.ARROW_BACK);
        icon.setSize("40");
        //icon.setStyle("-fx-font-weight: bold");
        JFXButton backButton = initBackButton();
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(4, 0, 0, 4));
        backButton.setOnMouseClicked((event) -> drawer.hide());
        header.getChildren().add(backButton);

        Label colorPickerSubHeader = new Label("Color Options");
        colorPickerSubHeader.getStyleClass().add("subheader");
        VBox.setMargin(colorPickerSubHeader, new Insets(10, 0, 6, 8));
        this.getChildren().add(colorPickerSubHeader);
        HBox[] colorPickerButtons = {newColorPicker("Alive Color", display.aliveColor),
                newColorPicker("Dead Color", display.deadColor),
                newColorPicker("Active Color", display.activeColor)};
        this.getChildren().addAll(colorPickerButtons);
        colorPickerButtons[2].getStyleClass().add("divider");
        colorPickerButtons[2].setPadding(new Insets(0, 8, 10, 8));
    }

    public JFXButton initBackButton() {
        MaterialIconView icon = new MaterialIconView();
        icon.setGlyphName("ARROW_BACK");
        icon.setSize("36");

        JFXButton button = new JFXButton("", icon);
        int buttonSize = 36;
        button.setRipplerFill(Paint.valueOf("FFFFFF"));
        button.getStyleClass().add("iconButton");
        button.setMinSize(buttonSize, buttonSize);
        button.setPrefSize(buttonSize, buttonSize);
        button.setMaxSize(buttonSize, buttonSize);
        return button;
    }

    public HBox newColorPicker(String name, ObjectProperty<Color> color) {
        Label label = new Label(name);
        //label.setFont(new Font());
        label.getStyleClass().add("colorPickerLabel");
        label.setAlignment(Pos.CENTER_LEFT);
        label.setMaxHeight(Double.MAX_VALUE);
        label.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);
        //HBox.setMargin(label, new Insets(0, 16, 0, 8));

        StackPane colorPicker = new StackPane(new ColorPicker(color));
        colorPicker.setAlignment(Pos.CENTER_RIGHT);

        HBox hBox = new HBox(label, colorPicker);
        hBox.setPrefHeight(55);
        hBox.setPadding(new Insets(0, 8, 0, 8));
        return hBox;
    }
}
