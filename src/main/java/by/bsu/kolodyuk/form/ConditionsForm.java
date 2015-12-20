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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

public class ConditionsForm extends NavigableForm implements Initializable {

    public ConditionsForm(ScreensConfig screens, Session session) {
        super(screens, session);
    }

    @FXML
    private TextField moneyField;
    @FXML
    private TextField interestField;
    @FXML
    private TextField timeField;
    @FXML
    private TextArea conditionsArea;

    @Resource
    private CreditRequestService creditRequestService;
    @Resource
    private CreditConditionsService creditConditionsService;

    private CreditRequest creditRequest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditRequest = creditRequestService.getCreditRequest(session.getUserUnderValidationId());
        moneyField.setText(creditRequest.getMoneyAmount().toString());
        interestField.setText(creditRequest.getInterestPercentage().toString());
        timeField.setText(creditRequest.getTimeRange().toString());
    }

    @FXML
    public void onPostConditionsButtonPressed(ActionEvent event) {
        creditRequest.setConfirmed(true);
        creditRequestService.upsertCreditRequest(creditRequest);

        CreditConditions creditConditions = new CreditConditions();
        creditConditions.setUserId(creditRequest.getUserId());
        creditConditions.setMoneyAmount(Long.parseLong(moneyField.getText()));
        creditConditions.setInterestPercentage(Integer.parseInt(interestField.getText()));
        creditConditions.setTimeRange(Integer.parseInt(timeField.getText()));
        creditConditions.setCreditConditions(conditionsArea.getText());

        creditConditionsService.saveCreditConditions(creditConditions);

        screens.toInspectorPage();
    }
}
