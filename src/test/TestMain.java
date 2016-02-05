package test;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * Created by Nick on 2/3/2016.
 */
public class TestMain extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        primaryStage.show();
    }
}
