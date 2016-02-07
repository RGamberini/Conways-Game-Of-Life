package sample;

import com.jfoenix.controls.JFXToolbar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Nick on 2/6/2016.
 */
public class Sidebar extends VBox {
    private StackPane header;

    public Sidebar() {
        JFXToolbar headerBG = new JFXToolbar();
        ImageView imageView = null;
        try {
            imageView = new ImageView(new Image(new FileInputStream(new File("resources\\material_icon_medium.png"))));
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Material icon missing");
        }
        //headerBG.setStyle("-fx-background-color: derive(#4CAF50,-20%)");
        //headerBG.setStyle("-fx-background-color: #009688");

        header = new StackPane(headerBG, imageView);
        this.getChildren().add(header);
        header.setPrefHeight(128);
    }

}
