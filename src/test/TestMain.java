package test;

import demos.gui.main.MainController;
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
        loader.setController(new demos.gui.main.MainController());
        MainController controller = loader.getController();
        primaryStage.show();
    }
}
