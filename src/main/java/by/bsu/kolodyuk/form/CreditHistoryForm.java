package by.bsu.kolodyuk.form;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.model.CreditInfo;
import by.bsu.kolodyuk.model.Session;
import by.bsu.kolodyuk.model.UserType;
import by.bsu.kolodyuk.repository.CreditInfoRepository;
import by.bsu.kolodyuk.service.CreditInfoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.text.TextFlow;

import javax.annotation.Resource;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static by.bsu.kolodyuk.model.UserType.*;

public class CreditHistoryForm extends NavigableForm implements Initializable {

    @FXML
    private Accordion creditHistoryAccordion;
    @FXML
    private Label statusLabel;

    @Resource
    private CreditInfoService creditInfoService;


    public CreditHistoryForm(ScreensConfig screens, Session session) {
        super(screens, session);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<CreditInfo> creditHistory = creditInfoService.getCreditHistory(session.getUserUnderValidationId());
        if(creditHistory != null) {
            creditHistory.forEach(c -> creditHistoryAccordion.getPanes().add(createTitledPane(c)));
            statusLabel.setText("List of previous credits of the user");
        } else {
            statusLabel.setText("There is no credit history for this user");
        }
    }

    @FXML
    public void onBackButtonPressed(ActionEvent actionEvent) {
        if(session.getUserType() == REFERENT) {
            screens.toReferentPage();
        } else if (session.getUserType() == BANK_OFFICER) {
            screens.toBankOfficerPage();
        }
    }

    private TitledPane createTitledPane(CreditInfo creditInfo) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText("Credit Info ID: " + creditInfo.getCreditInfoId());
        TextFlow textFlow = new TextFlow();
        Label creditInfoLabel = new Label(creditInfo.toString());
        creditInfoLabel.setWrapText(true);
        textFlow.getChildren().add(creditInfoLabel);
        titledPane.setContent(textFlow);

        return titledPane;
    }

}
