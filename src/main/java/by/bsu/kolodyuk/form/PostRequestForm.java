package by.bsu.kolodyuk.form;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.model.FinancialInfo;
import by.bsu.kolodyuk.model.Session;
import by.bsu.kolodyuk.service.FinancialInfoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

public class PostRequestForm extends NavigableForm implements Initializable {

    public PostRequestForm(ScreensConfig screens, Session session) {
        super(screens, session);
    }


    @FXML
    private AnchorPane formPane;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField moneyField;
    @FXML
    private TextField interestField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField jobField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextArea financialReportArea;

    @Resource
    private FinancialInfoService financialInfoService;

    private FinancialInfo financialInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        financialInfo = financialInfoService.getFinancialInfo(session.getUserId());
        if(financialInfo != null) {
            if(financialInfo.getValidated() == null) {
                statusLabel.setText("Your Financial Info is waiting for validation");
            } else if (financialInfo.getValidated() == true) {
                statusLabel.setText("Your Financial Info passed validation");
            } else {
                statusLabel.setText("Your Financial Info failed validation. Please Edit");
            }
            moneyField.setText(financialInfo.getMoneyAmount().toString());
            interestField.setText(financialInfo.getInterestPercentage().toString());
            timeField.setText(financialInfo.getTimeRange().toString());
            jobField.setText(financialInfo.getJob());
            salaryField.setText(financialInfo.getSalary().toString());
            financialReportArea.setText(financialInfo.getFinancialReport());
            if(financialInfo.getValidated() == null || financialInfo.getValidated() == true) {
                formPane.setDisable(true);
            }
        } else {
            statusLabel.setText("Please, provide required information");
        }
    }

    @FXML
    public void onSubmitRequestButtonPressed(ActionEvent event) {
        processFinancialInfo();
        screens.toPostRequestPage();
    }

    private void processFinancialInfo() {
        FinancialInfo financial = financialInfo != null ? financialInfo : new FinancialInfo();
        financial.setUserId(session.getUserId());
        financial.setMoneyAmount(Long.parseLong(moneyField.getText()));
        financial.setInterestPercentage(Integer.parseInt(interestField.getText()));
        financial.setTimeRange(Integer.parseInt(timeField.getText()));
        financial.setJob(jobField.getText());
        financial.setSalary(Integer.parseInt(salaryField.getText()));
        financial.setFinancialReport(financialReportArea.getText());
        financial.setValidated(null);
        financialInfoService.upsertFinancialInfo(financial);
    }
}
