package by.bsu.kolodyuk.controller;

import by.bsu.kolodyuk.ScreensConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.annotation.Resource;

public class MenuController {

    @Resource
    ScreensConfig screens;

    @FXML
    public void onQueriesButtonPressed(ActionEvent event) {
        screens.toQueriesPage();
    }

    @FXML
    public void onTablesButtonPressed(ActionEvent event) {
        screens.toTablesPage();
    }
}
