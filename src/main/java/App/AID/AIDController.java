package App.AID;

import App.AAdmins.AAdminsController;
import App.EditAdmin.EditAdminController;
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

public class AIDController {
    @FXML
    private TextField IDInput;
    private String username;
    private String process;
    private AAdminsController aAdminsController;
    @FXML
    private Button ProceedButton;
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
                     showErrorPopUp("Admin not found");
                    }

            } else if (process.equals("Delete")) {
                String Id = IDInput.getText();
                Client client = new Client();
                if(client.checkAdminId2(Id)) {
                    showSuccessPopUp("Admin Deleted Successfully");
                        client.deleteAdmin(Id);
                } else {
                    showErrorPopUp("Admin not found");
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
