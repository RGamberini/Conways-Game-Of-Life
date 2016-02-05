package sample;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

/**
 * Created by Nick on 2/5/2016.
 */
public class HamburgerDrawer extends JFXRippler {
    private int counter = 0;
    public JFXHamburger hamburger;
    public StackPane hamburgerContainer;

    public HamburgerDrawer(JFXDrawer drawer) {
        super(new StackPane(), RipplerMask.CIRCLE);
        hamburger = new JFXHamburger();
        HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask.setRate(-1);

        hamburgerContainer = (StackPane) this.getChildren().get(1);
        hamburgerContainer.getChildren().add(hamburger);

        drawer.setOnDrawingAction((e) -> {
            burgerTask.setRate(1);
            burgerTask.setOnFinished((event) -> counter = 1);
            burgerTask.play();
        });

        drawer.setOnHidingAction((e) -> {
            burgerTask.setRate(-1);
            burgerTask.setOnFinished((event) -> counter = 0);
            burgerTask.play();
        });

        hamburgerContainer.setOnMouseClicked((e) -> {
            if (counter == 0)
                drawer.draw();
            else if (counter == 1)
                drawer.hide();
            counter = -1;
        });

        hamburger.setAlignment(Pos.CENTER_LEFT);
    }
}
