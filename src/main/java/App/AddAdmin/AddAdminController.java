package App.AddAdmin;

import App.AAdmins.AAdminsController;
import App.ErrorPopUp.ErrorPopUpController;
import App.SucessfulPopUp.SucessfulPopUpController;
import Main.Client;
import Main.Encryptor;
import Main.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddAdminController {
    @FXML
    private Button AddButton;
    private String username;
    private AAdminsController aAdminsController;
    @FXML
    private TextField AName;
    @FXML
    private TextField APassword;
    Validation validation = new Validation();
    Encryptor encryptor = new Encryptor();

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAAdminsController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }
    private EventHandler<ActionEvent> AddButtonClicked() {
        return e -> {
            if (AName.getText().isEmpty() || APassword.getText().isEmpty()) {
                validation.showErrorPopUp("Please fill all the fields");
            } else {
                Client client = new Client();
                String name = AName.getText();
                String password = APassword.getText();
                password = encryptor.funcEncrypt(password);
                client.addAdmin(name, password);
                validation.showSuccessPopUp("Admin Added Successfully");
            }
        };
    }
    public void initialize() {
        AddButton.setOnAction(AddButtonClicked());
    }
}
