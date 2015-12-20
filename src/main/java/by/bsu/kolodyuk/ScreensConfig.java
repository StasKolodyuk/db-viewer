package by.bsu.kolodyuk;

import by.bsu.kolodyuk.form.*;
import by.bsu.kolodyuk.model.Session;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.net.URL;

@Configuration
@Lazy
public class ScreensConfig {

    private Stage stage;
    private Scene scene;
    private StackPane root;

    private Session session = new Session();

    public void setPrimaryStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void showMainScreen() {
        root = new StackPane();
        stage.setTitle("Bank System");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void toTop(Node node) {
        root.getChildren().setAll(node);
    }

    public void toLoginPage() {
        toTop(getNode(loginForm(), "login.fxml"));
    }

    public void toRegisterPage() {
        toTop(getNode(registerForm(), "register.fxml"));
    }

    public void toReferentPage() {
        toTop(getNode(referentForm(), "referent.fxml"));
    }

    public void toPostRequestPage() {
        toTop(getNode(postRequestForm(), "postRequest.fxml"));
    }

    public void toCreditHistoryPage() {
        toTop(getNode(creditHistoryForm(), "history.fxml"));
    }

    public void toBankOfficerPage() {
        toTop(getNode(bankOfficerForm(), "bankOfficer.fxml"));
    }

    public void toInspectorPage() {
        toTop(getNode(inspectorForm(), "inspector.fxml"));
    }

    public void toConditionsPage() {
        toTop(getNode(conditionsForm(), "conditions.fxml"));
    }

    public void toResultPage() {
        toTop(getNode(resultForm(), "results.fxml"));
    }


    @Bean
    @Scope("prototype")
    LoginForm loginForm() {
        return new LoginForm(this, session);
    }

    @Bean
    @Scope("prototype")
    RegisterForm registerForm() {
        return new RegisterForm(this, session);
    }

    @Bean
    @Scope("prototype")
    ReferentForm referentForm() {
        return new ReferentForm(this, session);
    }

    @Bean
    @Scope("prototype")
    PostRequestForm postRequestForm() {
        return new PostRequestForm(this, session);
    }

    @Bean
    @Scope("prototype")
    CreditHistoryForm creditHistoryForm() {
        return new CreditHistoryForm(this, session);
    }

    @Bean
    @Scope("prototype")
    BankOfficerForm bankOfficerForm() {
        return new BankOfficerForm(this, session);
    }

    @Bean
    @Scope("prototype")
    InspectorForm inspectorForm() {
        return new InspectorForm(this, session);
    }

    @Bean
    @Scope("prototype")
    ConditionsForm conditionsForm() {
        return new ConditionsForm(this, session);
    }

    @Bean
    @Scope("prototype")
    ResultForm resultForm() {
        return new ResultForm(this, session);
    }

    private Node getNode(final NavigableForm form, String view) {
        URL location = this.getClass().getClassLoader().getResource(view);
        FXMLLoader loader = new FXMLLoader(location);
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            public Object call(Class<?> aClass) {
                return form;
            }
        });

        try {
            return (Node) loader.load();
        } catch (Exception e) {
            return null;
        }
    }
}
