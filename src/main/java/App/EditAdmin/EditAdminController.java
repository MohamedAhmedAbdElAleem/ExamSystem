package App.EditAdmin;

import App.AAdmins.AAdminsController;
import Main.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
