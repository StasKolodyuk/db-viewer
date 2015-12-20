package by.bsu.kolodyuk.form;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.model.CreditConditions;
import by.bsu.kolodyuk.model.Session;
import by.bsu.kolodyuk.service.CreditConditionsService;
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

public class ResultForm extends NavigableForm implements Initializable {

    public ResultForm(ScreensConfig screens, Session session) {
        super(screens, session);
    }

    @FXML
    private Label statusLabel;
    @FXML
    private AnchorPane formPane;
    @FXML
    private TextField moneyField;
    @FXML
    private TextField interestField;
    @FXML
    private TextField timeField;
    @FXML
    private TextArea conditionsArea;

    @Resource
    private CreditConditionsService creditConditionsService;

    private CreditConditions creditConditions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditConditions = creditConditionsService.getCreditConditions(session.getUserId());
        if(creditConditions.getAccepted() == null) {
            statusLabel.setText("Accept or Reject the following credit conditions");
        } else if(creditConditions.getAccepted() == true) {
            statusLabel.setText("You've accepted the following credit conditions");
            formPane.setDisable(true);
        } else {
            statusLabel.setText("You've rejected the following credit conditions");
            formPane.setDisable(true);
        }
        moneyField.setText(creditConditions.getMoneyAmount().toString());
        interestField.setText(creditConditions.getInterestPercentage().toString());
        timeField.setText(creditConditions.getTimeRange().toString());
        conditionsArea.setText(creditConditions.getCreditConditions());
    }

    @FXML
    private void onAcceptConditionsButtonPressed(ActionEvent event) {
        creditConditions.setAccepted(true);
        creditConditionsService.saveCreditConditions(creditConditions);
        screens.toResultPage();
    }

    @FXML
    private void onRejectConditionsButtonPressed(ActionEvent event) {
        creditConditions.setAccepted(false);
        creditConditionsService.saveCreditConditions(creditConditions);
        screens.toResultPage();
    }

}
