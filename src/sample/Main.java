package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Board model = new Board(25, new GameOfLife(),
                ImageParser.parseImage("blank.bmp"));
        GameOfLifeDisplay display = new GameOfLifeDisplay(model);

        Button button = new Button("Tick");
        button.setOnAction((event -> display.playing.setValue(!display.playing.get())));

        StackPane stackPane = new StackPane(display, new RudeColorPicker());

        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(stackPane, 550, 550);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
