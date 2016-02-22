package sample;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.beans.Observable;
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
    private Stage primaryStage;
    public boolean[][] initialState = ImageParser.parseImage("blank.bmp");

    @Override
    public void start(Stage primaryStage) throws Exception {
        int size  = 25;
        Board model = new Board(size, new GameOfLife(), initialState);
        GameOfLifeDisplay display = new GameOfLifeDisplay(model);
        this.primaryStage = primaryStage;

//        StackPane stackPane = new StackPane(display, new ControlDisplay(display));
        JFXDecorator prettyWindow = new JFXDecorator(this.primaryStage, new ControlDisplay(display));
        HBox buttonContainer = (HBox) prettyWindow.getChildren().get(0);
        //Remove extra buttons on the window
        buttonContainer.getChildren().remove(0);
        buttonContainer.getChildren().remove(1);

        this.primaryStage.setTitle("Hello World");
        Scene scene = new Scene(prettyWindow);
        this.primaryStage.setWidth(22*size + 9);
        this.primaryStage.setHeight(22*size + 100);
        model.size.addListener(this::resize);
        System.out.println("Width: " + scene.getWidth() + " Height: " + scene.getHeight());
        scene.getStylesheets().add("style.css");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    public void resize(Observable o, Number oldVal, Number newVal) {
        this.primaryStage.setWidth(22*newVal.intValue() + 9);
        this.primaryStage.setHeight(22*newVal.intValue() + 100);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
