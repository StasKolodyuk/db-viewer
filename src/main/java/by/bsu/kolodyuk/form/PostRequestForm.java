package by.bsu.kolodyuk.form;


import by.bsu.kolodyuk.ScreensConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PostRequestForm extends NavigableForm {

    public PostRequestForm(ScreensConfig screens) {
        super(screens);
    }

    @FXML
    private TextField jobField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextArea financialReportArea;

    @FXML
    public void onSubmitRequestButtonPressed(ActionEvent event) {

    }
}
