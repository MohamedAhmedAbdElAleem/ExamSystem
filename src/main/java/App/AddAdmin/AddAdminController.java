package App.AddAdmin;

import App.AAdmins.AAdminsController;
import Main.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddAdminController {
    @FXML
    private Button AddButton;
    private String username;
    private AAdminsController aAdminsController;
    @FXML
    private TextField AName;
    @FXML
    private TextField APassword;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAAdminsController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }
    private EventHandler<ActionEvent> AddButtonClicked() {
        if (AName.getText().isEmpty() || APassword.getText().isEmpty()
        ) {
            return e -> {
            };
        }
        return e -> {
            Client client = new Client();
            String name = AName.getText();
            String password = APassword.getText();
            client.addAdmin(name, password);
        };
    }
    public void initialize() {
        AddButton.setOnAction(AddButtonClicked());
    }
}
