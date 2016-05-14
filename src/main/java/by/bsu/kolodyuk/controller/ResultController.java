package by.bsu.kolodyuk.controller;


import by.bsu.kolodyuk.ScreensConfig;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.annotation.Resource;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;

public class ResultController extends NavigableController implements Initializable {
    public ResultController(ScreensConfig screens) {
        super(screens);
    }

    @Resource
    JdbcTemplate jdbcTemplate;

    @FXML
    TableView result;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jdbcTemplate.query("SELECT * FROM CUSTOMER", (rs) -> {
            System.out.println(rs.getFetchSize());
            ObservableList<ObservableList> data = FXCollections.observableArrayList();
            for(int i = 0 ; i < rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                result.getColumns().addAll(col);
            }

            while(rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i = 1 ; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
                System.out.println("added");
            }

            result.setItems(data);
            return null;
        });
    }
}
