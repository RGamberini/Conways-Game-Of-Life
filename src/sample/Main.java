package sample;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        int size  = 25;
        Board model = new Board(size, new GameOfLife(),
                ImageParser.parseImage("blank.bmp"));
        GameOfLifeDisplay display = new GameOfLifeDisplay(model);

//        StackPane stackPane = new StackPane(display, new ControlDisplay(display));
        JFXDecorator prettyWindow = new JFXDecorator(primaryStage, new ControlDisplay(display));
        HBox buttonContainer = (HBox) prettyWindow.getChildren().get(0);
        //Remove extra buttons on the window
        buttonContainer.getChildren().remove(0);
        buttonContainer.getChildren().remove(1);

        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(prettyWindow, 22*size + 9, 22*size + 100);
        System.out.println("Width: " + scene.getWidth() + " Height: " + scene.getHeight());
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
