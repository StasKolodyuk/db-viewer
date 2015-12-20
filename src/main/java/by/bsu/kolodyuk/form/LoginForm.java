package by.bsu.kolodyuk.form;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.model.CreditConditions;
import by.bsu.kolodyuk.model.Session;
import by.bsu.kolodyuk.model.User;
import by.bsu.kolodyuk.service.CreditConditionsService;
import by.bsu.kolodyuk.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.annotation.Resource;

public class LoginForm extends NavigableForm {

    public LoginForm(ScreensConfig screens, Session session) {
        super(screens, session);
    }

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;

    @Resource
    private UserService userService;
    @Resource
    private CreditConditionsService creditConditionsService;


    @FXML
    public void onLoginButtonPressed(ActionEvent event) {
        User user = userService.getUser(loginField.getText(), passwordField.getText());
        if(user == null) {
            statusLabel.setText("Wrong Login or Password !!!");
            loginField.clear();
            passwordField.clear();
            return;
        }
        session.setUserId(user.getUserId());
        session.setUserType(user.getUserType());
        switch(user.getUserType()) {
            case SIMPLE:
                CreditConditions creditConditions = creditConditionsService.getCreditConditions(session.getUserId());
                if(creditConditions != null) {
                    screens.toResultPage();
                } else {
                    screens.toPostRequestPage();
                }
                break;
            case REFERENT:
                screens.toReferentPage();
                break;
            case BANK_OFFICER:
                screens.toBankOfficerPage();
                break;
            case INSPECTOR:
                screens.toInspectorPage();
                break;
        }
    }

    @FXML
    public void onRegisterButtonPressed(ActionEvent event) {
        screens.toRegisterPage();
    }
}