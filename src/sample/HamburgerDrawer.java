package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Created by Nick on 2/5/2016.
 */
public class HamburgerDrawer extends JFXRippler {
    private int counter = 0;
    public MaterialDesignIconView hamburger;
    public StackPane hamburgerContainer;

    public HamburgerDrawer(JFXDrawer drawer) {
        super(new StackPane(), RipplerMask.CIRCLE);
        hamburger = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        hamburger.setSize("28");
//        HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
//        burgerTask.setRate(-1);

        hamburgerContainer = (StackPane) this.getChildren().get(1);
        hamburgerContainer.getChildren().add(hamburger);

//        drawer.setOnDrawingAction((e) -> {
//            burgerTask.setRate(1);
//            burgerTask.setOnFinished((event) -> counter = 1);
//            burgerTask.play();
//        });
//
//        drawer.setOnHidingAction((e) -> {
//            burgerTask.setRate(-1);
//            burgerTask.setOnFinished((event) -> counter = 0);
//            burgerTask.play();
//        });

        hamburgerContainer.setOnMouseClicked((e) -> {
            if (counter == 0)
                drawer.draw();
            else if (counter == 1)
                drawer.hide();
            counter = -1;
        });

        hamburgerContainer.setAlignment(Pos.CENTER_LEFT);
    }

    private JFXButton newButton(String buttonName) {
        FontAwesomeIconView icon = new FontAwesomeIconView();
        icon.setGlyphName(buttonName);
        icon.setSize("32");

        JFXButton button = new JFXButton("", icon);
        int buttonSize = 48;
        button.setMinSize(buttonSize, buttonSize);
        button.setPrefSize(buttonSize, buttonSize);
        button.setMaxSize(buttonSize, buttonSize);
        HBox.setMargin(button, new Insets(0, 8, 0, 8));

        return button;
    }
}
