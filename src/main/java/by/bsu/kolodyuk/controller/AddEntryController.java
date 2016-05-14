package by.bsu.kolodyuk.controller;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.Session;
import by.bsu.kolodyuk.util.TableUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static by.bsu.kolodyuk.util.TableUtil.getTableColumns;
import static java.util.stream.Collectors.joining;

public class AddEntryController implements Initializable
{
    @FXML
    VBox vBox;
    @Resource
    Session session;
    @Resource
    ScreensConfig screens;
    @Resource
    JdbcTemplate jdbcTemplate;
    @Resource
    DataSource dataSource;
    List<TextField> textFields;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        textFields = new ArrayList<>();
        getTableColumns(dataSource, session).stream().forEach(i -> vBox.getChildren().add(vBox.getChildren().size() - 1, createHBox(i)));
    }

    @FXML
    public void onAddButtonPressed(ActionEvent event) {
        jdbcTemplate.execute("INSERT INTO " + session.getTable() + " VALUES('" + textFields.stream().map(t -> t.getText()).collect(joining("' , '")) + "')");
        screens.toResultPage();
    }

    @FXML
    public void onBackButtonPressed(ActionEvent event) {
        screens.toResultPage();
    }

    private HBox createHBox(String column) {
        HBox hBox = new HBox(20);
        Label label = new Label(column);
        label.setPrefWidth(100);
        hBox.getChildren().add(label);
        TextField textField = new TextField();
        textFields.add(textField);
        hBox.getChildren().add(textField);
        return hBox;
    }
}
