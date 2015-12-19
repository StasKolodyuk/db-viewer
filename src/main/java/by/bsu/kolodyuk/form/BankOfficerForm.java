package by.bsu.kolodyuk.form;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.model.AccountInfo;
import by.bsu.kolodyuk.model.Session;
import by.bsu.kolodyuk.service.AccountInfoService;
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

public class BankOfficerForm extends NavigableForm implements Initializable {

    public BankOfficerForm(ScreensConfig screens, Session session) {
        super(screens, session);
    }

    @FXML
    private Label statusLabel;
    @FXML
    private Accordion accountInfoAccordion;

    @Resource
    private AccountInfoService accountInfoService;

    private List<AccountInfo> toValidate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toValidate = accountInfoService.getNotValidatedAccountInfos();
        if(toValidate.isEmpty()) {
            statusLabel.setText("No Account Infos to Validate");
        } else {
            statusLabel.setText("Validate the following Account Infos");
        }
        toValidate.forEach(a -> accountInfoAccordion.getPanes().add(createTitledPane(a)));
    }

    @FXML
    public void onValidateAccountInfosButtonPressed(ActionEvent event) {
        accountInfoService.updateAccountInfos(toValidate);
        screens.toBankOfficerPage();
    }

    private TitledPane createTitledPane(AccountInfo accountInfo) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText("User ID: " + accountInfo.getUserId());

        VBox vBox = new VBox();
        TextFlow textFlow = new TextFlow();

        Label label = new Label(accountInfo.toString());
        label.setWrapText(true);
        textFlow.getChildren().add(label);

        textFlow.getChildren().add(new Label("Validate: "));
        CheckBox isValidCheckBox = new CheckBox();
        CheckBox rejectCheckBox = new CheckBox();

        isValidCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == true) {
                rejectCheckBox.setSelected(false);
                accountInfo.setValidated(true);
            } else {
                accountInfo.setValidated(null);
            }
        });
        textFlow.getChildren().add(isValidCheckBox);

        textFlow.getChildren().add(new Label("Reject: "));
        rejectCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == true) {
                isValidCheckBox.setSelected(false);
                accountInfo.setValidated(false);
            } else {
                accountInfo.setValidated(null);
            }
        });
        textFlow.getChildren().add(rejectCheckBox);

        Button creditHistoryButton = new Button("View Credit History");
        creditHistoryButton.setOnAction(e -> {
            session.setUserUnderValidationId(accountInfo.getUserId());
            screens.toCreditHistoryPage();
        });

        vBox.getChildren().add(textFlow);
        vBox.getChildren().add(creditHistoryButton);

        titledPane.setContent(vBox);
        return titledPane;
    }
}
