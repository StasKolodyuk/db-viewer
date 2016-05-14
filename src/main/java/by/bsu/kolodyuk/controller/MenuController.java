package by.bsu.kolodyuk.controller;

import by.bsu.kolodyuk.ScreensConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController extends NavigableController {

    public MenuController(ScreensConfig screens) {
        super(screens);
    }

    @FXML
    public void onQueriesButtonPressed(ActionEvent event) {
        screens.toQueriesPage();
    }

    @FXML
    public void onTablesButtonPressed(ActionEvent event) {

    }

    @FXML
    public void onFormsButtonPressed(ActionEvent event) {

    }

    @FXML
    public void onBackButtonPressed(ActionEvent event) {
        screens.toMenuPage();
    }
}