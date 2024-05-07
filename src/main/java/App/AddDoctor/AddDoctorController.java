package App.AddDoctor;

import App.ADoctors.ADoctorsController;
import Main.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        if (Name.getText().isEmpty() || Password.getText().isEmpty() || SSN.getText().isEmpty())
        {
            System.out.println("Please fill all the fields");
            return null;
        }
        return e -> {
            String name = Name.getText();
            String password = Password.getText();
            String ssn = SSN.getText();
            Client client = new Client();
            client.sendMessage("addDoctor");
            client.sendMessage(name);
            client.sendMessage(password);
            client.sendMessage(ssn);
            String response = client.receiveMessage();
            if (response.equals("Doctor added successfully")) {
//                aDoctorsController.initialize();
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
