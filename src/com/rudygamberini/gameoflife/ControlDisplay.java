package com.rudygamberini.gameoflife;

import com.jfoenix.controls.*;
import javafx.scene.layout.*;

/**
 * The Play/Pause/Reverse Controls and Settings button.
 */
class ControlDisplay extends StackPane {
    private final JFXDrawer drawer;

    public ControlDisplay(GameOfLifeDisplay display) {
        BorderPane borderPane = new BorderPane();

        JFXToolbar toolBar = new JFXToolbar();
        VBox tempVBox = new VBox(toolBar);
        borderPane.setTop(tempVBox);

        drawer = new JFXDrawer();
        drawer.setDefaultDrawerSize(250.0);
        drawer.setDirection(JFXDrawer.DrawerDirection.LEFT);
//        drawer.setOnMouseClicked((event) -> {
//            if (!drawer.isShown()) display.fireEvent(event);
//        });
        drawer.setOnDrawerClosed((event) -> drawer.setMouseTransparent(true));
        drawer.setOnDrawerOpened((event) -> {
            drawer.setMouseTransparent(false);
            display.playing.set(false);
        });
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
