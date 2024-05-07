package App.AddAdmin;

import App.AAdmins.AAdminsController;
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

public class AddAdminController {
    @FXML
    private Button AddButton;
    private String username;
    private AAdminsController aAdminsController;
    @FXML
    private TextField AName;
    @FXML
    private TextField APassword;

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
    public void setUsername(String username) {
        this.username = username;
    }

    public void setAAdminsController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }
    private EventHandler<ActionEvent> AddButtonClicked() {
        return e -> {
            if (AName.getText().isEmpty() || APassword.getText().isEmpty()) {
                showErrorPopUp("Please fill all the fields");
            } else {
                Client client = new Client();
                String name = AName.getText();
                String password = APassword.getText();
                client.addAdmin(name, password);
                showSuccessPopUp("Admin Added Successfully");
            }
        };
    }
    public void initialize() {
        AddButton.setOnAction(AddButtonClicked());
    }
}
