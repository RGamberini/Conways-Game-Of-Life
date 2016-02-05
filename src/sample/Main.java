package sample;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Board model = new Board(25, new GameOfLife(),
                ImageParser.parseImage("blank.bmp"));
        GameOfLifeDisplay display = new GameOfLifeDisplay(model);

//        StackPane stackPane = new StackPane(display, new ControlDisplay(display));
        JFXDecorator prettyWindow = new JFXDecorator(primaryStage, new ControlDisplay(display));
        HBox buttonContainer = (HBox) prettyWindow.getChildren().get(0);
        buttonContainer.getChildren().remove(0);
        buttonContainer.getChildren().remove(1);

        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(prettyWindow, 22*25 + 9, 22*25 + 100);
//        scene.widthProperty().addListener((o, old, newV) -> {
//            System.out.println("Width: " + newV);
//        });
//
//        scene.heightProperty().addListener((o, old, newV) -> {
//            System.out.println("Height: " + newV);
//        });
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
