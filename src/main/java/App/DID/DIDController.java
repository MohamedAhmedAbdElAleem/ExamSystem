package App.DID;

import App.ADoctors.ADoctorsController;
import App.EditDoctor.EditDoctorController;
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
import javafx.scene.shape.VLineTo;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DIDController {
    @FXML
    private TextField DoctorID;
    @FXML
    private Button ProccedButton;
    private String username;
    private String process;
    private ADoctorsController aDoctorsController;

    Validation validation = new Validation();
    public void initialize() {
        ProccedButton.setOnAction(ProccedButtonClicked());
    }

    private EventHandler<ActionEvent> ProccedButtonClicked() {
        return e -> {
            String doctorID = DoctorID.getText();
            if (DoctorID.getText().isEmpty()) {
                validation.showErrorPopUp("Please fill all the fields");
                return;
            }
            if(process.equalsIgnoreCase("delete")) {
                Client client = new Client();
                client.sendMessage("deleteDoctor");
                client.sendMessage(doctorID);
                String response = client.receiveMessage();
                if (response.equals("Doctor deleted successfully")) {
                    validation.showSuccessPopUp("Doctor Deleted Successfully");
                } else {
                    validation.showErrorPopUp("Doctor not found");

                }
            } else if(process.equalsIgnoreCase("edit")) {
                Client client = new Client();
                client.sendMessage("getDoctor");
                client.sendMessage(doctorID);
                String response = client.receiveMessage();
                if (response.equalsIgnoreCase("true")) {
                    Doctor doctor = null;
                    doctor = (Doctor) client.receiveObject();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/EditDoctor/EditDoctor.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException ex) {
                        System.out.println("Error in loading scene : " + ex.getMessage());
                    }
                    EditDoctorController editDoctorController = fxmlLoader.getController();
                    editDoctorController.setID(doctorID);
                    editDoctorController.setDoctor(doctor);
                    editDoctorController.setADoctorsController(aDoctorsController);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    validation.showErrorPopUp("Doctor not found");
                }
            }
        };
    }

    public void setUsername(String username1) {
        this.username = username1;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setADoctorsController(ADoctorsController aDoctorsController) {
        this.aDoctorsController = aDoctorsController;
    }
}
