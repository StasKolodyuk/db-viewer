package by.bsu.kolodyuk.form;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.model.CreditConditions;
import by.bsu.kolodyuk.model.CreditRequest;
import by.bsu.kolodyuk.model.Session;
import by.bsu.kolodyuk.service.CreditConditionsService;
import by.bsu.kolodyuk.service.CreditRequestService;
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
    private CreditRequestService creditRequestService;

    private CreditRequest creditRequest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditRequest = creditRequestService.getCreditRequest(session.getUserId());
        if(creditRequest != null) {
            if(creditRequest.getValidated() == null) {
                statusLabel.setText("Your Financial Info is waiting for validation");
            } else if (creditRequest.getValidated() == true) {
                statusLabel.setText("Your Financial Info passed validation");
            } else {
                statusLabel.setText("Your Financial Info failed validation. Please Edit");
            }
            if(creditRequest.getConfirmed() != null && creditRequest.getConfirmed() == false) {
                statusLabel.setText("Your Credit Request was rejected");
            }
            moneyField.setText(creditRequest.getMoneyAmount().toString());
            interestField.setText(creditRequest.getInterestPercentage().toString());
            timeField.setText(creditRequest.getTimeRange().toString());
            jobField.setText(creditRequest.getJob());
            salaryField.setText(creditRequest.getSalary().toString());
            financialReportArea.setText(creditRequest.getFinancialReport());
            if(creditRequest.getValidated() == null || creditRequest.getValidated() == true) {
                formPane.setDisable(true);
            }
        } else {
            statusLabel.setText("Please, provide required information");
        }
    }

    @FXML
    public void onSubmitRequestButtonPressed(ActionEvent event) {
        processCreditRequest();
        screens.toPostRequestPage();
    }

    private void processCreditRequest() {
        CreditRequest credit = creditRequest != null ? creditRequest : new CreditRequest();
        credit.setUserId(session.getUserId());
        credit.setMoneyAmount(Long.parseLong(moneyField.getText()));
        credit.setInterestPercentage(Integer.parseInt(interestField.getText()));
        credit.setTimeRange(Integer.parseInt(timeField.getText()));
        credit.setJob(jobField.getText());
        credit.setSalary(Integer.parseInt(salaryField.getText()));
        credit.setFinancialReport(financialReportArea.getText());
        credit.setValidated(null);
        creditRequestService.upsertCreditRequest(credit);
    }
}
