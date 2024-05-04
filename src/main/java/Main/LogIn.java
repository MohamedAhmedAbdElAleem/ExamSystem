package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogIn extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Client client = new Client();
        VBox root = new VBox();
        Label label = new Label("Log In");
        root.getChildren().add(label);
        Label usernameLabel = new Label("Username");
        root.getChildren().add(usernameLabel);
        TextField usernameField = new TextField();
        root.getChildren().add(usernameField);
        Label passwordLabel = new Label("Password");
        root.getChildren().add(passwordLabel);
        TextField passwordField = new TextField();
        root.getChildren().add(passwordField);
        Button logInButton = new Button("Log In");
        root.getChildren().add(logInButton);
        Label messageLabel = new Label();
        root.getChildren().add(messageLabel);
        logInButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            client.sendMessage("login");
            client.sendMessage(username);
            client.sendMessage(password);
//            client.sendMessage("student");
            String message = client.receiveMessage();
            messageLabel.setText(message);
            client.close();
        });
        root.setAlignment(javafx.geometry.Pos.CENTER);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Log In");
        primaryStage.show();
    }
}
