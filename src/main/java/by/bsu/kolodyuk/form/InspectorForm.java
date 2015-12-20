package by.bsu.kolodyuk.form;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.model.FullInfo;
import by.bsu.kolodyuk.model.Session;
import by.bsu.kolodyuk.service.AccountInfoService;
import by.bsu.kolodyuk.service.CreditInfoService;
import by.bsu.kolodyuk.service.CreditRequestService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

import javax.annotation.Resource;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class InspectorForm extends NavigableForm implements Initializable {

    public InspectorForm(ScreensConfig screens, Session session) {
        super(screens, session);
    }

    @FXML
    private Accordion creditRequestAccordion;
    @FXML
    private Label statusLabel;

    @Resource
    private CreditRequestService creditRequestService;
    @Resource
    private CreditInfoService creditInfoService;
    @Resource
    private AccountInfoService accountInfoService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(accountInfoService.getValidatedAccountInfos() == null) {
            statusLabel.setText("No credit requests to analyze");
            return;
        }
        List<FullInfo> fullInfos = accountInfoService.getValidatedAccountInfos().stream()
                .map(a -> new FullInfo(creditRequestService.getCreditRequest(a.getUserId()), a,
                        creditInfoService.getCreditHistory(a.getUserId())))
                .filter(f -> f.getCreditRequest().getConfirmed() == null)
                .collect(Collectors.toList());

        if(fullInfos != null && !fullInfos.isEmpty()) {
            statusLabel.setText("Analyze the following credit requests");
            fullInfos.forEach(f -> creditRequestAccordion.getPanes().add(createTitledPane(f)));
        } else {
            statusLabel.setText("No credit requests to analyze");
        }
    }

    private TitledPane createTitledPane(FullInfo fullInfo) {
        TitledPane titledPane = new TitledPane();
        VBox vBox = new VBox();
        TextFlow textFlow = new TextFlow();
        Label label = new Label(fullInfo.toString());
        label.setWrapText(true);
        textFlow.getChildren().add(label);
        vBox.getChildren().add(textFlow);
        Button determineConditionsButton = new Button("Determine Conditions");
        determineConditionsButton.setOnAction(e -> {
            session.setUserUnderValidationId(fullInfo.getAccountInfo().getUserId());
            screens.toConditionsPage();
        });
        Button rejectCredit = new Button("Reject Credit");
        rejectCredit.setOnAction(e ->  {
            fullInfo.getCreditRequest().setConfirmed(false);
            creditRequestService.upsertCreditRequest(fullInfo.getCreditRequest());
            screens.toInspectorPage();
        });
        vBox.getChildren().addAll(determineConditionsButton, rejectCredit);
        titledPane.setContent(vBox);
        titledPane.setText("User ID: " + fullInfo.getAccountInfo().getUserId().toString());

        return titledPane;
    }
}
