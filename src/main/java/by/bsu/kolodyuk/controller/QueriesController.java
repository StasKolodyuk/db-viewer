package by.bsu.kolodyuk.controller;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.annotation.Resource;

public class QueriesController {

    @Resource
    Session session;
    @Resource
    ScreensConfig screens;
    @FXML
    TextField dateField;
    @FXML
    TextField genreField;

    @FXML
    public void onFirstQueryButtonPressed(ActionEvent event) {
        session.setQuery("SELECT p.first_name, p.last_name\n" +
                         "FROM video_lib\n" +
                         "JOIN film\n" +
                         "USING (film_id)\n" +
                         "JOIN film_actor\n" +
                         "USING (film_id)\n" +
                         "JOIN actor a\n" +
                         "USING (actor_id)\n" +
                         "JOIN director p\n" +
                         "ON p.first_name = a.first_name AND p.last_name = a.last_name\n" +
                         "GROUP BY p.first_name, p.last_name");
        screens.toResultPage();
    }

    @FXML
    public void onSecondQueryButtonPressed(ActionEvent event) {
        session.setQuery("SELECT title, grade, genre_name\n" +
                         "FROM film\n" +
                         "JOIN genre_film\n" +
                         "USING (film_id)\n" +
                         "JOIN genre\n" +
                         "USING (genre_id)\n" +
                         "WHERE grade >=3 AND genre_name = '" + genreField.getText() +"'");
        screens.toResultPage();
    }

    @FXML
    public void onThirdQueryButtonPressed(ActionEvent event) {
        session.setQuery("SELECT title, creation_year\n" +
                "FROM film\n" +
                "WHERE creation_year > add_months(to_date('" + dateField.getText() + "', 'dd-mm-yyyy'), -12) AND creation_year < add_months(to_date('" + dateField.getText() + "', 'dd-mm-yyyy'), 12)");
        screens.toResultPage();
    }

    @FXML
    public void onBackButtonPressed(ActionEvent event) {
        screens.toMenuPage();
    }
}
