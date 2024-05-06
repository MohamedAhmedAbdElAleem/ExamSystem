package App.AID;

import App.AAdmins.AAdminsController;
import App.EditAdmin.EditAdminController;
import Main.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AIDController {
    @FXML
    private TextField IDInput;
    private String username;
    private String process;
    private AAdminsController aAdminsController;
    @FXML
    private Button ProceedButton;
    public EventHandler<ActionEvent> ProceedButtonClicked() {
        return e -> {

             if (process.equals("Edit")) {
                 String Id = IDInput.getText();
                 Client client = new Client();
                 if(client.checkAdminId(Id)) {
                    String name = client.getUsername1();
                    String password = client.getPassword1();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/EditAdmin/EditAdmin.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException ex) {
                        System.out.println("Error in loading scene : "+ex.getMessage());
                    }
                    EditAdminController editAdminController = fxmlLoader.getController();
                    editAdminController.setAAdminsController(aAdminsController);
                    editAdminController.setUsername(name);
                    editAdminController.setPassword(password);
                    editAdminController.setAdminId(Id);
                    Stage stage = (Stage) ProceedButton.getScene().getWindow();
                    stage.setScene(scene);
                    } else {
                        System.out.println("Admin not found");
                    }

            } else if (process.equals("Delete")) {
                String Id = IDInput.getText();
                Client client = new Client();
                if(client.checkAdminId2(Id)) {
                        client.deleteAdmin(Id);
                } else {
                    System.out.println("Admin not found");
                }
            }
        };
    }
    public void initialize() {
        ProceedButton.setOnAction(ProceedButtonClicked());

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAAdminsController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}
