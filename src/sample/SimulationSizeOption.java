package sample;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Dropdown ComboBox that enables you to change the size of the GOL board.
 */
class SimulationSizeOption extends Option {
    private final JFXComboBox<Integer> comboBox;
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

    private void selectionChanged(Observable o, Integer oldVal, Integer newVal) {
        display.size.set(newVal);
    }

    private void sizeChanged(Observable o, Number oldVal, Number newVal) {
        if(comboBox.getSelectionModel().getSelectedItem() != newVal.intValue())
            this.comboBox.getSelectionModel().select(newVal.intValue());
    }
}
