package by.bsu.kolodyuk.form;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.model.FinancialInfo;
import by.bsu.kolodyuk.model.Session;
import by.bsu.kolodyuk.repository.FinancialInfoRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
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
    private Accordion financialInfoAccordion;
    @FXML
    private List<FinancialInfo> toValidate;

    @Resource
    private FinancialInfoRepository financialInfoRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toValidate = financialInfoRepository.findByValidatedNull();
        if(toValidate.isEmpty()) {
            statusLabel.setText("No Financial Reports to Validate");
        } else {
            statusLabel.setText("Validate the following Financial Reports");
        }
        toValidate.forEach(f -> financialInfoAccordion.getPanes().add(createTitledPane(f)));
    }

    @FXML
    public void onValidateReportsButtonPressed(ActionEvent event) {
        financialInfoRepository.save(toValidate);
        screens.toReferentPage();
    }

    private TitledPane createTitledPane(FinancialInfo financialInfo) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText("UserId: " + financialInfo.getUserId());
        TextFlow textFlow = new TextFlow();

        Label label = new Label(financialInfo.toString());
        label.setWrapText(true);
        textFlow.getChildren().add(label);

        textFlow.getChildren().add(new Label("Validate: "));
        CheckBox isValidCheckBox = new CheckBox();
        CheckBox rejectCheckBox = new CheckBox();

        isValidCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == true) {
                rejectCheckBox.setSelected(false);
                financialInfo.setValidated(true);
            } else {
                financialInfo.setValidated(null);
            }
        });
        textFlow.getChildren().add(isValidCheckBox);

        textFlow.getChildren().add(new Label("Reject: "));
        rejectCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == true) {
                isValidCheckBox.setSelected(false);
                financialInfo.setValidated(false);
            } else {
                financialInfo.setValidated(null);
            }
        });
        textFlow.getChildren().add(rejectCheckBox);

        titledPane.setContent(textFlow);
        return titledPane;
    }
}
