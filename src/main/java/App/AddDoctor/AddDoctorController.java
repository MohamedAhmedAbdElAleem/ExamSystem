package App.AddDoctor;

import App.ADoctors.ADoctorsController;
import App.ErrorPopUp.ErrorPopUpController;
import App.SucessfulPopUp.SucessfulPopUpController;
import Main.Client;
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
    @FXML
    public EventHandler<ActionEvent> AddDoctorButtonClicked() {
        return e -> {
            String name = Name.getText();
            String password = Password.getText();
            String ssn = SSN.getText();
            if (Name.getText().isEmpty() || Password.getText().isEmpty() || SSN.getText().isEmpty())
            {
                showErrorPopUp("Please fill all the fields");
                return;
            }
            Client client = new Client();
            client.sendMessage("addDoctor");
            client.sendMessage(name);
            client.sendMessage(password);
            client.sendMessage(ssn);
            String response = client.receiveMessage();
            if (response.equals("Doctor added successfully")) {
                showSuccessPopUp("Doctor added successfully");
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
    private void showSuccessPopUp(String message) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SucessfulPopUp/SucessfulPopUp.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        SucessfulPopUpController sucessfulPopUpController = fxmlLoader.getController();
        sucessfulPopUpController.setSuccessfulMessage(message);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void showErrorPopUp(String message) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/ErrorPopUp/ErrorPopUp.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        ErrorPopUpController errorPopUpController = fxmlLoader.getController();
        errorPopUpController.setErrorMessage(message);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();


    }
}
