package by.bsu.kolodyuk.form;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.model.User;
import by.bsu.kolodyuk.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.annotation.Resource;

public class LoginForm extends NavigableForm {

    public LoginForm(ScreensConfig screens) {
        super(screens);
    }

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    @Resource
    private UserService userService;


    @FXML
    public void onLoginButtonPressed(ActionEvent event) {
        User user = userService.getUser(loginField.getText(), passwordField.getText());
        if(user != null) {
            screens.toPostRequestPage();
        }
    }

    @FXML
    public void onRegisterButtonPressed(ActionEvent event) {
        screens.toRegisterPage();
    }
}