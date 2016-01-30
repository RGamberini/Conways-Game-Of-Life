package sample;

import com.jfoenix.skins.JFXColorPickerSkin;
import com.sun.javafx.css.converters.BooleanConverter;
import com.sun.javafx.scene.control.skin.ComboBoxBaseSkin;
import javafx.beans.value.WritableValue;
import javafx.css.*;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nick on 1/29/2016.
 */
public class RudeColorPickerSkin extends JFXColorPickerSkin {
    StyleableBooleanProperty colorLabelVisible = new SimpleStyleableBooleanProperty(StyleableProperties.COLOR_LABEL_VISIBLE, RudeColorPickerSkin.this,"colorLabelVisible",true);
    private RudeColorPalette popupContent;

    public RudeColorPickerSkin(ColorPicker colorPicker) {
        super(colorPicker);
    }

    @Override protected Node getPopupContent() {
        if (popupContent == null) {
            popupContent = /*new JFXColorPalette((ColorPicker)getSkinnable());*/ new RudeColorPalette((ColorPicker)getSkinnable());
            popupContent.setPopupControl(getPopup());
        }
        return popupContent;
    }

    @Override public void show() {
        super.showUnOveride();
        ColorPicker colorPicker = (ColorPicker)this.getSkinnable();
        System.out.println("WHOLE NEW \"OOGABOOGA\"");
        this.popupContent.updateSelection((Color)colorPicker.getValue());
    }

    @Override protected void handleControlPropertyChanged(String p) {
        super.handleControlPropertyChangedUnOveride(p);
        if("SHOWING".equals(p)) {
            if(((ComboBoxBase)this.getSkinnable()).isShowing()) {
                this.show();
            } else if(!this.popupContent.isCustomColorDialogShowing()) {
                this.hide();
            }
        } else if("VALUE".equals(p)) {
            this.updateColor();
        }

    }

    private static class StyleableProperties {
        private static final CssMetaData<ColorPicker,Boolean> COLOR_LABEL_VISIBLE =
                new CssMetaData<ColorPicker,Boolean>("-fx-color-label-visible",
                        BooleanConverter.getInstance(), Boolean.TRUE) {

                    @Override public boolean isSettable(ColorPicker n) {
                        final RudeColorPickerSkin skin = (RudeColorPickerSkin) n.getSkin();
                        return skin.colorLabelVisible == null || !skin.colorLabelVisible.isBound();
                    }

                    @Override public StyleableProperty<Boolean> getStyleableProperty(ColorPicker n) {
                        final RudeColorPickerSkin skin = (RudeColorPickerSkin) n.getSkin();
                        return (StyleableProperty<Boolean>)(WritableValue<Boolean>)skin.colorLabelVisible;
                    }
                };
        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                    new ArrayList<CssMetaData<? extends Styleable, ?>>(ComboBoxBaseSkin.getClassCssMetaData());
            styleables.add(COLOR_LABEL_VISIBLE);
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }
}
