package sample;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

/**
 * Created by Rudy Gamberini on 2/4/2016.
 */
public class ControlDisplay extends BorderPane {
    private int counter = 0;
    private JFXDrawer drawer;
    private JFXToolbar toolBar;

    public ControlDisplay(GameOfLifeDisplay display) {
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

        MediaPlayerButtons mediaPlayerButtons = new MediaPlayerButtons(display);
        Node hamburger = new HamburgerDrawer(drawer);

        toolBar.setLeft(hamburger);

        toolBar.setCenter(mediaPlayerButtons);

        HamburgerDrawer invisibleHamburger = new HamburgerDrawer(drawer);
        invisibleHamburger.setMouseTransparent(true);
        invisibleHamburger.hamburger.setStyle("-fx-opacity: 0");
        toolBar.setRight(invisibleHamburger);
    }
}
