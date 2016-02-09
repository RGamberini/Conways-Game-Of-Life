package sample;

import com.jfoenix.controls.*;
import javafx.scene.layout.*;

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
        drawer.setSidePane(new Sidebar(display, drawer));
        drawer.setContent(content);
        borderPane.setCenter(display);

        MediaPlayerButtons mediaPlayerButtons = new MediaPlayerButtons(display);
        StackPane hamburger = new SettingsButton(drawer);

        toolBar.setLeft(hamburger);

        toolBar.setCenter(mediaPlayerButtons);

        SettingsButton invisibleHamburger = new SettingsButton(drawer);
        invisibleHamburger.setMouseTransparent(true);
        invisibleHamburger.icon.setStyle("-fx-opacity: 0");
        toolBar.setRight(invisibleHamburger);
        this.getChildren().add(new StackPane(borderPane, drawer));
    }
}
