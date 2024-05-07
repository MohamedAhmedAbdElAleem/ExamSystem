package App.EditAdmin;

import App.AAdmins.AAdminsController;
import App.ErrorPopUp.ErrorPopUpController;
import App.SucessfulPopUp.SucessfulPopUpController;
import Main.Client;
import Main.Validation;
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
