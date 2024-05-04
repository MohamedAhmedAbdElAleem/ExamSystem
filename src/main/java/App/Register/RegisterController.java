package App.Register;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RegisterController {

    @FXML
    private Button RegButton;

    @FXML
    public void initialize() {
        RegButton.setOnAction(e -> {
            System.out.println("Hello, World!");
        });
    }
}