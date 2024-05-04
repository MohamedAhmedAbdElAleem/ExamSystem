package App.AdminLogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class AdminLoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.AdminLogin.AdminLoginApplication.class.getResource("AdminLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log In!");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}
