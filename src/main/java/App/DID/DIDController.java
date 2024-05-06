package App.DID;

import App.ADoctors.ADoctorsController;
import App.EditDoctor.EditDoctorController;
import Main.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    public void initialize() {
        ProccedButton.setOnAction(ProccedButtonClicked());
    }

    private EventHandler<ActionEvent> ProccedButtonClicked() {
        return e -> {
            String doctorID = DoctorID.getText();
            if(process.equalsIgnoreCase("delete")) {
                Client client = new Client();
                client.sendMessage("deleteDoctor");
                client.sendMessage(doctorID);
                String response = client.receiveMessage();
                if (response.equals("Doctor deleted successfully")) {
//                    aDoctorsController.initialize();
                }
            } else if(process.equalsIgnoreCase("edit")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/EditDoctor/EditDoctor.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException ex) {
                    System.out.println("Error in loading scene : "+ex.getMessage());
                }
                EditDoctorController editDoctorController = fxmlLoader.getController();
                editDoctorController.setID(doctorID);
//                editDoctorController.setProcess("edit");
                editDoctorController.setADoctorsController(aDoctorsController);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.show();

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
