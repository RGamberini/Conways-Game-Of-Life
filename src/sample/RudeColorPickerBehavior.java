package sample;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.behavior.JFXColorPickerBehavior;
import com.jfoenix.skins.JFXColorPickerSkin;
import javafx.scene.control.ColorPicker;

/**
 * Created by Nick on 1/29/2016.
 */
public class RudeColorPickerBehavior extends JFXColorPickerBehavior {

    public RudeColorPickerBehavior(ColorPicker colorPicker) {
        super(colorPicker);
    }

    @Override
    public void onAutoHide() {
        ColorPicker colorPicker = (ColorPicker)this.getControl();
        RudeColorPickerSkin cpSkin = (RudeColorPickerSkin) colorPicker.getSkin();
        cpSkin.syncWithAutoUpdate();
        if(!colorPicker.isShowing()) {
            super.onAutoHide();
        }
    }
}
