package App.DoctorLogin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Main.Client;
public class DoctorLoginController {
    @FXML
    private TextField UID;
    @FXML
    private PasswordField UPassword;
    @FXML
    private Button LogInButton;
    @FXML
    public void initialize() {
        LogInButton.setOnAction(event -> {
            String username = UID.getText();
            String password = UPassword.getText();
            // Send the username and password to the server
            Client client = new Client();
            client.sendMessage("login");
            client.sendMessage("Doctor");
            client.sendMessage(username);
            client.sendMessage(password);
            String message = client.receiveMessage();
            // Display the message from the server
            System.out.println(message);
            client.close();
        });
    }
}
