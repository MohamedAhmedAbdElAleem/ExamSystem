package App.EditDoctor;

import App.ADoctors.ADoctorsController;
import App.ErrorPopUp.ErrorPopUpController;
import App.SucessfulPopUp.SucessfulPopUpController;
import Main.Client;
import Main.Doctor;
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

public class EditDoctorController {
    @FXML
    private TextField Name;
    @FXML
    private TextField Password;
    @FXML
    private TextField SSN;
    @FXML
    private Button EditButton;
    private String doctorID;
    private ADoctorsController aDoctorsController;
    public void setID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void initialize() {
        EditButton.setOnAction(EditButtonClicked());
    }

    private EventHandler<ActionEvent> EditButtonClicked() {
        return e -> {
            String name = Name.getText();
            String password = Password.getText();
            String ssn = SSN.getText();
             Client client = new Client();
             client.sendMessage("editDoctor");
             client.sendMessage(doctorID);
             client.sendMessage(name);
             client.sendMessage(password);
             client.sendMessage(ssn);
             String response = client.receiveMessage();
             if (response.equals("Doctor edited successfully")) {
                    showSuccessPopUp("Doctor Edited Successfully");
                } else {
                    showErrorPopUp("Doctor not found");
             }
        };
    }

    public void setADoctorsController(ADoctorsController aDoctorsController) {
        this.aDoctorsController = aDoctorsController;
    }

    public void setDoctor(Doctor doctor) {
        Name.setText(doctor.getDname());
        Password.setText(doctor.getDpassword());
        SSN.setText(doctor.getDssn());
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
