package by.bsu.kolodyuk.controller;


import by.bsu.kolodyuk.ScreensConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class QueriesController extends NavigableController {
    public QueriesController(ScreensConfig screens) {
        super(screens);
    }

    @FXML
    public void onFirstQueryButtonPressed(ActionEvent event) {
        screens.toResultPage();
    }

    @FXML
    public void onSecondQueryButtonPressed(ActionEvent event) {

    }

    @FXML
    public void onThirdQueryButtonPressed(ActionEvent event) {

    }

    @FXML
    public void onBackButtonPressed(ActionEvent event) {
        screens.toMenuPage();
    }
}
