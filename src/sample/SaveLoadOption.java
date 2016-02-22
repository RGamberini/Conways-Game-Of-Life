package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by Rudy Gamberini on 2/22/2016.
 */
public class SaveLoadOption extends HBox {
    FileChooser fileChooser;
    GameOfLifeDisplay display;
    public SaveLoadOption(GameOfLifeDisplay display) {
        super(16);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(0, 8, 0, 8));
        this.display = display;

        JFXButton save = new JFXButton("Save"), load = new JFXButton("Load");
        save.getStyleClass().add("save-load-button");
        load.getStyleClass().add("save-load-button");
        this.getChildren().addAll(save, load);
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("resources"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Board Images", "*.bmp"));

        save.setOnMouseClicked(this::save);
        load.setOnMouseClicked(this::load);
    }

    private void save(Event event) {
        fileChooser.setTitle("Save Board");
        File selectedFile = fileChooser.showSaveDialog(this.getScene().getWindow());
        if (selectedFile != null)
            ImageParser.saveImage(selectedFile, display.currentState.get().getState());
    }

    private void load(Event event) {
        fileChooser.setTitle("Load Board");
        File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());

        if (selectedFile != null) {
            boolean[][] state = ImageParser.parseImage(selectedFile);
            if (display.size.get() != state.length)
                display.size.set(state.length);
            display.currentState.set(new State(display.currentState.get(), state));
        }

    }
}
