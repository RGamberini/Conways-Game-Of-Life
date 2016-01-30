package sample;

import com.jfoenix.controls.JFXColorPicker;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

/**
 * Created by Nick on 1/29/2016.
 */
public class RudeColorPicker extends JFXColorPicker {
    public RudeColorPicker() {
        super();
    }

    public RudeColorPicker(Color color) {
        super(color);
    }

    @Override protected Skin<?> createDefaultSkin() {
        return new IndependantColorPickerSkin(this);
    }
}
