package App.SBefore;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;


public class SBeforeController {
    @FXML
    private ComboBox<String> choice1;

    @FXML
    public void initialize() {
        choice1.setItems(FXCollections.observableArrayList("Advanced Programming Application", "Data Structure"));
    }
}
