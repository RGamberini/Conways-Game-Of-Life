package sample;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Nick on 2/6/2016.
 */
public class Sidebar extends VBox {
    private StackPane header;

    public Sidebar(GameOfLifeDisplay display, JFXDrawer drawer) {
        VBox innerVBox = new VBox();
        /**
         * HEADER
         * The header consists of an a background toolbar,
         * title, subtitle, image and back button.
         */

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

        /**
         * TITLE AND SUBTITLE
         */

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

        /**
         * BACK BUTTON
         */

        MaterialIconView icon = new MaterialIconView(MaterialIcon.ARROW_BACK);
        icon.setSize("40");
        //icon.setStyle("-fx-font-weight: bold");
        JFXButton backButton = initBackButton();
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(4, 0, 0, 4));
        backButton.setOnMouseClicked((event) -> drawer.hide());
        header.getChildren().add(backButton);

        /**
         * END HEADER
         * START COLOR OPTIONS
         */

        Label colorPickerSubHeader = new Label("Color Options");
        colorPickerSubHeader.getStyleClass().add("subheader");
        VBox.setMargin(colorPickerSubHeader, new Insets(10, 0, 6, 8));
        innerVBox.getChildren().add(colorPickerSubHeader);
        VBox colorOptions = new VBox(10);
        HBox[] colorPickerButtons = {new Option(display, "Alive Color", new ColorPicker(display.aliveColor)),
                new Option(display, "Dead Color", new ColorPicker(display.deadColor)),
                new Option(display, "Active Color", new ColorPicker(display.activeColor))};
        colorOptions.getChildren().addAll(colorPickerButtons);
        innerVBox.getChildren().add(colorOptions);

        colorPickerButtons[2].getStyleClass().add("divider");
        colorPickerButtons[2].setPadding(new Insets(0, 8, 10, 8));

        /**
         * END COLOR OPTIONS
         * START SIMULATION OPTIONS
         */

        Label simulationOptionsSubHeader = new Label("Simulation Options");
        simulationOptionsSubHeader.getStyleClass().add("subheader");
        VBox.setMargin(simulationOptionsSubHeader, new Insets(10, 0, 16, 8));
        innerVBox.getChildren().add(simulationOptionsSubHeader);

        /**
         * Sim Size Option
         */
        VBox simulationOptionsVBox = new VBox(16);
        //Settings Speed, Simulation loop, Grid Size
        JFXSlider slider = new JFXSlider();
        slider.setMin(-10);
        slider.setMax(10);
        slider.setValue(display.time.getValue());
        display.time.bind(slider.valueProperty());
        slider.getStyleClass().add("sidebar-slider");
        simulationOptionsVBox.getChildren().add(new Option(display, "Sim Speed", slider));

        /**
         * Edge Loop Option
         */
        JFXCheckBox checkBox = new JFXCheckBox();
        checkBox.setSelected(display.borderLoop.getValue());
        display.borderLoop.bind(checkBox.selectedProperty());
        simulationOptionsVBox.getChildren().add(new Option(display, "Loop Edges", checkBox));

        /**
         * Sim Size Option
         */
        simulationOptionsVBox.getChildren().add(new SimulationSizeOption(display));

        /**
         * Saving and loading options
         */
        simulationOptionsVBox.getChildren().add(0, new SaveLoadOption(display));

        /**
         * Breakout button
         */
        simulationOptionsVBox.getChildren().add(1, new BreakOutOption(display));

        innerVBox.getChildren().add(simulationOptionsVBox);
        /**
         * END SIMULATION OPTIONS
         */
        ScrollPane scrollPane = new ScrollPane(new StackPane(innerVBox));
        scrollPane.getStyleClass().add("sidebar");
        this.getChildren().add(scrollPane);
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
}
