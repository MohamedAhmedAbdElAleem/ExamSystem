package App.EditDoctor;

import App.ADoctors.ADoctorsController;
import App.ErrorPopUp.ErrorPopUpController;
import App.SucessfulPopUp.SucessfulPopUpController;
import Main.Client;
import Main.Doctor;
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
    Validation validation = new Validation();
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
                 validation.showSuccessPopUp("Doctor Edited Successfully");
                } else {
                 validation.showErrorPopUp("Doctor not found");
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


}
