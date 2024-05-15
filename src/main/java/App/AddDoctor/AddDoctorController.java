package App.AddDoctor;

import App.ADoctors.ADoctorsController;
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

public class AddDoctorController {
    @FXML
    private TextField Name;
    @FXML
    private TextField Password;
    @FXML
    private TextField SSN;
    @FXML
    private Button AddButton;
    private String username;
    private ADoctorsController aDoctorsController;
    Validation validation = new Validation();
    @FXML
    public EventHandler<ActionEvent> AddDoctorButtonClicked() {
        return e -> {
            String name = Name.getText();
            String password = Password.getText();
            String ssn = SSN.getText();
            if (Name.getText().isEmpty() || Password.getText().isEmpty() || SSN.getText().isEmpty())
            {
                validation.showErrorPopUp("Please fill all the fields");
                return;
            }
            Encryptor encryptor = new Encryptor();
            password = encryptor.funcEncrypt(password);
            Client client = new Client();
            client.sendMessage("addDoctor");
            client.sendMessage(name);
            client.sendMessage(password);
            client.sendMessage(ssn);
            String response = client.receiveMessage();
            if (response.equals("Doctor added successfully")) {
                validation.showSuccessPopUp("Doctor added successfully");
            }
            else {
                validation.showErrorPopUp("Doctor not added");
            }
        };
    }
    public void initialize() {
        AddButton.setOnAction(AddDoctorButtonClicked());
    }
    public void setUsername(String username1) {
        this.username = username1;
    }

    public void setAAdminsController(ADoctorsController aDoctorsController) {
        this.aDoctorsController = aDoctorsController;
    }

}
