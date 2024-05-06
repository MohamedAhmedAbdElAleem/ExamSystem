package App.AddCourse;

import App.ACourses.ACoursesController;
import Main.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddCourseController {
    @FXML
    private TextField CourseName;
    @FXML
    private TextField CCreditHours;
    @FXML
    private TextField CDID;
    @FXML
    private Button AddButton;

    private String username;
    private ACoursesController aCoursesController;

    public void initialize() {
        AddButton.setOnAction(addButtonClicked());
    }

    private EventHandler<ActionEvent> addButtonClicked() {
        return e -> {
            String name = CourseName.getText();
            String creditHours = CCreditHours.getText();
            String id = CDID.getText();
            if (name.isEmpty() || creditHours.isEmpty() || id.isEmpty()){
                return;
            }
            Client client = new Client();
            client.sendMessage("addCourse");
            client.sendMessage(name);
            client.sendMessage(creditHours);
            client.sendMessage(id);
            String response = client.receiveMessage();
            if (response.equalsIgnoreCase("true")){
//                aCoursesController.refreshTable();
            }

        };

    }

    public void setUsername(String username1) {
        this.username = username1;
    }

    public void setACoursesController(ACoursesController aCoursesController) {
        this.aCoursesController = aCoursesController;
    }
}
