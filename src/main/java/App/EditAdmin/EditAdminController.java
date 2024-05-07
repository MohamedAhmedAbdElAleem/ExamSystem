package App.EditAdmin;

import App.AAdmins.AAdminsController;
import App.ErrorPopUp.ErrorPopUpController;
import App.SucessfulPopUp.SucessfulPopUpController;
import Main.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditAdminController {
    @FXML
    private TextField Name;
    @FXML
    private TextField Pass;
    private String username;
    private String password;
    private String id;
    private AAdminsController aAdminsController;
    @FXML
    private Button EditButton;

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
    public void EditButtonClicked() {
        Client client = new Client();
        client.editAdmin(id, Name.getText(), Pass.getText());
    }
    public void initialize() {
        EditButton.setOnAction(e -> EditButtonClicked());
    }
    public void setUsername(String username) {
        this.username = username;
        Name.setText(username);
    }

    public void setAAdminsController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }

    public void setPassword(String password) {
        this.password = password;
        Pass.setText(password);
    }

    public void setAdminId(String id) {
        this.id = id;
    }
}
