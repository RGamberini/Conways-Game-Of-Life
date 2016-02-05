package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Created by Rudy Gamberini on 2/4/2016.
 */
public class MediaPlayerButtons extends HBox {
    public JFXButton STEPBACKWARD, PLAYPAUSE, STEPFORWARD;
    private int buttonSize = 48;
    public MediaPlayerButtons() {
        STEPBACKWARD = newButton("STEP_BACKWARD");
        PLAYPAUSE = newButton("PLAY");
        STEPFORWARD = newButton("STEP_FORWARD");

        this.getChildren().addAll(STEPBACKWARD, PLAYPAUSE, STEPFORWARD);
        this.setAlignment(Pos.CENTER);
        PLAYPAUSE.setPadding(new Insets(0, 0, 0, 5));
    }

    private JFXButton newButton(String buttonName) {
        FontAwesomeIconView icon = new FontAwesomeIconView();
        icon.setGlyphName(buttonName);
        icon.setSize("32");

        JFXButton button = new JFXButton("", icon);
        button.setMinSize(buttonSize, buttonSize);
        button.setPrefSize(buttonSize, buttonSize);
        button.setMaxSize(buttonSize, buttonSize);
        HBox.setMargin(button, new Insets(0, 8, 0, 8));

        return button;
    }
}
