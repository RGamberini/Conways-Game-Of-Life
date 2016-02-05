package sample;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Created by Rudy Gamberini on 2/4/2016.
 */
public class NewControlDisplay extends BorderPane {
    private int counter = 0;
    private JFXDrawer drawer;
    private JFXToolbar toolBar;

    public NewControlDisplay(GameOfLifeDisplay display) {
        toolBar = new JFXToolbar();
        VBox tempVBox = new VBox(toolBar);
        this.setTop(tempVBox);

        drawer = new JFXDrawer();
        drawer.setDefaultDrawerSize(250.0);
        drawer.setDirection(JFXDrawer.DrawerDirection.LEFT);
        drawer.setOnMouseClicked((event) -> {
            if (!drawer.isShown()) display.fireEvent(event);
        });

        StackPane sideContent = new StackPane(), content = new StackPane();
        drawer.setSidePane(sideContent);
        drawer.setContent(content);
        this.setCenter(new StackPane(display, drawer));

        MediaPlayerButtons mediaPlayerButtons = new MediaPlayerButtons();
        toolBar.setCenter(mediaPlayerButtons);
        mediaPlayerButtons.PLAYPAUSE.setOnMouseClicked((event) -> {
            display.playing.set(!display.playing.get());
        });
        initHamburger();
    }

    private void initHamburger() {
        JFXHamburger hamburger = new JFXHamburger();
        HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask.setRate(-1);

        StackPane hamburgerContainer = new StackPane(hamburger);
        JFXRippler hamburgerRippler = new JFXRippler(hamburgerContainer, JFXRippler.RipplerMask.CIRCLE);

        toolBar.setLeftItems(hamburgerRippler);

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

        hamburgerContainer.setOnMouseClicked((e)->{
            if (counter == 0)
                drawer.draw();
            else if (counter == 1)
                drawer.hide();
            counter = -1;
        });
    }
}
