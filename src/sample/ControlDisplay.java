package sample;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Rudy Gamberini on 2/4/2016.
 */
public class ControlDisplay extends StackPane {
    private JFXDrawer drawer;
    private JFXToolbar toolBar;
    private BorderPane borderPane;

    public ControlDisplay(GameOfLifeDisplay display) {
        borderPane = new BorderPane();

        toolBar = new JFXToolbar();
        VBox tempVBox = new VBox(toolBar);
        borderPane.setTop(tempVBox);

        drawer = new JFXDrawer();
        drawer.setDefaultDrawerSize(250.0);
        drawer.setDirection(JFXDrawer.DrawerDirection.LEFT);
//        drawer.setOnMouseClicked((event) -> {
//            if (!drawer.isShown()) display.fireEvent(event);
//        });
        drawer.setOnDrawerClosed((event) -> drawer.setMouseTransparent(true));
        drawer.setOnDrawerOpened((event) -> drawer.setMouseTransparent(false));
        drawer.setMouseTransparent(true);

        StackPane sideContent = new StackPane(), content = new StackPane();
        drawer.setSidePane(new Sidebar());
        drawer.setContent(content);
        borderPane.setCenter(display);

        MediaPlayerButtons mediaPlayerButtons = new MediaPlayerButtons(display);
        Node hamburger = new HamburgerDrawer(drawer);

        toolBar.setLeft(hamburger);

        toolBar.setCenter(mediaPlayerButtons);

        HamburgerDrawer invisibleHamburger = new HamburgerDrawer(drawer);
        invisibleHamburger.setMouseTransparent(true);
        invisibleHamburger.hamburger.setStyle("-fx-opacity: 0");
        toolBar.setRight(invisibleHamburger);
        this.getChildren().add(new StackPane(borderPane, drawer));
    }
}
