package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

/**
 * Obviously the settings ("gear") icon on the ControlDisplay.
 */
class SettingsButton extends StackPane {
    public final MaterialDesignIconView icon;
    public SettingsButton(JFXDrawer drawer) {
        icon = new MaterialDesignIconView();
        icon.setGlyphName("SETTINGS");
        icon.setSize("36");

        JFXButton button = new JFXButton("", icon);
        button.getStyleClass().add("iconButton");
        int buttonSize = 36;
        button.setMinSize(buttonSize, buttonSize);
        button.setPrefSize(buttonSize, buttonSize);
        button.setMaxSize(buttonSize, buttonSize);

        button.setAlignment(Pos.CENTER);

        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(0, 8, 0, 8));
        this.getChildren().add(button);

        button.setOnMouseClicked((event) -> {
            if (drawer.isShown()) drawer.hide();
            else drawer.draw();
        });
    }
}
