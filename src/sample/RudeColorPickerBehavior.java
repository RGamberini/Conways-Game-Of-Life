package sample;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.ESCAPE;
import static javafx.scene.input.KeyCode.SPACE;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import java.util.ArrayList;
import java.util.List;
import com.jfoenix.skins.JFXColorPickerSkin;
import com.sun.javafx.scene.control.behavior.ComboBoxBaseBehavior;
import com.sun.javafx.scene.control.behavior.KeyBinding;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

/**
 * @author Shadi Shaheen
 *
 */
public class RudeColorPickerBehavior extends ComboBoxBaseBehavior<Color> {

    /***************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/

    public RudeColorPickerBehavior(final ColorPicker colorPicker) {
        super(colorPicker, JFX_COLOR_PICKER_BINDINGS);
    }

    /***************************************************************************
     *                                                                         *
     * Key event handling                                                      *
     *                                                                         *
     **************************************************************************/
    protected static final String JFX_OPEN_ACTION = "Open";
    protected static final String JFX_CLOSE_ACTION = "Close";
    protected static final List<KeyBinding> JFX_COLOR_PICKER_BINDINGS = new ArrayList<KeyBinding>();
    static {
        JFX_COLOR_PICKER_BINDINGS.add(new KeyBinding(ESCAPE, KEY_PRESSED, JFX_CLOSE_ACTION));
        JFX_COLOR_PICKER_BINDINGS.add(new KeyBinding(SPACE, KEY_PRESSED, JFX_OPEN_ACTION));
        JFX_COLOR_PICKER_BINDINGS.add(new KeyBinding(ENTER, KEY_PRESSED, JFX_OPEN_ACTION));
    }

    @Override protected void callAction(String name) {
        if (JFX_OPEN_ACTION.equals(name)) show();
        else if(JFX_CLOSE_ACTION.equals(name)) hide();
        else super.callAction(name);
    }

    /**************************************************************************
     *                                                                        *
     * Mouse Events handling (when losing focus)                              *
     *                                                                        *
     *************************************************************************/

    @Override public void onAutoHide() {
        ColorPicker colorPicker = (ColorPicker)getControl();
        IndependantColorPickerSkin cpSkin = (IndependantColorPickerSkin)colorPicker.getSkin();
        cpSkin.syncWithAutoUpdate();
        if (!colorPicker.isShowing()) super.onAutoHide();
    }

}
