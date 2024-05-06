package App.EditDoctor;

import App.ADoctors.ADoctorsController;
import Main.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
//                 aDoctorsController.initialize();
             }
        };
    }

    public void setADoctorsController(ADoctorsController aDoctorsController) {
        this.aDoctorsController = aDoctorsController;
    }
}
