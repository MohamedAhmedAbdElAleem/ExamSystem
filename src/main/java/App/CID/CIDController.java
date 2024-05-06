package App.CID;

import App.ACourses.ACoursesController;
import Main.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CIDController {
    @FXML
    private TextField CID;
    @FXML
    private Button ProceedButton;

    private String username1;
    private ACoursesController aCoursesController;
    public void initialize() {
        ProceedButton.setOnAction(proceedButtonClicked());
    }

    private EventHandler<ActionEvent> proceedButtonClicked() {
        return e -> {
            String id = CID.getText();
            Client client = new Client();
            client.sendMessage("checkCourseId");
            client.sendMessage(id);
            String response = client.receiveMessage();
            if (response.equalsIgnoreCase("true")){
                client.sendMessage("deleteCourse");
                client.sendMessage(id);
                response = client.receiveMessage();
                if (response.equalsIgnoreCase("true")){
//                    aCoursesController.refreshTable();
                }
            }
        };

    }

    public void setUsername(String username1) {
        this.username1 = username1;
    }

    public void setACoursesController(ACoursesController aCoursesController) {
        this.aCoursesController = aCoursesController;
    }
}
