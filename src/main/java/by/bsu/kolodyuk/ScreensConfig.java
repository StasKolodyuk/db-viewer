package by.bsu.kolodyuk;

import by.bsu.kolodyuk.form.*;
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

    public void toProfilePage() {
        toTop(getNode(profileForm(), "profile.fxml"));
    }

    public void toPostRequestPage() {
        toTop(getNode(postRequestForm(), "postRequest.fxml"));
    }

    @Bean
    @Scope("prototype")
    LoginForm loginForm() {
        return new LoginForm(this);
    }

    @Bean
    @Scope("prototype")
    RegisterForm registerForm() {
        return new RegisterForm(this);
    }

    @Bean
    @Scope("prototype")
    ProfileForm profileForm() {
        return new ProfileForm(this);
    }

    @Bean
    @Scope("prototype")
    PostRequestForm postRequestForm() {
        return new PostRequestForm(this);
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
