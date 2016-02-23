package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class Controller {
    @FXML
    public void initialize() {
        GridPane root = new GridPane();
        for (int i = 0; i < 25; i++) {
            root.addColumn(0);
            root.addRow(0);
        }
    }
}
