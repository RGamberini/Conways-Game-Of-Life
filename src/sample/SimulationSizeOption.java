package sample;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * Created by Rudy Gamberini on 2/18/2016.
 */
public class SimulationSizeOption extends Option {
    private JFXComboBox<Integer> comboBox;
    public SimulationSizeOption(GameOfLifeDisplay display) {
        super(display);
        comboBox = new JFXComboBox<>();
        comboBox.setValue(display.size.getValue());
        ObservableList<Integer> sizes = FXCollections.observableArrayList();
        for (int i = 15; i < 50; i++)
            sizes.add(i);
        comboBox.getSelectionModel()
                .selectedItemProperty()
                .addListener(this::selectionChanged);
        display.size.addListener(this::sizeChanged);
        comboBox.setItems(sizes);
        init("Simulation Size", comboBox);
    }

    public void selectionChanged(Observable o, Integer oldVal, Integer newVal) {
        display.size.set(newVal);
    }

    public void sizeChanged(Observable o, Number oldVal, Number newVal) {
        if(comboBox.getSelectionModel().getSelectedItem() != newVal.intValue())
            this.comboBox.getSelectionModel().select(newVal.intValue());
    }
}
