package App.GetPassword;

import App.StudentLogin.StudentLoginController;
import Main.Client;
import Main.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GetPasswordController {
    @FXML
    private Button GetButton;
    @FXML
    private TextField id;

    @FXML
    private TextField ssn;

    Validation validation = new Validation();

    public void initialize() {
        GetButton.setOnAction(GetButtonClicked());
    }

    private EventHandler<ActionEvent> GetButtonClicked() {
        return e -> {
            String ID = id.getText();
            String SSN = ssn.getText();
            if (ID.isEmpty() || SSN.isEmpty()) {
                validation.showErrorPopUp("Please fill all the fields");
                return;
            }
            Client client = new Client();
            client.sendMessage("getPassword");
            client.sendMessage(ID);
            client.sendMessage(SSN);
            String response = client.receiveMessage();
            if (response.equals("true")) {
                String password = client.receiveMessage();
                validation.showSuccessPopUp("Your password is: " + password);
                return;
            }
            else {
                validation.showErrorPopUp("Invalid ID or SSN");
            }
        };

    }


    private StudentLoginController studentLoginController;
    public void setLoginController(StudentLoginController studentLoginController) {
        this.studentLoginController = studentLoginController;
    }
}
