package by.bsu.kolodyuk.form;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.model.CreditRequest;
import by.bsu.kolodyuk.model.Session;
import by.bsu.kolodyuk.service.CreditRequestService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

import javax.annotation.Resource;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReferentForm extends NavigableForm implements Initializable {

    public ReferentForm(ScreensConfig screens, Session session) {
        super(screens, session);
    }

    @FXML
    private Label statusLabel;
    @FXML
    private Accordion creditRequestAccordion;
    @FXML
    private List<CreditRequest> toValidate;

    @Resource
    private CreditRequestService creditRequestService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toValidate = creditRequestService.getNotValidatedCreditRequests();
        if(toValidate.isEmpty()) {
            statusLabel.setText("No Financial Reports to Validate");
        } else {
            statusLabel.setText("Validate the following Financial Reports");
        }
        toValidate.forEach(f -> creditRequestAccordion.getPanes().add(createTitledPane(f)));
    }

    @FXML
    public void onValidateReportsButtonPressed(ActionEvent event) {
        creditRequestService.updateCreditRequests(toValidate);
        screens.toReferentPage();
    }

    private TitledPane createTitledPane(CreditRequest creditRequest) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText("UserId: " + creditRequest.getUserId());
        VBox vBox = new VBox();
        TextFlow textFlow = new TextFlow();

        Label label = new Label(creditRequest.toString());
        label.setWrapText(true);
        textFlow.getChildren().add(label);

        textFlow.getChildren().add(new Label("Validate: "));
        CheckBox isValidCheckBox = new CheckBox();
        CheckBox rejectCheckBox = new CheckBox();

        isValidCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == true) {
                rejectCheckBox.setSelected(false);
                creditRequest.setValidated(true);
            } else {
                creditRequest.setValidated(null);
            }
        });
        textFlow.getChildren().add(isValidCheckBox);

        textFlow.getChildren().add(new Label("Reject: "));
        rejectCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == true) {
                isValidCheckBox.setSelected(false);
                creditRequest.setValidated(false);
            } else {
                creditRequest.setValidated(null);
            }
        });
        textFlow.getChildren().add(rejectCheckBox);

        Button creditHistoryButton = new Button("View Credit History");
        creditHistoryButton.setOnAction(e -> {
            session.setUserUnderValidationId(creditRequest.getUserId());
            screens.toCreditHistoryPage();
        });
        vBox.getChildren().add(textFlow);
        vBox.getChildren().add(creditHistoryButton);

        titledPane.setContent(vBox);

        return titledPane;
    }
}
